package com.studyx.urgames.code2job;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by rahula on 10/01/18.
 */

public class tips extends Activity {
    private Button Faq,Dday,Imp;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tips);
        Faq=(Button)findViewById(R.id.faq);
        Dday=(Button)findViewById(R.id.d_day);
        Imp=(Button)findViewById(R.id.important_topics);
        Faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), Faq.class);
                startActivity(i);
            }
        });
        Dday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), dday.class);
                startActivity(i);
            }
        });
        Imp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), imp_topics.class);
                startActivity(i);
            }
        });
    }
}