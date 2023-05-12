package com.example.quiz;

import static com.example.quiz.QuestionAnswer.choices;
import static java.lang.Math.min;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Locale;

public class MainActivity3 extends AppCompatActivity {
    TextView textView3;
    LinearLayout layout_test;
    FloatingActionButton flbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        textView3=findViewById(R.id.textView3);
        flbtn=findViewById(R.id.floatingActionButton);
        layout_test = findViewById(R.id.layout_test);

        Bundle arguments = getIntent().getExtras();
        String text = arguments.get("hello").toString();
        String res = arguments.get("res").toString();
        textView3.setText(text);

        flbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActLearn();
            }
        });
        create_btn_s(res);
    }

    public void openActLearn(){
        Intent intent = new Intent(this, Activity_learn.class);
        startActivity(intent);
    }


    public int find_false_bid_index(int i, String[][]choices){ //я тупо скопировала текст с find_true_bid_index ;)
        String word_true = choices[i][1];
        for (int j = 0; j < word_true.length(); j++) {
            char sim = word_true.charAt(j);
            String sim_of_res = String.valueOf(sim);
            if (sim_of_res.equals(sim_of_res.toLowerCase(Locale.ROOT))){
                continue;
            }
            else{
                return j;
            }
        }
        return -1;
    }

    public int find_true_bid_index(int i, String[][]some_choices){
        String word_true = some_choices[i][0];
        for (int j = 0; j < word_true.length(); j++) {
            char sim = word_true.charAt(j);
            String sim_of_res = String.valueOf(sim);
            if (sim_of_res.equals(sim_of_res.toLowerCase(Locale.ROOT))){
                continue;
            }
            else{
                return j;
            }
        }
        return -1;
    }

    public void create_btn_s(String res){
        int list_len =res.length();


        Button[] btn = new Button[list_len+1];
        for (int i = 0; i < list_len; ++i) {
            btn[i] = new Button(this);
            btn[i].setAllCaps(false);
            btn[i].setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            // btn[i].setText(some_choices[i][0]);
            btn[i].setId(i);
            int index_of_true_letter = find_true_bid_index(i, choices);
            String true_wr_txt = choices[i][0];


            SpannableStringBuilder builder = new SpannableStringBuilder();

// Добавление зеленой части текста в builder

            char c = res.charAt(i);
            String sim_of_res = String.valueOf(c);
            if (sim_of_res.equals("-")){  //типа если у нас неверноор
                int index_of_false_letter = find_false_bid_index(i, choices);
                SpannableString redSpannable = new SpannableString(true_wr_txt);
                redSpannable.setSpan(new ForegroundColorSpan(Color.GREEN), index_of_true_letter, index_of_true_letter+1, 0);
                redSpannable.setSpan(new ForegroundColorSpan(Color.RED), index_of_false_letter, index_of_false_letter+1, 0);
                builder.append(redSpannable);
            }else{
                SpannableString greenSpannable = new SpannableString(true_wr_txt);
                greenSpannable.setSpan(new ForegroundColorSpan(Color.GREEN), index_of_true_letter, index_of_true_letter+1, 0);
                builder.append(greenSpannable);
            }


            btn[i].setText(builder);

            // слой, к которому кнопку хотите прикрепить
            layout_test.addView(btn[i]);
        }



    }
}