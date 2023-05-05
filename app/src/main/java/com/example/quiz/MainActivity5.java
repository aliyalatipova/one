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

public class MainActivity5 extends AppCompatActivity {
    LinearLayout layot_res;
    LinearLayout layout_likes;
    TextView text_with_res;
    //U+2665

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        layot_res = findViewById(R.id.layot_res);
        layout_likes = findViewById(R.id.layout_likes);
        text_with_res = findViewById(R.id.text_with_res);
        int slice=0; // боже надеюсь это переводится как отрезок
        int slice_t=0;
        int slice_f = 0;

        Spannable spans = new SpannableString("Пример текста в текст вью");
        spans.setSpan(new ForegroundColorSpan(Color.GREEN), 1, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        text_with_res.setText(spans);

        Bundle arguments = getIntent().getExtras();
        String text = arguments.get("hello").toString(); // text для textView
        String res = arguments.get("res").toString();
        int totalQuestion = Integer.parseInt(arguments.get("totalQuestion").toString());
        int unicode1 = 0x2665;
        int unicode = 0x2661;
        String heart = getEmojiByUnicode(unicode);

        Button[] btn = new Button[totalQuestion];
        TextView[] textView = new TextView[totalQuestion];

        for (int i = 0; i < totalQuestion; ++i) {
            text_with_res.setText(text_with_res.getText().toString() + i);
            //создаем textView[i] и btn[i]
            textView[i] = new Button(this);
            textView[i].setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            btn[i] = new Button(this);
            btn[i].setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            int id= View.generateViewId();
            btn[i].setId(id);
            int id1= View.generateViewId();
            textView[i].setId(id1);
            //устанавливаем чтоьы были и маленькие буквы
            btn[i].setAllCaps(false);
            textView[i].setAllCaps(false);
            //очень важная штука ,будем определять slice для true и для false
            String true_writting = QuestionAnswer.choices[i][0];
            String true_lower = true_writting.toLowerCase(Locale.ROOT);
            // сейчас будет обработка слова, чтобы понять при каком ндексе буквы большая
           // for (int j = 0; j < true_lower.length(); j++) {
              //  char true_writting_j = true_writting.charAt(j);
              //  String tr_wr = String.valueOf(true_writting_j);
             //   char low_writting_j = true_lower.charAt(j);
             //   String lo_wr = String.valueOf(low_writting_j);
              //  if (tr_wr.equals(lo_wr)){
               //     continue;
               // }else { // если как ьы не равнятются
                  //  slice_t = j;
                   // break;
              //  }
          //  }





            char c = res.charAt(i);
            String sim_of_res = String.valueOf(c);
            btn[i].setText(heart);


            //if (sim_of_res.equals("+")){

                //ура тот код бетте
                //btn[i].setText(QuestionAnswer.choices[i][0] + " - Верно" + sm);
                //String tt = QuestionAnswer.choices[i][0] + " - Верно" + heart;
               // Spannable spans1 = new SpannableString(tt);
                //spans1.setSpan(new ForegroundColorSpan(Color.GREEN), slice_t, slice_t+1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                //textView[i].setText(spans1);
              //  textView[i].setText("+");
          //  }
           // else {

               // String false_writting = QuestionAnswer.choices[i][1];
              //  String false_lower = false_writting.toLowerCase(Locale.ROOT);
               // for (int j = 0; j < false_lower.length(); j++) {
                    //char false_writting_j = false_writting.charAt(j);
                   // String fl_wr = String.valueOf(false_writting_j);
                   // char low_writting_j = false_lower.charAt(j);
                    //String lo_wr = String.valueOf(low_writting_j);
                    //if (fl_wr.equals(lo_wr)){
                       // continue;
                   // }else { // если как ьы не равнятются
                      //  slice_f = j;
                       // break;
                  //  }
         //   }

                // ака классная штука
               // String tt = QuestionAnswer.choices[i][0] + " - Верно" + heart;
               // Spannable spans1 = new SpannableString(tt);
               // spans1.setSpan(new ForegroundColorSpan(Color.GREEN), slice_f, slice_f+1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
               // textView[i].setText(spans1);
              //  textView[i].setText("-");
           // layot_res.addView(textView[i]);
            //layout_likes.addView(btn[i]);
        //}
    }




}
    public String getEmojiByUnicode(int unicode){
        return new String(Character.toChars(unicode));
    }
}


