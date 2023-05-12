package com.example.quiz;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class how_many_q extends AppCompatActivity {
    Button button2, button3, button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_many_q);
        String[][] some_choices = (String[][]) getIntent().getSerializableExtra("some_choices");
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);


        if (some_choices[0][2].equals("д")){
            button2.setText("5");
            button3.setText("7");
        }

        if (some_choices[0][2].equals("д")){
            button2.setText("5");
            button3.setText("7");
        }


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start_main(some_choices, parseInt(button4.getText().toString()));
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start_main(some_choices, parseInt(button4.getText().toString()));
            }
        });


        button4=findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start_main(some_choices, some_choices.length);
            }
        });
    }

    void start_main(String[][] some_choices, int len){
        Intent intent = new Intent(this, MainActivity.class);
        //передача объекта с ключом "hello" и значением "Hello World"

        //запуск SecondActivitygj
        intent.putExtra("some_choices",some_choices);
        intent.putExtra("len",len);
        //intent.putExtra("is_clicked",is_clicked);
        startActivity(intent);
    }
}