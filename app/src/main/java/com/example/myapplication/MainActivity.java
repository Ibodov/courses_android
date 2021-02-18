package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public EditText login;
    public EditText password;
    public ConstraintLayout error;
    Button button;
    MainActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity = this;
        button = findViewById(R.id.button);
        login = findViewById(R.id.login);
        password = findViewById(R.id.password);
        error = findViewById(R.id.error);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoadToken loader = new LoadToken();
                loader.activity = activity;
                loader.execute();
            }
        });

    }
}