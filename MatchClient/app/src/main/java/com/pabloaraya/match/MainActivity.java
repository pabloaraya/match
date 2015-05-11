package com.pabloaraya.match;

import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.pabloaraya.match.fragment.BlindlyFragment;
import com.pabloaraya.match.fragment.LoginFragment;
import com.pabloaraya.match.fragment.MainFragment;

import java.net.URISyntaxException;


public class MainActivity extends ActionBarActivity {

    /* Public socket */
    public static Socket socket;

    /* Volley requests */
    public static RequestQueue queue;

    /* Shared Preferences */
    public static SharedPreferences sharedPref;
    public static SharedPreferences.Editor sharedEditorPref;

    /* Var constant */
    public final static String VAR_USER_ID = "user_id";
    public final static String VAR_FACEBOOK_ID = "facebook_id";
    public final static String VAR_FIRST_NAME = "first_name";
    public final static String VAR_LAST_NAME = "last_name";
    public final static String VAR_PROFILE_AVATAR = "profile_avatar";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Instance Request Queue */
        queue = Volley.newRequestQueue(this);

        try {
            /* Instance Socket */
            socket = IO.socket(getString(R.string.url_socket));
            socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

                @Override
                public void call(Object... args) {
                    socket.emit("new user", "");
                }
            }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {

                @Override
                public void call(Object... args) {
                    socket.emit("disconnect", "");
                }
            });
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        /* Instance Shared Preference */
        sharedPref = getSharedPreferences(getString(R.string.app_name), MODE_PRIVATE);
        sharedEditorPref = sharedPref.edit();

        //if (savedInstanceState == null) {
            if(sharedPref.contains(VAR_FACEBOOK_ID)) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.root_container, MainFragment.newInstance())
                        .commit();
            }{
                getSupportFragmentManager().beginTransaction()
                        // IndexFragment! for login
                        .replace(R.id.root_container, LoginFragment.newInstance())
                        .commit();
            }
        //}

        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
            return;
        }
        super.onBackPressed();
    }
}
