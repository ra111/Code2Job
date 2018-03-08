package com.studyx.urgames.code2job;

import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by rahula on 20/01/18.
 */

public class dday extends AppCompatActivity {




    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dday);
TextView tv;
        getSupportActionBar().setBackgroundDrawable(
                new ColorDrawable(Color.parseColor("#505050")));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();

            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(android.R.color.transparent));

            window.setBackgroundDrawableResource(R.drawable.animationlist);
        }
        android.support.v7.app.ActionBar ab = getSupportActionBar();

        // Create a TextView programmatically.
        tv = new TextView(getApplicationContext());

        // Create a LayoutParams for TextView
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                RelativeLayout.LayoutParams.WRAP_CONTENT); // Height of TextView

        // Apply the layout parameters to TextView widget
        tv.setLayoutParams(lp);

        // Set text to display in TextView
        tv.setText("D Day");

        // Set the text color of TextView
        tv.setTextColor(Color.WHITE);
        tv.setTextSize(20.0f);
        Typeface boldTypeface = Typeface.defaultFromStyle(Typeface.BOLD);

        tv.setTypeface(boldTypeface);

        // Set TextView text alignment to center
        tv.setGravity(Gravity.CENTER);

        // Set the ActionBar display option
        ab.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        // Finally, set the newly created TextView as ActionBar custom view
        ab.setCustomView(tv);
    }

}