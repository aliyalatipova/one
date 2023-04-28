package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class Activity_learn extends AppCompatActivity implements View.OnClickListener {
    TextView textView;
    TextView textView2;
    Button ansA, ansB;
    Button btn_back;
    Button submitBtn;
    int score = 0;
    int scoreInOneQ = 0; //тут типа количесво очков за один вопросикк
    int currentQuestionIndex=-1;
    int totalQuestion=0;
    String selected_ans_str = ""; // последний типа выбранный стринг
    int canNextQ = 0; //типа флаг нажата ли какая-то кнопка из вариков чтобы можно было двигаться дальше
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);

        btn_back = findViewById(R.id.button);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);

        ansA = findViewById(R.id.ansA);
        ansB = findViewById(R.id.ansB);
        submitBtn = findViewById(R.id.submitBtn);


        ansA.setOnClickListener(this);
        ansB.setOnClickListener((View.OnClickListener) this);
        submitBtn.setOnClickListener(this);
        btn_back.setOnClickListener(this);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_back.setText("");
                openAct2();
            }
        });


    }

    public void openAct2(){
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }

    public void openAct4(){
        Intent intent = new Intent(this, MainActivity4.class);
        startActivity(intent);
    }



    //@Override
    public void onClick(View view) {
        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        Button clickedButton = (Button) view;
        String selectedAnswer = clickedButton.getText().toString();
        if (currentQuestionIndex == -1) {
            if (clickedButton == ansA){
                totalQuestion=5;
                ansA.setBackgroundColor(Color.MAGENTA);
                ansB.setBackgroundColor(Color.WHITE);
            }
            if (clickedButton == ansB){
                totalQuestion=7;
                ansB.setBackgroundColor(Color.MAGENTA);
                ansA.setBackgroundColor(Color.WHITE);
            }
            if (clickedButton == submitBtn){
                if (totalQuestion!=0) {
                    currentQuestionIndex = 0;
                    submitBtn.setText("->");
                    textView.setText("");
                    textView2.setText("1/ "+totalQuestion);
                    loadNewQuestion();
                }
            }
        }
        else{
            if (clickedButton == ansA){
                ansA.setBackgroundColor(Color.MAGENTA);
                //selected_ans_str = ansA.getText().toString();
                canNextQ = 1; //то что типа можем двигаться к след вопросику типа флажка
                if (selectedAnswer.equals(QuestionAnswer.choices[currentQuestionIndex][0])){
                    scoreInOneQ = 1;
                }
                else{
                    scoreInOneQ=0;
                }
            }
            if (clickedButton == ansB){
                ansB.setBackgroundColor(Color.MAGENTA);
                //selected_ans_str = ansB.getText().toString();
                canNextQ = 1;
                if (ansB.getText().toString().equals(QuestionAnswer.choices[currentQuestionIndex][0])){
                    scoreInOneQ=1;
                }
                else{
                    scoreInOneQ=0;
                }
            }
            if (clickedButton == submitBtn){
                score = score + scoreInOneQ;
                //textView.setText(scoreInOneQ+" "+score);
                //if (canNextQ == 1){
                    //canNextQ = 0;
                    //submitBtn.setText(score);
                currentQuestionIndex ++;

                    //if (selected_ans_str.equals(QuestionAnswer.choices[currentQuestionIndex][0])){
                        //score++;
                    //}
                loadNewQuestion();
                    }

                }
            }
   // }



    private void loadNewQuestion() {
        if (currentQuestionIndex !=0) {
            if (currentQuestionIndex >= totalQuestion) {
                //submitBtn.setText("");
                String text = "Вы получили "+score + " баллов из " + totalQuestion;
                Intent intent = new Intent(this, MainActivity3.class);
                intent.putExtra("hello", text);
                startActivity(intent);
                return;
            }
        }
        textView2.setText((currentQuestionIndex + 1) + "/"+totalQuestion);
        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        int diff = 2;
        Random random = new Random();
        int i = random.nextInt(diff);

        ansA.setText(QuestionAnswer.choices[currentQuestionIndex][i]);
        ansB.setText(QuestionAnswer.choices[currentQuestionIndex][(i+1)%2]);



    }


}

