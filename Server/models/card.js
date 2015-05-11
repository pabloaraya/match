var mongoose = require('mongoose');

var cardSchema = new mongoose.Schema({
  title       	: String,
  description 	: String,
  latitude    	: String,
  longitude   	: String,
  distance	  	: Number,
  category    	: Number,
  privacy       : Boolean,
  image		  	  : String,
  time_created  : { type : Date, default: Date.now },
  moment        : String,
  user     	    : { type: mongoose.Schema.ObjectId, ref: 'User' },
  is_active     : Boolean,
  is_sponsored  : Boolean,
  likes         : Array,
  users         : Array,
  references    : [{ type: mongoose.Schema.ObjectId, ref: 'User' }],
  user         	: { type: mongoose.Schema.ObjectId, ref: 'User' }
});
var Card = mongoose.model('Card', cardSchema);
module.exports = { Card: Card };