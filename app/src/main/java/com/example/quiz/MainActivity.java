package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView totalQuestionsTextView;
    TextView questionTextView;
    Button ansA, ansB;
    Button btn_back;

    int canClickFlag = 1;
    String next = "next";

    int score = 0;
    int totalQuestion = 6;
    int currentQuestionIndex = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        totalQuestionsTextView = findViewById(R.id.total_question);
        questionTextView = findViewById(R.id.question);
        btn_back = findViewById(R.id.btn_back);
        ansA = findViewById(R.id.ans_A);
        ansB = findViewById(R.id.ans_B);


        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);

        btn_back.setOnClickListener(this);

        //totalQuestionsTextView.setText("Total questions" + totalQuestion);

        loadNewQuestion();

    }
    private void loadNewQuestion() {
        if (currentQuestionIndex == totalQuestion){
            resQuizKnowAns();
            return;
        }

        canClickFlag = 1;
        totalQuestionsTextView.setText("Номер вопроса: " +(currentQuestionIndex + 1));
        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        int diff = 2;
        Random random = new Random();
        int i = random.nextInt(diff);

        ansA.setText(QuestionAnswer.choices[currentQuestionIndex][i]);
        ansB.setText(QuestionAnswer.choices[currentQuestionIndex][(i+1)%2]);




    }

    void resQuizKnowAns(){
        Intent intent = new Intent(this, MainActivity4.class);
        // передача объекта с ключом "hello" и значением "Hello World"
        String text = "Вы получили " +score + " очков из " + totalQuestion + " возможных";
        intent.putExtra("hello", text);
        //запуск SecondActivitygj
        startActivity(intent);

    }

    void restartQuiz(){
        score = 0;
        currentQuestionIndex = 0;
        loadNewQuestion();
    }

    @Override
    public void onClick(View view) {

        Button clickedButton = (Button) view;
        //String selectedAnswer = clickedButton.getText().toString();
        if (clickedButton.getId() == R.id.btn_back){    // если нажата кнопка btnback то переход на mainactivity2
            btn_back.setText("");
            Intent intent = new Intent(this, MainActivity2.class);
            startActivity(intent);
        }
        else {
            if (canClickFlag == 1) {
                canClickFlag = 0;
                if (clickedButton.getText().equals(QuestionAnswer.choices[currentQuestionIndex][0])) {
                    clickedButton.setBackgroundColor(Color.GREEN);
                    score++;
                } else {
                    clickedButton.setBackgroundColor(Color.RED);
                }
                currentQuestionIndex++;

                new Thread(() -> {

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    loadNewQuestion();
                }).start();
            }
        }


        }
    }