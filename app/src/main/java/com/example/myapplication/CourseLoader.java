package com.example.myapplication;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CourseLoader extends AsyncTask<Void, Void, Void> {

    public LecturesActivity activity;
    String jsonStr;
    public Course course;

    @Override
    protected Void doInBackground(Void... arg0) {
        HTTPHandler sh = new HTTPHandler();
        jsonStr = sh.makeServiceCall( "http://192.168.88.239:5000/course?token="+activity.token);
        if (jsonStr == null) {
            jsonStr = "";
        }

        try {
            JSONArray jsonArray = new JSONArray(jsonStr);
//            todo delete courses in db
//            activity.db.wipeLectures();
            for (Integer i = 0; i < jsonArray.length(); i++) {
                JSONObject json = jsonArray.getJSONObject(i);

                Course c = new Course();
                c.setTitle(json.getString("title"));
                c.setFio(json.getString("fio"));
                activity.course = c;
//
//                activity.db.insertLecture(lecture.title, lecture.published, lecture.photo, lecture.video, lecture.shownotes);
//                activity.lectures.add(lecture);
//                Log.e("Lecture " + i.toString(), lecture.title + " " + lecture.published);
            }
        } catch (JSONException error) {
            Log.e("Parsing error", error.getMessage());
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);

        activity.courseTitleTextView.setText(activity.course.getTitle());
        activity.mentorFioTextView.setText(activity.course.getFio());
    }
}