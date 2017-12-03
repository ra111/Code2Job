package com.studyx.urgames.code2job;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by rahula on 15/11/17.
 */

public class outline extends AppCompatActivity {
    private LinearLayout syllabus;
    int i;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.outline);
        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        syllabus=(LinearLayout)findViewById(R.id.syllabus);
        for(i=0;i<5;i++)
        {
            TextView tv=new TextView(this);
            tv.setText("\u25CB "+"Dynamic text"+i);tv.setLayoutParams(lparams);

            lparams.setMargins(40,30,20,30);
            tv.setTextSize(15);
            tv.setId(i+20);
            syllabus.addView(tv);
        }
    }
}
