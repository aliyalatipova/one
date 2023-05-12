package com.example.quiz;

//import static com.example.quiz.QuestionAnswer.choicesall;

//import static com.example.quiz.QuestionAnswer.choices;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity  {

    TextView totalQuestionsTextView;
    TextView questionTextView;
    Button ansA, ansB;
    Button btn_back;
    String res;
    private TextToSpeech textToSpeech;
    private MediaPlayer zasvetloSound;
    public String[][] some_choices;


    int canClickFlag = 1;
    int score = 0;
    public int totalQuestion = 5;
    int currentQuestionIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        totalQuestionsTextView = findViewById(R.id.total_question);
        questionTextView = findViewById(R.id.question);

        ansA = findViewById(R.id.ans_A);
        ansB = findViewById(R.id.ans_B);

        String[][] some_choices = (String[][]) getIntent().getSerializableExtra("some_choices");
        Bundle arguments = getIntent().getExtras();
        //int len = parseInt(arguments.get("hello").toString());
        //totalQuestion = some_choices.length;

        totalQuestion = parseInt(arguments.get("len").toString());
        if (totalQuestion >some_choices.length){
            totalQuestion=some_choices.length;
        }

        Collections.shuffle(Arrays.asList(some_choices));
        questionTextView.setText("");

        textToSpeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = textToSpeech.setLanguage(new Locale("ru"));
                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported");
                    }
                } else {
                    Log.e("TTS", "Initialization failed");
                }
            }
        });


        res = "";
        ansA.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                a_clicked(some_choices);
            }
        });
        ansB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b_clicked(some_choices);
            }
        });
        // btn_back.setOnClickListener(this);
        loadNewQuestion(some_choices);

    }

    public void a_clicked(String[][] some_choices){

        if (some_choices[currentQuestionIndex][0].equals("зАсветло")) {
            zasvetloSound = MediaPlayer.create(this, R.raw.zasvetloo);
            soundPlat(zasvetloSound);
        }else{
            textToSpeech.speak(some_choices[currentQuestionIndex][0], TextToSpeech.QUEUE_ADD, null, null);
        }

        if (canClickFlag == 1) {
            canClickFlag = 0;
            if (ansA.getText().equals(some_choices[currentQuestionIndex][0])) {
                ansA.setBackgroundColor(Color.GREEN);
                res = res + "+";
                score++;
            } else {
                ansA.setBackgroundColor(Color.RED);
                res = res + "-";
            }
            currentQuestionIndex++;
            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                loadNewQuestion(some_choices);
            }).start();
        }
    }

    public void b_clicked(String[][] some_choices){

        //  ansA.setBackgroundColor(Color.MAGENTA);
        if (some_choices[currentQuestionIndex][0].equals("зАсветло")) {
            zasvetloSound = MediaPlayer.create(this, R.raw.zasvetloo);
            soundPlat(zasvetloSound);
        }else{
            textToSpeech.speak(some_choices[currentQuestionIndex][0], TextToSpeech.QUEUE_ADD, null, null);
        }
        if (canClickFlag == 1) {
            canClickFlag = 0;
            if (ansB.getText().equals(some_choices[currentQuestionIndex][0])) {
                ansB.setBackgroundColor(Color.GREEN);
                res = res + "+";
                score++;
            } else {
                ansB.setBackgroundColor(Color.RED);
                res = res + "-";
            }
            currentQuestionIndex++;
            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                loadNewQuestion(some_choices);
            }).start();
        }
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

    private void loadNewQuestion(String[][] some_choices) {
        if (currentQuestionIndex == totalQuestion){
            //questionTextView.setText("-");
            resQuizKnowAns(some_choices);
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

        //ansB.setText(choices[currentQuestionIndex][(i+1)%2]);
        ansA.setText(some_choices[currentQuestionIndex][i]);
        ansB.setText(some_choices[currentQuestionIndex][(i+1)%2]);




    }

    void resQuizKnowAns(String[][] some_choices){
        //Intent intent = new Intent(this, MainActivity4.class);
        Intent intent = new Intent(this, MainActivityAfterMain.class);
        //передача объекта с ключом "hello" и значением "Hello World"
        String text = "Вы получили " +score + " из " + totalQuestion + " возможных очков";
        intent.putExtra("hello", text);
        intent.putExtra("res", res);
        //intent.putExtra("totalQuestion", totalQuestion);
        intent.putExtra("some_choices", some_choices);
        //запуск SecondActivitygj
        startActivity(intent);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }

    public void soundPlat(MediaPlayer sound){
        sound.start();

    }






    //j@Override
    public void onClick(View view, String[][] some_choices) {
        textToSpeech.speak(some_choices[currentQuestionIndex][0], TextToSpeech.QUEUE_ADD, null, null);

        questionTextView.setText("--");
        Button clickedButton = (Button) view;
        //String selectedAnswer = clickedButton.getText().toString();
        //if (clickedButton.getId() == R.id.btn_back){    // если нажата кнопка btnback то переход на mainactivity2
        //btn_back.setText("");
        //Intent intent = new Intent(this, MainActivity2.class);
        // startActivity(intent);
        // }

        if (some_choices[currentQuestionIndex][0].equals("зАсветло")) {
            zasvetloSound = MediaPlayer.create(this, R.raw.zasvetloo);
            soundPlat(zasvetloSound);


        } else {
            // questionTextView.setText(choices[currentQuestionIndex][0]);
            textToSpeech.speak(some_choices[currentQuestionIndex][0], TextToSpeech.QUEUE_ADD, null, null);
        }
        if (canClickFlag == 1) {
            canClickFlag = 0;
            if (clickedButton.getText().equals(some_choices[currentQuestionIndex][0])) {
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
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                loadNewQuestion(some_choices);
            }).start();
        }


    }


}