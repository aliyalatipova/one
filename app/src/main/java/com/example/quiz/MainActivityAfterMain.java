package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivityAfterMain extends AppCompatActivity {
    public LinearLayout layout_for_txt;
    public TextView textView_with_res; // в xml это textView4
    //String text;
    public int totalQ=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_after_main);

        findById();

        Bundle arguments = getIntent().getExtras();
        textView_with_res.setText(arguments.get("hello").toString());// ну как бы в текстик пишем что у нас получилосьр5оп7куво
        String res = arguments.get("res").toString();
        createTextView(res);




    }


    public void findById(){
        layout_for_txt = findViewById(R.id.layout_for_txt);
        textView_with_res = findViewById(R.id.textView4);
    }



    public void createTextView(String res){
        totalQ = res.length(); //длина стоски из плюсов и минусов

        TextView[] txt = new TextView[totalQ];
        for (int i = 0; i < totalQ; ++i) {
            char c = res.charAt(i);
            String sim_of_res = String.valueOf(c);


            txt[i] = new TextView(this);
            txt[i].setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            txt[i].setText(QuestionAnswer.choices[i][0]);
            int id= View.generateViewId();
            txt[i].setId(id);

            // слой, к которому кнопку хотите прикрепить
            layout_for_txt.addView(txt[i]);
        }
    }

    //public find_false_index(){

   // }
}