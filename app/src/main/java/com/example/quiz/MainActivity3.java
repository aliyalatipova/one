package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity3 extends AppCompatActivity {
    TextView textView3;
    FloatingActionButton flbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        textView3=findViewById(R.id.textView3);
        flbtn=findViewById(R.id.floatingActionButton);

        Bundle arguments = getIntent().getExtras();
        String text = arguments.get("hello").toString();
        textView3.setText(text);

        flbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActLearn();
            }
        });
    }

    public void openActLearn(){
        Intent intent = new Intent(this, Activity_learn.class);
        startActivity(intent);
    }
}