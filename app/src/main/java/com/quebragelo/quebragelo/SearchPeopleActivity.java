package com.quebragelo.quebragelo;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebView;

/**
 * Created by bruno on 14/06/15.
 */
public class SearchPeopleActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.search_people_load);

        WebView webView = (WebView) findViewById(R.id.webView);
        webView.loadUrl("file:///android_asset/loading.html");
    }
}
