package com.quebragelo.quebragelo;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
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

//            ImageView imageView = (ImageView) findViewById(R.id.profile_image);
//
//            Bitmap resized = Bitmap.createScaledBitmap(image, 400, 400, true);
//            Bitmap rounded = getRoundedRectBitmap(resized, 300);

            RoundedImageView imageView = (RoundedImageView) findViewById(R.id.profile_image);
            Bitmap image = new ImageDownloaderTask().execute(currentUser.getImageLink(Constraint.PROFILE_IMAGE_LARGE)).get();

            imageView.setImageBitmap(image);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
//
//    public static Bitmap getRoundedRectBitmap(Bitmap bitmap, int pixels) {
//        Bitmap result = null;
//        try {
//            result = Bitmap.createBitmap(200, 200, Bitmap.Config.ARGB_8888);
//            Canvas canvas = new Canvas(result);
//
//            int color = 0xff424242;
//            Paint paint = new Paint();
//            Rect rect = new Rect(0, 0, 200, 200);
//
//            paint.setAntiAlias(true);
//            canvas.drawARGB(0, 0, 0, 0);
//            paint.setColor(color);
//            canvas.drawCircle(pixels, pixels, pixels, paint);
//            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
//            canvas.drawBitmap(bitmap, rect, rect, paint);
//
//        } catch (Exception e) { }
//        return result;
//    }
}
