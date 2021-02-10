package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;

public class LecturesActivity extends AppCompatActivity {

    public ArrayList<Lecture> lectures;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lectures);

        LoadLectures loader = new LoadLectures();
        loader.activity = this;
        loader.execute();
    }
}