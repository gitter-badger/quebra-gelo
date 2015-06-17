package com.quebragelo.quebragelo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
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

    private RoundedImageView imageView;

    private EditText editBirthday;
    private TextView viewBirthday;
    private ViewSwitcher birthdaySwitcher;

    private EditText editName;
    private TextView viewName;
    private ViewSwitcher nameSwitcher;

    private EditText editBio;
    private TextView viewBio;
    private ViewSwitcher bioSwitcher;

    private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            FacebookSdk.sdkInitialize(getApplicationContext());
            setContentView(R.layout.activity_profile);

            currentUser = new LoadPersonTask(this).execute().get();

            initializeElements();
            turnRedButton();
            loadUserInfos();
        } catch (Exception e) {
           Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void initializeElements(){
        imageView = (RoundedImageView) findViewById(R.id.profile_image);

        actionButton = (ActionButton) findViewById(R.id.action_button);

        editBirthday = (EditText) findViewById(R.id.hidden_edit_birthday);
        viewBirthday = (TextView) findViewById(R.id.label_birthday);
        birthdaySwitcher = (ViewSwitcher) findViewById(R.id.birthday_switcher);

        editName = (EditText) findViewById(R.id.hidden_edit_name);
        viewName = (TextView) findViewById(R.id.label_name);
        nameSwitcher = (ViewSwitcher) findViewById(R.id.name_switcher);

        editBio = (EditText) findViewById(R.id.hidden_edit_bio);
        viewBio = (TextView) findViewById(R.id.label_bio);
        bioSwitcher = (ViewSwitcher) findViewById(R.id.bio_switcher);
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
            Bitmap image = new ImageDownloaderTask().execute(currentUser.getImageLink(Constraint.PROFILE_IMAGE_LARGE)).get();
            imageView.setImageBitmap(image);

            viewName.setText(currentUser.getName());
            editName.setText(currentUser.getName());

            viewBio.setText(currentUser.getBio());
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
            person.setBio(editBio.getText().toString());
            person.setName(editName.getText().toString());

            new UpdatePersonTask().execute(person);

            turnRedButton();
            turnShowableView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void turnShowableView(){
        birthdaySwitcher.showPrevious();
        bioSwitcher.showPrevious();
        nameSwitcher.showPrevious();
    }

    public void turnEditableView(){
        birthdaySwitcher.showNext();
        bioSwitcher.showNext();
        nameSwitcher.showNext();
    }
}
