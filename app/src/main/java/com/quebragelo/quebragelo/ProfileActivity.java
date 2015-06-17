package com.quebragelo.quebragelo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ViewSwitcher;
import com.facebook.FacebookSdk;
import com.quebragelo.quebragelo.helper.Constraint;
import com.quebragelo.quebragelo.task.ImageDownloaderTask;
import com.quebragelo.quebragelo.task.LoadPersonTask;
import com.quebragelo.quebragelo.task.UpdatePersonTask;
import com.quebragelo.quebragelo.vo.PersonVO;
import com.software.shell.fab.ActionButton;

import java.text.SimpleDateFormat;

/**
 * Created by bruno on 16/06/15.
 */
public class ProfileActivity extends Activity {
    private PersonVO currentUser;
    private ActionButton actionButton;

    private EditText editBirthday;
    private TextView viewBirthday;
    private SimpleDateFormat format;
    private ViewSwitcher birthdaySwitcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_profile);

        format = new SimpleDateFormat("dd/MM/yyyy");

        actionButton = (ActionButton) findViewById(R.id.action_button);
        editBirthday = (EditText) findViewById(R.id.hidden_edit_birthday);
        viewBirthday = (TextView) findViewById(R.id.label_birthday);
        birthdaySwitcher = (ViewSwitcher) findViewById(R.id.birthday_switcher);

        turnRedButton();
        loadUserInfos();
        loadPerson();
    }

    public void loadPerson(){
        try{
            this.currentUser = new LoadPersonTask(this).execute().get();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void turnRedButton(){
        actionButton.setButtonColor(getResources().getColor(R.color.fab_material_red_500));
        actionButton.setButtonColorPressed(getResources().getColor(R.color.fab_material_red_900));
        actionButton.setImageResource(R.drawable.ic_mode_edit_white_18dp);

        if (actionButton.getButtonColor() != getResources().getColor(R.color.fab_material_red_500))
            turnShowableView();
    }

    public void turnGreenButton(View view){
        actionButton.setButtonColor(getResources().getColor(R.color.fab_material_green_500));
        actionButton.setButtonColorPressed(getResources().getColor(R.color.fab_material_green_900));
        actionButton.setImageResource(R.drawable.ic_done_white_18dp);

        turnEditableView();
        loadUserInfos();

        actionButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        updateInfos(view);
                    }
                });
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

            editBirthday.setText(format.format(currentUser.getBirthdayAt()));
            viewBirthday.setText(format.format(currentUser.getBirthdayAt()));

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void updateInfos(View view){
        try {
            PersonVO person = new PersonVO();
            person.setApiId(currentUser.getApiId());

            person.setBirthdayAt(format.parse(editBirthday.getText().toString()));

            new UpdatePersonTask().execute(person);

            loadPerson();
            turnRedButton();
            turnShowableView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void turnShowableView(){
        birthdaySwitcher.showPrevious();
    }

    public void turnEditableView(){
        birthdaySwitcher.showNext();
    }
}
