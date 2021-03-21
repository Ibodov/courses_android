package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class LecturesActivity extends AppCompatActivity {

    public ArrayList<Lecture> lectures;
    public RecyclerView list;
    Button exit;
    LecturesActivity activity;
    public LecturesDatabase db;
    public String   token;
    public Course course;
    public TextView courseTitleTextView;
    public TextView mentorFioTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lectures);
        db = new LecturesDatabase(this);

        activity = this;
        list = findViewById(R.id.list);
        exit = findViewById(R.id.exit);
        courseTitleTextView = findViewById(R.id.tv_course_title);
        mentorFioTextView = findViewById(R.id.tv_mentor_fio);

        SharedPreferences prefs = getSharedPreferences("courses", 0);

        //Вывод строками
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity.getApplicationContext(), LinearLayoutManager.VERTICAL,false);
        activity.list.setLayoutManager(layoutManager);


        LecturesLoader loader = new LecturesLoader();
        loader.activity = this;
        loader.execute();

        CourseLoader courseLoader = new CourseLoader();
        courseLoader.activity = this;
        courseLoader.execute();

        this.token=prefs.getString("token", "");

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = activity.getSharedPreferences("courses", 0).edit();
                editor.putString("token", "");
                editor.apply();

                Intent intent = new Intent(activity.getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                activity.getApplicationContext().startActivity(intent);
            }
        });
    }
}