package com.quebragelo.quebragelo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.widget.TextView;
import com.facebook.*;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.quebragelo.quebragelo.task.AddPersonTask;
import com.quebragelo.quebragelo.vo.PersonVO;

import java.util.Arrays;

public class MainActivity extends Activity {

    private CallbackManager callbackManager;
    private ProfileTracker profileTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);

        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);

        loginButton.setReadPermissions(Arrays.asList("user_status", "user_birthday", "email", "user_about_me"));

        callbackManager = CallbackManager.Factory.create();
        loginButton.registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        AddPersonTask task = new AddPersonTask();

                        PersonVO person = new PersonVO();

                        person.setToken(loginResult.getAccessToken());
                        person.setEmail("");
                        person.setName("");

                        task.execute(person);

                        profileTracker = new ProfileTracker() {
                            @Override
                            protected void onCurrentProfileChanged(
                                    Profile oldProfile,
                                    Profile currentProfile) {
                                // App code

                            }
                        };
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        System.out.println("NOPEs!");
                        System.out.println(exception);
                    }
                });

        TextView txt = (TextView) findViewById(R.id.txtTitle);
        Typeface font = Typeface.createFromAsset(getAssets(), "Generally Speaking.ttf" );
        txt.setTypeface(font);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this);
    }
}
