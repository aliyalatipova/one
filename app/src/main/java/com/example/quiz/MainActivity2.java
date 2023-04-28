package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity {
    Button learn_btn;
    Button test_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        learn_btn = findViewById(R.id.learn_btn);
        test_btn = findViewById(R.id.test_btn);
        learn_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAct1();
            }
        });

        test_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openAct3();
            }
        });

    }

    public void openAct1(){

        learn_btn.setText(""); //перепутала названия, постаралась:)))))
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void openAct3(){
        test_btn.setText(""); //надпись тест открывает learn activity, и learn activity это как тест
        Intent intent = new Intent(this, Activity_learn.class);
        startActivity(intent);
    }



}