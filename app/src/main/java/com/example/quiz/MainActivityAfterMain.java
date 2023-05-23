package com.example.quiz;

//import static com.example.quiz.QuestionAnswer.choices;

import static java.lang.Math.min;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Locale;

public class MainActivityAfterMain extends AppCompatActivity {
    public LinearLayout layout_for_txt;
    public TextView textView_with_res; // в xml это textView4
    String[][] some_choices;
    Button btn_try, btn_again;
    public int totalQ=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_after_main);
        layout_for_txt = findViewById(R.id.layout_af_main);
        textView_with_res = findViewById(R.id.textView4);
        Bundle arguments = getIntent().getExtras();
        String res = arguments.get("res").toString();
        String[][] some_choices = (String[][]) getIntent().getSerializableExtra("some_choices");
        String text = arguments.get("hello").toString();
        textView_with_res.setText(text);

        create_btn_s(res, some_choices);
        // сохдание byn_try
        btn_again = findViewById(R.id.button_again);
        btn_again.setAllCaps(false);
       // btn_again.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));
        // btn[i].setText(some_choices[i][0]);

        // btn_try.setBackgroundColor(Color.RED);
        btn_again.setBackgroundColor(Color.parseColor("#be3455"));
        GradientDrawable shape = new GradientDrawable();
        shape.setColor(Color.parseColor("#be3455"));
        btn_again.setTextColor(Color.WHITE);
        shape.setCornerRadius(30);
        btn_again.setBackground(shape);

        btn_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMainStart();
            }
        });


    }


    public void create_btn_s(String res, String[][] some_choices){
        int list_len = min(res.length(), some_choices.length);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

// задаем отступы между кнопками
        params.setMargins(100, 100, 100, 50);
        Button[] btn = new Button[list_len+1];
        for (int i = 0; i < list_len; ++i) {
            btn[i] = new Button(this);
            btn[i].setAllCaps(false);
            btn[i].setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
           // btn[i].setText(some_choices[i][0]);
            btn[i].setId(i);
            int index_of_true_letter = find_true_bid_index(i, some_choices);
            String true_wr_txt = some_choices[i][0];


            SpannableStringBuilder builder = new SpannableStringBuilder();

// Добавление зеленой части текста в builder

            char c = res.charAt(i);
            String sim_of_res = String.valueOf(c);
            if (sim_of_res.equals("-")){  //типа если у нас неверноор
                int index_of_false_letter = find_false_bid_index(i, some_choices);
                SpannableString redSpannable = new SpannableString(true_wr_txt);
                redSpannable.setSpan(new ForegroundColorSpan(Color.GREEN), index_of_true_letter, index_of_true_letter+1, 0);
                redSpannable.setSpan(new ForegroundColorSpan(Color.RED), index_of_false_letter, index_of_false_letter+1, 0);
                builder.append(redSpannable);

            }else{
                SpannableString greenSpannable = new SpannableString(true_wr_txt);
                greenSpannable.setSpan(new ForegroundColorSpan(Color.GREEN), index_of_true_letter, index_of_true_letter+1, 0);
                builder.append(greenSpannable);




            }
            //GradientDrawable shape = new GradientDrawable();
            //shape.setColor(Color.parseColor("#be3455"));

           // shape.setCornerRadius(30);
            //btn[i].setBackground(shape);


            btn[i].setText(builder);
            


            // слой, к которому кнопку хотите прикрепить
            layout_for_txt.addView(btn[i]);
        }



    }






    public void createTextView(String res, String[][] some_choices){


        //totalQ = res.length(); //длина стоски из плюсов и минусов
        totalQ = min(some_choices.length, res.length());
        TextView[] txt = new TextView[totalQ];
        for (int i = 0; i < totalQ; ++i) {
            char c = res.charAt(i);
            String sim_of_res = String.valueOf(c);


            txt[i] = new TextView(this);
            txt[i].setTextSize(20);
            txt[i].setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            //txt[i].setText(QuestionAnswer.choices[i][0]);
            int index_true_big = find_true_bid_index(i, some_choices);
            if (sim_of_res.equals("+")) {
                Spannable spans = new SpannableString(some_choices[i][0]);

                spans.setSpan(new ForegroundColorSpan(Color.GREEN), index_true_big, index_true_big + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                txt[i].setText(spans);
            }else{
                Spannable spans = new SpannableString(some_choices[i][0]);
                int index_falsee_big = find_false_bid_index(i, some_choices);
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

    public void startMainStart(){
        Intent intent = new Intent(this, MainStart.class);
        //передача объекта с ключом "hello" и значением "Hello World"

        //запуск SecondActivitygj
       // intent.putExtra("some_choices",some_choices);
        startActivity(intent);
    }

    public int find_false_bid_index(int i, String[][]some_choices){ //я тупо скопировала текст с find_true_bid_index ;)
        String word_true = some_choices[i][1];
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