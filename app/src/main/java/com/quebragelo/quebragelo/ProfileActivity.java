package com.quebragelo.quebragelo;

import android.app.Activity;
import android.graphics.*;
import android.os.Bundle;
import android.widget.ImageView;
import com.facebook.FacebookSdk;
import com.quebragelo.quebragelo.helper.Constraint;
import com.quebragelo.quebragelo.task.ImageDownloaderTask;
import com.quebragelo.quebragelo.task.LoadPersonTask;
import com.quebragelo.quebragelo.vo.PersonVO;

/**
 * Created by bruno on 16/06/15.
 */
public class ProfileActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            FacebookSdk.sdkInitialize(getApplicationContext());
            setContentView(R.layout.activity_profile);

            LoadPersonTask task = new LoadPersonTask(this);
            PersonVO currentUser = task.execute().get();

            ImageView imageView = (ImageView) findViewById(R.id.profile_image);
            Bitmap image = new ImageDownloaderTask().execute(currentUser.getImageLink(Constraint.PROFILE_IMAGE_LARGE)).get();

            imageView.setImageBitmap(image);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
