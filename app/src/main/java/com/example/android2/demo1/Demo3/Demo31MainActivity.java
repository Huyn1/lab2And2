package com.example.android2.demo1.Demo3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;

import com.example.android2.R;

public class Demo31MainActivity extends AppCompatActivity {
Button btn1 , btn2,btn3,btn4;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo31_main);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
// 1 Alert diolog
        btn1.setOnClickListener(v -> {
            //1.1Tao Builder
        });
    }
}