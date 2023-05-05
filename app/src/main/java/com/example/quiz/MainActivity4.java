package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity4 extends AppCompatActivity {
    TextView textView6, textViewRes;
    Button button2, button3;
    LinearLayout layout;
    LinearLayout layout2;

    FloatingActionButton flbtn2;
    int totalQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);


        button3 = findViewById(R.id.button3);
        textView6 = findViewById(R.id.textView6);
        textViewRes = findViewById(R.id.textViewRes);
        LinearLayout layout = (LinearLayout) findViewById(R.id.linear_layout);




        Bundle arguments = getIntent().getExtras();
        String text = arguments.get("hello").toString();
        String textRes = arguments.get("res").toString();

        int totalQuestion = Integer.parseInt(arguments.get("totalQuestion").toString());
        textViewRes.setText(textRes);




        for (int i = 0; i < totalQuestion; i++) {
            char c = textRes.charAt(i);
            if (String.valueOf(c).equals("+")){
                String aq = textViewRes.getText().toString() + "/n " + QuestionAnswer.choices[i][0];
            }
           // textViewRes.setText(aq);

            }


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