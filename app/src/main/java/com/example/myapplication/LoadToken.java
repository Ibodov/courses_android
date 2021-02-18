package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LoadToken extends AsyncTask<Void, Void, Void> {

    public MainActivity activity;
    String jsonStr;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    @Override
    protected Void doInBackground(Void... arg0) {
        HTTPHandler sh = new HTTPHandler();
        jsonStr = sh.makeServiceCall( "https://mncc-android3-courses-backend.k1.cybernet.tj/auth?login=" + activity.login.getText() + "&password=" + activity.password.getText());

        return null;
    }


    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);

        if (jsonStr.equals("400\n")) {
            activity.error.setVisibility(View.VISIBLE);
        } else {
            SharedPreferences.Editor editor = activity.getSharedPreferences("courses", 0).edit();
            editor.putString("token", jsonStr);
            editor.apply();

            Intent intent = new Intent(activity.getApplicationContext(), LecturesActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            activity.getApplicationContext().startActivity(intent);

        }
    }
}
