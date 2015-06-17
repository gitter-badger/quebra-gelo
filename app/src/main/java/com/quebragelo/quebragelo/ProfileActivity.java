package com.quebragelo.quebragelo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ViewSwitcher;
import com.facebook.FacebookSdk;
import com.quebragelo.quebragelo.helper.Constraint;
import com.quebragelo.quebragelo.task.ImageDownloaderTask;
import com.quebragelo.quebragelo.task.LoadPersonTask;
import com.quebragelo.quebragelo.vo.PersonVO;
import com.software.shell.fab.ActionButton;

import java.text.SimpleDateFormat;

/**
 * Created by bruno on 16/06/15.
 */
public class ProfileActivity extends Activity {
    private PersonVO currentUser;
    private ActionButton actionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            FacebookSdk.sdkInitialize(getApplicationContext());
            setContentView(R.layout.activity_profile);

            LoadPersonTask task = new LoadPersonTask(this);
            currentUser = task.execute().get();

            actionButton = (ActionButton) findViewById(R.id.action_button);
            actionButton.hide();

            loadUserInfos();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void loadUserInfos(){
        try{
            RoundedImageView imageView = (RoundedImageView) findViewById(R.id.profile_image);
            Bitmap image = new ImageDownloaderTask().execute(currentUser.getImageLink(Constraint.PROFILE_IMAGE_LARGE)).get();

            imageView.setImageBitmap(image);

            TextView labelName = (TextView) findViewById(R.id.label_name);
            labelName.setText(currentUser.getName());

            TextView editBio = (TextView) findViewById(R.id.edit_bio);
            editBio.setText(currentUser.getBio());

            TextView editBirthday = (TextView) findViewById(R.id.label_birthday);
            SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
            editBirthday.setText(dt.format(currentUser.getBirthdayAt()));

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void birthdayViewClicked(View view) {
        ViewSwitcher switcher = (ViewSwitcher) findViewById(R.id.birthday_switcher);
        switcher.showNext(); //or switcher.showPrevious();
        TextView myTV = (TextView) switcher.findViewById(R.id.label_birthday);
        myTV.setText("value");

        TextView editBirthday = (TextView) findViewById(R.id.hidden_edit_birthday);
        SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyyy");
        editBirthday.setText(dt.format(currentUser.getBirthdayAt()));

        this.actionButton.show();
    }
}
