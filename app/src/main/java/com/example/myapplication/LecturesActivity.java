package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class LecturesActivity extends AppCompatActivity {

    public ArrayList<Lecture> lectures;
    public RecyclerView list;
    TextView token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lectures);

        list = findViewById(R.id.list);
        token = findViewById(R.id.token);

        SharedPreferences prefs = getSharedPreferences("courses", 0);
        token.setText(prefs.getString("token", ""));


        LoadLectures loader = new LoadLectures();
        loader.activity = this;
        loader.execute();
    }
}