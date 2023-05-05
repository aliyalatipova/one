package com.example.quiz;

import static com.example.quiz.QuestionAnswer.choices;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView totalQuestionsTextView;
    TextView questionTextView;
    Button ansA, ansB;
    Button btn_back;
    String res;

    int canClickFlag = 1;


    int score = 0;

    int totalQuestion = 2;
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

        Collections.shuffle(Arrays.asList(choices));
        //List<String> list = new ArrayList<>();
        questionTextView.setText("***\nМир");


        res = "";
        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        btn_back.setOnClickListener(this);


        loadNewQuestion();

    }



    private void snth_with_db(){    //типа бд
        SQLiteDatabase db = getBaseContext().openOrCreateDatabase("app.db", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS words (true_wr TEXT, false_wr TEXT, part TEXT," +
                " liked_or TEXT, hist TEXT, UNIQUE(name))");
        db.execSQL("INSERT OR IGNORE INTO words VALUES ('закУпорив',  'закупорИв',  'д',  0,  \"\"),\n" +
                "('начАв',  'нАчав',  'д',  0,  \"\"),\n" +
                "('начАвшись',  'нАчавшись',  'д',  0,  \"\"),\n" +
                "('отдАв',  'Отдав',  'д',  0,  \"\"),\n" +
                "('поднЯв',  'пОдняв',  'д',  0,  \"\"),\n" +
                "('понЯв',  'пОняв',  'д',  0,  \"\"),\n" +
                "('прибЫв',  'прИбыв',  'д',  0,  \"\"),\n" +
                "('создАв',  'сОздав',  'д',  0,  \"\"),\n" +
                "('вОвремя',  'вОвремя',  'н',  0,  \"\"),\n" +
                "('дОверху',  'довЕрху',  'н',  0,  \"\"),\n" +
                "('донЕльзя',  'дОнельзя',  'н',  0,  \"\"),\n" +
                "('дОнизу',  'донИзу',  'н',  0,  \"\"),\n" +
                "('дОсуха',  'досУха',  'н',  0,  \"\"),\n" +
                "('зАсветло',  'засветлО',  'н',  0,  \"\"),\n" +
                "('зАтемно',  'затемнО',  'н',  0,  \"\"),\n" +
                "('красИвее',  'красИвее',  'н',  0,  \"\"),\n" +
                "('надОлго',  'надОлго',  'н',  0,  \"\"),\n" +
                "('ненадОлго',  'ненадОлго',  'н',  0,  \"\");");

        Cursor query = db.rawQuery("SELECT * FROM words;", null);
        //TextView textView = findViewById(R.id.textView);
        questionTextView.setText("");
        while(query.moveToNext()){
            String name = query.getString(0);
            String age = query.getString(1);
            //questionTextView.append("Name: " + name + " Age: " + age + "\n");
        }
        query.close();
        db.close();
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

        // как было до этого
        //ansA.setText(choices[currentQuestionIndex][i]);
        //ansB.setText(choices[currentQuestionIndex][(i+1)%2]);
        ansA.setText(choices[currentQuestionIndex][i]);
        ansB.setText(choices[currentQuestionIndex][(i+1)%2]);




    }

    void resQuizKnowAns(){
        //Intent intent = new Intent(this, MainActivity4.class);
        Intent intent = new Intent(this, MainActivityAfterMain.class);
        //передача объекта с ключом "hello" и значением "Hello World"
        String text = "Вы получили " +score + " очков из " + totalQuestion + " возможных";
        intent.putExtra("hello", text);
        intent.putExtra("res", res);
        intent.putExtra("totalQuestion", totalQuestion);
        //запуск SecondActivitygj
        startActivity(intent);



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
                if (clickedButton.getText().equals(choices[currentQuestionIndex][0])) {
                    clickedButton.setBackgroundColor(Color.GREEN);
                    res = res + "+";
                    score++;

                } else {
                    clickedButton.setBackgroundColor(Color.RED);
                    res = res + "-";

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