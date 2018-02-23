package com.studyx.urgames.code2job;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by rahula on 10/01/18.
 */

public class syllabus extends Activity {
    private Button Verbal,Logic,Quants;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.syllabus);
        Verbal=(Button)findViewById(R.id.verbal);
        Logic=(Button)findViewById(R.id.logical);
        Quants=(Button)findViewById(R.id.quants);
        Verbal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), course.class);
                i.putExtra("course","Verbal");
                startActivity(i);

            }
        });
        Quants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), course.class);
                i.putExtra("course","Quants");
                startActivity(i);

            }
        });
        Logic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), course.class);
                i.putExtra("course","Logical");
                startActivity(i);

            }
        });
    }
}

