package com.studyx.urgames.code2job;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by rahula on 10/01/18.
 */

public class tips extends AppCompatActivity {
    private Button Faq;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tips);
        Faq=(Button)findViewById(R.id.faq);
        Faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), Faq.class);
                startActivity(i);
            }
        });
    }
}