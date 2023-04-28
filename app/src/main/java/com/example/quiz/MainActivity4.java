package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity4 extends AppCompatActivity {
    TextView textView6;
    Button button2, button3;
    FloatingActionButton flbtn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);



        button3 = findViewById(R.id.button3);
        textView6 = findViewById(R.id.textView6);


        //button2.setTextColor(Color.BLACK);

        Bundle arguments = getIntent().getExtras();
        String text = arguments.get("hello").toString();

        textView6.setText(text);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActLearn();
            }
        });
    }




    public void openActLearn(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}