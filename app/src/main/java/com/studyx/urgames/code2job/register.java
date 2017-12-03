package com.studyx.urgames.code2job;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by rahula on 09/11/17.
 */

public class register   extends AppCompatActivity {
    private EditText email, name, college, password, city;
    private Button submit;
    private SignInButton google_signIn;
    private FirebaseAuth firebaseAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        firebaseAuth = FirebaseAuth.getInstance();
        super.onCreate(savedInstanceState);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        setContentView(R.layout.register);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        name = (EditText) findViewById(R.id.name);
        college = (EditText) findViewById(R.id.college);
        city = (EditText) findViewById(R.id.city);

        submit = (Button) findViewById(R.id.submit);
        google_signIn=(SignInButton) findViewById(R.id.sign_in_button);
        google_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConnectivityManager connectivityManager
                        = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                if( activeNetworkInfo != null && activeNetworkInfo.isConnected())
                {
                    Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                    startActivityForResult(signInIntent, 9001);
                }else {
                    Toast.makeText(getApplicationContext(), "Oops! no internet connection!", Toast.LENGTH_SHORT).show();
                }

                }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                registerUser();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);

            } catch (ApiException e) {


            }
        }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            final FirebaseUser user = firebaseAuth.getCurrentUser();


                            name.setKeyListener(null);
                            name.setTextColor(Color.GRAY);
                            email.setKeyListener(null);
                            email.setTextColor(Color.GRAY);
                            password.setKeyListener(null);
                            password.setTextColor(Color.GRAY);
                            name.setText(user.getEmail());
                            email.setText(user.getDisplayName());
                            password.setVisibility(View.GONE);

                            submit.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    if (city.getText().toString().trim().length() < 0 ||city.getText().toString().trim().length() == 0) {
                                        Toast.makeText(getApplicationContext(), "Enter your city", Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                    if (college.getText().toString().trim().length() < 0 || college.getText().toString().trim().length() == 0) {
                                        Toast.makeText(getApplicationContext(), "Enter your college", Toast.LENGTH_SHORT).show();
                                        return;
                                    }

                                    mDatabase.child("users").child(user.getUid()).child("city").setValue(city.getText().toString().trim());
                                    mDatabase.child("users").child(user.getUid()).child("college").setValue(college.getText().toString().trim());
                                    mDatabase.child("users").child(user.getUid()).child("name").setValue(user.getDisplayName());
                                    mDatabase.child("users").child(user.getUid()).child("current").setValue("1");
                                    mDatabase.child("users").child(user.getUid()).child("handout").setValue("1");

                                 //   Toast.makeText(getApplicationContext(), "Successfully registered", Toast.LENGTH_LONG).show();
                                }
                            });

                    //   registerUser(user.getEmail(),user.getDisplayName(),user.getUid());

                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();

    }
    public void registerUser() {



        String email_val = email.getText().toString().trim();
        String pass_val = password.getText().toString().trim();
        final String name_val = name.getText().toString().trim();
        final String city_val = city.getText().toString().trim();
        final String college_val = college.getText().toString().trim();
        Log.d("Emails", email_val);

            if (email.getText().toString().trim() == null) {
                Toast.makeText(getApplicationContext(), "Enter email address", Toast.LENGTH_SHORT).show();
                return;
            } else {
                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText().toString().trim()).matches()) {
                    Toast.makeText(getApplicationContext(), "Enter a valid Email", Toast.LENGTH_SHORT).show();
                    return;
                }

            }
            if (pass_val.length() < 0 || pass_val.length() == 0) {
        Toast.makeText(getApplicationContext(), "Enter password ", Toast.LENGTH_SHORT).show();
        return;
    }
        if (city_val.length() < 0 || city_val.length() == 0) {
        Toast.makeText(getApplicationContext(), "Enter your city", Toast.LENGTH_SHORT).show();
        return;
    }
        if (college_val.length() < 0 || college_val.length() == 0) {
        Toast.makeText(getApplicationContext(), "Enter your college", Toast.LENGTH_SHORT).show();
        return;
    }


        firebaseAuth.createUserWithEmailAndPassword(email_val, pass_val)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //checking if success
                            if (task.isSuccessful()) {
                                FirebaseUser use = firebaseAuth.getCurrentUser();

                                //display some message here
                                Toast.makeText(getApplicationContext(), "Successfully registered", Toast.LENGTH_LONG).show();


                                mDatabase.child("users").child(use.getUid()).child("city").setValue(city_val);
                                mDatabase.child("users").child(use.getUid()).child("college").setValue(college_val);
                                mDatabase.child("users").child(use.getUid()).child("name").setValue(name_val);
                                mDatabase.child("users").child(use.getUid()).child("current").setValue("1");
                                mDatabase.child("users").child(use.getUid()).child("handout").setValue("1");
                            } else {
                                task.getException().getMessage();
                                //display some message here
                                Toast.makeText(getApplicationContext(), "Registration Error please check your connection", Toast.LENGTH_LONG).show();
                            }
                        }
                    });


        }
    }






