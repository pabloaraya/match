/* Import library */
var express = require('express');
var app = express(),
//mongoose = require('mongoose'),
http = require('http'),
server = http.createServer(app),
io = require('socket.io').listen(server);
//path = require('path');

/* Configuration */
app.set('port', process.env.PORT || 80);
//app.use(express.bodyParser({limit: '10mb'}));
//app.use(express.bodyParser());

/* Listen server */
server.listen(app.get('port'), function(){
  console.log("Blindly server listening on port " + app.get('port'));
});

/* Users connected */
var users = {};

/* Socket connection */
io.on('connection', function(socket){

  /* Event new user */
  socket.on('new user', function(data){

    /* Verify if user exits */
    if(!(data in users)){
      socket.user_id = data;
      users[socket.user_id] = socket;
    }
  });

  /*  */
  socket.on('chat message', function(msg){
    socket.broadcast.emit('send message', msg);
  });

  socket.on('private message', function(msg){
    if(msg.destiny_id in users){
      users[msg.destiny_id].emit('private message', msg);
      users[msg.user_id].emit('private message', msg);
    }
  });

  socket.on('disconnect', function(data){
    if(!socket.user_id) return;
    delete users[socket.user_id];
  });

});

/* Index app */
app.get('/get', function(req, res){

  console.log('call the server!!');

  var response = [];
  response.push({
    name: 'Pablo',
    text: 'Este es un texto',
    age: 25
  });
  response.push({
    name: 'Amelia',
    text: 'Este es un segundo texto',
    age: 23
  });
  response.push({
    name: 'Petrolina',
    text: 'Este es el texto de la Petrolina',
    age: 20
  });
  response.push({
    name: 'Sergio',
    text: 'Este es el texto mucho más largo que los anteriores. Queremos probar los acentos y carácteres extraños.',
    age: 20
  });
  res.send(response);
});

/* Register user */
app.post('/register', function(req, res){

});

/* Update user */
app.post('/update', function(req, res){

});