package com.example.quiz;

import static com.example.quiz.QuestionAnswer.choices;
import static com.example.quiz.QuestionAnswer.choices_d;
import static com.example.quiz.QuestionAnswer.choices_gl;
import static com.example.quiz.QuestionAnswer.choices_n;
import static com.example.quiz.QuestionAnswer.choices_prich;
import static com.example.quiz.QuestionAnswer.choices_pril;
import static com.example.quiz.QuestionAnswer.choices_s;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

public class MainStart extends AppCompatActivity implements View.OnClickListener {
    Button btn_back1, btn_all, btn_d, btn_n, btn_s, btn_pril, btn_gl, btn_prich;
    CheckBox checkBox;
    public int is_clicked=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_start);

       btn_all = findViewById(R.id.btn_all);
        btn_back1 = findViewById(R.id.btn_back1);
       btn_n = findViewById(R.id.btn_n);
       btn_d = findViewById(R.id.btn_d);
        btn_s = findViewById(R.id.btn_s);
        btn_pril = findViewById(R.id.btn_pril);

       btn_all.setOnClickListener(this);
       btn_n.setOnClickListener(this);
       btn_d.setOnClickListener(this);
        btn_s.setOnClickListener(this);
        btn_pril.setOnClickListener(this);

        btn_gl = findViewById(R.id.btn_gl);
        btn_gl.setOnClickListener(this);
       //
        btn_prich= findViewById(R.id.btn_prich);
        btn_prich.setOnClickListener(this);
       // CheckBox checkBox = findViewById(R.id.checkBox);
       // checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
          //  @Override
          //  public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
               // if (isChecked) {
                   // is_clicked=1;
               // } else {
                  //  is_clicked=0;
               // }
           // }
       // });

        btn_back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMain2();
            }
        });






    }

  //  @Override


   public void startMainAct(String[][] some_choices){
       Intent intent = new Intent(this, how_many_q.class);
       //передача объекта с ключом "hello" и значением "Hello World"

       //запуск SecondActivitygj
        intent.putExtra("some_choices",some_choices);
       //intent.putExtra("is_clicked",is_clicked);
       startActivity(intent);
   }

    public void startMain2(){
        Intent intent = new Intent(this, MainActivity2.class);

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
        if(part.equals("прил")){
            startMainAct(choices_pril);
        }
        if(part.equals("прил")){
            startMainAct(choices_pril);
        }
        if(part.equals("глагол")){
            startMainAct(choices_gl);
       }
       if(part.equals("прич")){
            startMainAct(choices_prich);
        }



    }
}