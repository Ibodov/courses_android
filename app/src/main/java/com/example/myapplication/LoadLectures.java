package com.example.myapplication;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LoadLectures extends AsyncTask<Void, Void, Void> {

    public LecturesActivity activity;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        activity.lectures = new ArrayList<>();
    }


    @Override
    protected Void doInBackground(Void... arg0) {
        HTTPHandler sh = new HTTPHandler();
        String jsonStr = sh.makeServiceCall( "https://mncc-android3-courses-backend.k1.cybernet.tj/lectures");

        try {
            JSONArray jsonArray = new JSONArray(jsonStr);
            for (Integer i = 0; i < jsonArray.length(); i++) {
                JSONObject json = jsonArray.getJSONObject(i);
                Lecture lecture = new Lecture();
                lecture.title = json.getString("title");
                lecture.published = json.getString("published");
                activity.lectures.add(lecture);
                Log.e("Lecture " + i.toString(), lecture.title + " " + lecture.published);
            }
        } catch (JSONException error) {
            Log.e("Parsing error", error.getMessage());
        }

        return null;
    }


    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
    }
}
