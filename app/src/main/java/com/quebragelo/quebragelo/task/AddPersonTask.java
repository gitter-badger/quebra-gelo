package com.quebragelo.quebragelo.task;

import android.os.AsyncTask;
import com.google.gson.Gson;
import com.quebragelo.quebragelo.MainActivity;
import com.quebragelo.quebragelo.helper.URLMaker;
import com.quebragelo.quebragelo.vo.PersonVO;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by bruno on 11/06/15.
 */
public class AddPersonTask extends AsyncTask<PersonVO, Void, Void> {

    private MainActivity activity;
//
//    public AddPersonTask(MainActivity activity){
//        this.activity = activity;
//    }

    @Override
    protected Void doInBackground(PersonVO... params) {

        try {
            PersonVO person = params[0];
            String msg = new Gson().toJson(person);
            URL url = URLMaker.create(PersonVO.API_PATH);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setReadTimeout(10000);
            con.setConnectTimeout(15000);
            con.setRequestMethod("POST");

            con.setDoInput(false);
            con.setDoOutput(true);

            con.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            con.setRequestProperty("X-Request-With", "XMLHttpRequest");

            con.setFixedLengthStreamingMode(msg.getBytes().length);

            OutputStream os = new BufferedOutputStream(con.getOutputStream());
            os.write(msg.getBytes());
            os.flush();

            os.close();
            con.disconnect();

            publishProgress();
        }catch (Exception e) {

        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
//        this.activity.finalizar();
    }
}
