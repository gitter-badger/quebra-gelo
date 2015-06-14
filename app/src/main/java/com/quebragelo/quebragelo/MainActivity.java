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
import com.quebragelo.quebragelo.helper.Constraint;
import com.quebragelo.quebragelo.task.AddPersonTask;
import com.quebragelo.quebragelo.vo.PersonVO;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class MainActivity extends Activity {

    private CallbackManager callbackManager;
    private ProfileTracker profileTracker;
    private PersonVO person;
    private AddPersonTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);

        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);

        loginButton.setReadPermissions(Arrays.asList("user_status", "user_birthday", "email", "user_about_me"));

        task = new AddPersonTask(this);

        callbackManager = CallbackManager.Factory.create();
        loginButton.registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(final LoginResult loginResult) {
                        person = new PersonVO();

                        profileTracker = new ProfileTracker() {
                            @Override
                            protected void onCurrentProfileChanged(
                                    Profile oldProfile,
                                    Profile currentProfile) {

                                person.setImage(currentProfile.getProfilePictureUri(Constraint.PROFILE_IMAGE_WIDTH, Constraint.PROFILE_IMAGE_HEIGHT).getPath());

                                createPerson(loginResult.getAccessToken());
                            }
                        };
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
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

    public void createPerson(final AccessToken accessToken){
        GraphRequest request = GraphRequest.newMeRequest(accessToken,
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        try {
                            person.setEmail(object.getString("email"));
                            person.setFbAccessToken(accessToken.getToken());
                            person.setName(object.getString("name"));
//                          person.setBio(object.getString("bio"));

                            person.setBirthdayAt(new Date(new SimpleDateFormat("MM/dd/yyyy").parse(object.getString("birthday")).getTime()));

                            task.execute(person);
                        } catch (JSONException e) {
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,link,email,bio,birthday,about");
        request.setParameters(parameters);
        request.executeAsync();
    }

    public void close(){
        setResult(0);
        // abrir ooutra activity
//        finish();
    }
}

