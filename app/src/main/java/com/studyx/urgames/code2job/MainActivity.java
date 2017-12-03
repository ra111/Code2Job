package com.studyx.urgames.code2job;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.common.api.GoogleApiClient;


public  class MainActivity extends  AppCompatActivity {


    private GoogleApiClient mGoogleApiClient;

    private Button register, login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        register = (Button) findViewById(R.id.register);
        login = (Button) findViewById(R.id.signIn);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
Intent i =new Intent(MainActivity.this,register.class);
startActivity(i);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 =new Intent(MainActivity.this,login.class);
                startActivity(i1);

            }
        });


    }
}


