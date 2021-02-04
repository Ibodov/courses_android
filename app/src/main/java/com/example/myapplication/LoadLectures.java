package com.example.myapplication;

import android.os.AsyncTask;
import android.util.Log;

public class LoadLectures extends AsyncTask<Void, Void, Void> {

    @Override

    protected Void doInBackground(Void... arg0) {
        HTTPHandler sh = new HTTPHandler();

        String jsonStr = sh.makeServiceCall( "https://mncc-android3-courses-backend.k1.cybernet.tj/lectures");

        Log.e("HTTP", jsonStr);
        return null;
    }
}
