package com.studyx.urgames.code2job;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

/**
 * Created by rahula on 13/11/17.
 */

public class menu extends AppCompatActivity {
    protected DrawerLayout mDrawerLayout;
    private ImageButton Formula, Vids, Tips, Syllabus,Premium;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        LinearLayout constraintLayout = (LinearLayout) findViewById(R.id.mainContent);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        getSupportActionBar().setBackgroundDrawable(
                new ColorDrawable(Color.parseColor("#505050")));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();

            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(android.R.color.transparent));

            window.setBackgroundDrawableResource(R.drawable.animationlist);
        }
        Formula = (ImageButton) findViewById(R.id.formula);
        Tips = (ImageButton) findViewById(R.id.tips);
        Syllabus = (ImageButton) findViewById(R.id.course);
        Premium=(ImageButton)findViewById(R.id.premium);




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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.overflow_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_facebook:
                Uri uri = Uri.parse("https://www.facebook.com/groups/114673289208612/"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                return true;
            case R.id.menu_profile:
              Intent i =new Intent(this,account.class);
              startActivity(i);
                return true;
            case  R.id.menu_signout:
               AuthUI.getInstance()
                        .signOut(this)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            public void onComplete(@NonNull Task<Void> task) {
                                // user is now signed out
                                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(i);
                                finish();
                            }
                        });
                    return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    }
