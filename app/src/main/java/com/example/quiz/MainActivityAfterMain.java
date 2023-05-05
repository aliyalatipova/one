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
    String text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_after_main);

        findById();
        getInfFromMainAct();
        textView_with_res.setText(text);
        createTextView();
        Button[] btn = new Button[3];




    }


    public void findById(){
        layout_for_txt = findViewById(R.id.layout_for_txt);
        textView_with_res = findViewById(R.id.textView4);
    }

    public void getInfFromMainAct(){
        Bundle arguments = getIntent().getExtras();
        String text = arguments.get("hello").toString(); // text для textView
        String res = arguments.get("res").toString();
        int totalQuestion = Integer.parseInt(arguments.get("totalQuestion").toString());
    }

    public void createTextView(){
        TextView[] txt = new TextView[3];
        for (int i = 0; i < 3; ++i) {


            txt[i] = new TextView(this);
            txt[i].setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            txt[i].setText("кнопка " + i);
            int id= View.generateViewId();
            txt[i].setId(id);

            // слой, к которому кнопку хотите прикрепить
            layout_for_txt.addView(txt[i]);
        }
    }
}