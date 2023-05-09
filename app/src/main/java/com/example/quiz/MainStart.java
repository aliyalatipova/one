package com.example.quiz;

import static com.example.quiz.QuestionAnswer.choices;
import static com.example.quiz.QuestionAnswer.choices_d;
import static com.example.quiz.QuestionAnswer.choices_n;
import static com.example.quiz.QuestionAnswer.choices_s;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainStart extends AppCompatActivity implements View.OnClickListener {
    Button btn_all, btn_d, btn_n, btn_s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_start);

       btn_all = findViewById(R.id.btn_all);
       btn_n = findViewById(R.id.btn_n);
       btn_d = findViewById(R.id.btn_d);
        btn_s = findViewById(R.id.btn_s);
       btn_all.setOnClickListener(this);
       btn_n.setOnClickListener(this);
       btn_d.setOnClickListener(this);
        btn_s.setOnClickListener(this);




    }

  //  @Override


   public void startMainAct(String[][] some_choices){
       Intent intent = new Intent(this, MainActivity.class);
       //передача объекта с ключом "hello" и значением "Hello World"

       //запуск SecondActivitygj
        intent.putExtra("some_choices",some_choices);
       startActivity(intent);
   }

    @Override
    public void onClick(View view) {
        Button clickedButton = (Button) view;
        String part = clickedButton.getText().toString();
        if (part.equals("н")){
            startMainAct(choices_n);
        }

        if(part.equals("микс")){
            startMainAct(choices);
        }
        if(part.equals("д")){
            startMainAct(choices_d);
        }
        if(part.equals("сущ")){
            startMainAct(choices_s);
        }



    }
}