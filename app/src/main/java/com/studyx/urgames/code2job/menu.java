package com.studyx.urgames.code2job;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by rahula on 13/11/17.
 */

public class menu extends AppCompatActivity {
    private String[] mPlanetTitles;
    protected DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private Intent drawer;
    private ImageButton Formula, Vids, Tips, Syllabus,Premium;
    RelativeLayout mDrawerPane;
    private TextView name;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);


        Formula = (ImageButton) findViewById(R.id.formula);
        Vids = (ImageButton) findViewById(R.id.vid);
        Tips = (ImageButton) findViewById(R.id.tips);
        Syllabus = (ImageButton) findViewById(R.id.course);
        Premium=(ImageButton)findViewById(R.id.premium);
        Vids.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), vid.class);
                startActivity(i);
            }
        });
        Formula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), Formula.class);
                startActivity(i);
            }
        });
       Tips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), tips.class);
                startActivity(i);
            }
        });
        Syllabus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), syllabus.class);
                startActivity(i);
            }
        });
        Premium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), premium.class);
                startActivity(i);
            }
        });
    }
    }
