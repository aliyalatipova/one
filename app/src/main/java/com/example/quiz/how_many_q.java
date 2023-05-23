package com.example.quiz;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class how_many_q extends AppCompatActivity {
    Button button2, button3, btn_all_n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_many_q);
        String[][] some_choices = (String[][]) getIntent().getSerializableExtra("some_choices");
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        btn_all_n=findViewById(R.id.btn_all_n);

        if (some_choices[0][2].equals("гл")){
            button3.setText("10");
            btn_all_n.setText("15");
        }
        if (some_choices[0][2].equals("с")){
            button3.setText("10");
            btn_all_n.setText("15");
        }
        if (some_choices[0][2].equals("прич")){
            button3.setText("10");
            btn_all_n.setText("15");
        }






        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start_main(some_choices, parseInt(button2.getText().toString()));
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start_main(some_choices, parseInt(button3.getText().toString()));
            }
        });



        btn_all_n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start_main(some_choices, parseInt(btn_all_n.getText().toString()));
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