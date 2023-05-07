package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Locale;

public class MainActivityAfterMain extends AppCompatActivity {
    public LinearLayout layout_for_txt;
    public TextView textView_with_res; // в xml это textView4
    //String text;
    public int totalQ=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_after_main);

        layout_for_txt = findViewById(R.id.layout_for_txt);
        textView_with_res = findViewById(R.id.textView4);


        Bundle arguments = getIntent().getExtras();
        String res = arguments.get("res").toString();

        //Spannable spans = new SpannableString(arguments.get("hello").toString()); //текстик
        //spans.setSpan(new ForegroundColorSpan(Color.GREEN), 1, 6, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        //textView_with_res.setText(spans);
        textView_with_res.setText(arguments.get("hello").toString());

        //String res = arguments.get("res").toString();
        createTextView(res);




    }






    public void createTextView(String res){
        totalQ = res.length(); //длина стоски из плюсов и минусов

        TextView[] txt = new TextView[totalQ];
        for (int i = 0; i < totalQ; ++i) {
            char c = res.charAt(i);
            String sim_of_res = String.valueOf(c);


            txt[i] = new TextView(this);
            txt[i].setTextSize(20);
            txt[i].setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            //txt[i].setText(QuestionAnswer.choices[i][0]);
            int index_true_big = find_true_bid_index(i);
            if (sim_of_res.equals("+")) {
                Spannable spans = new SpannableString(QuestionAnswer.choices[i][0]);

                spans.setSpan(new ForegroundColorSpan(Color.GREEN), index_true_big, index_true_big + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                txt[i].setText(spans);
            }else{
                Spannable spans = new SpannableString(QuestionAnswer.choices[i][0]);
                int index_falsee_big = find_false_bid_index(i);
                spans.setSpan(new ForegroundColorSpan(Color.GREEN), index_true_big, index_true_big+1, 0);
                spans.setSpan(new ForegroundColorSpan(Color.RED), index_falsee_big, index_falsee_big+1, 0);
                txt[i].setText(spans, TextView.BufferType.SPANNABLE);
                //spans.setSpan(new ForegroundColorSpan(Color.RED), index_falsee_big, index_falsee_big + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                //txt[i].setText(spans);
            }

            int id= View.generateViewId();
            txt[i].setId(id);

            // слой, к которому кнопку хотите прикрепить
            layout_for_txt.addView(txt[i]);
        }
    }

    public int find_true_bid_index(int i){
        String word_true = QuestionAnswer.choices[i][0];
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

    public int find_false_bid_index(int i){ //я тупо скопировала текст с find_true_bid_index ;)
        String word_true = QuestionAnswer.choices[i][1];
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
}