package com.quebragelo.quebragelo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import com.quebragelo.quebragelo.view.GifView;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by bruno on 14/06/15.
 */
public class SearchPeopleActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.search_people_load);

        try {
            InputStream stream = getAssets().open("loading.gif");

            GifView view = new GifView(this, stream);
            setContentView(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
