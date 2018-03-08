package com.studyx.urgames.code2job;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by rahula on 09/11/17.
 */

public class MainActivity extends Activity {
    private static final String TAG ="Google Activity" ;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private SignInButton google_signIn;
    private DatabaseReference mDatabase;
    LinearLayout button;
    View importPanel;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=(LinearLayout)findViewById(R.id.signIn);
        final RelativeLayout linearLayout = (RelativeLayout) findViewById(R.id.relative);
        AnimationDrawable animationDrawable = (AnimationDrawable) linearLayout.getBackground();
        animationDrawable.setEnterFadeDuration(1000);
            animationDrawable.setExitFadeDuration(1500);
            animationDrawable.start();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();

            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(android.R.color.transparent));

            window.setBackgroundDrawableResource(R.drawable.animationlist);
        }


        mAuth = FirebaseAuth.getInstance();
        mDatabase= FirebaseDatabase.getInstance().getReference();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        google_signIn=(SignInButton) findViewById(R.id.sign_in_button1);
        google_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                importPanel = ((ViewStub) findViewById(R.id.stub_import)).inflate();
              button.setVisibility(View.GONE);
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


    }


    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, 9001);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 9001) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                Log.w(TAG, "Google sign in failed", e);
                Toast.makeText(this,e.getMessage()+"GOOGLE SIGN IN FAILED",Toast.LENGTH_LONG).show();
            }
        }
    }
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        importPanel.setVisibility(View.GONE);
                        button.setVisibility(View.VISIBLE);
                        if (task.isSuccessful()) {

                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(getApplicationContext(), "Singed in Sucessfully",
                                    Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                    final  String uid= user.getUid();
                            DatabaseReference current_ref = mDatabase.child("users").child(uid).child("current");
                            current_ref.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    String current = dataSnapshot.getValue(String.class);

                                    if (current == null) {
                                        mDatabase.child("users").child(uid).child("current").setValue("1");
                                        mDatabase.child("users").child(uid).child("freemium").setValue("1");
                                        mDatabase.child("users").child(uid).child("paid").setValue("0");
                                    }
                                }
                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                            Intent menu=new Intent(MainActivity.this, menu.class);

                            startActivity(menu);
                        } else {
                            importPanel.setVisibility(View.GONE);
                            button.setVisibility(View.VISIBLE);
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
        //    Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        try {
            if (currentUser.getDisplayName() != null) {
                Toast.makeText(this, "Signed In Successfully", Toast.LENGTH_LONG).show();
                Intent menu = new Intent(MainActivity.this, menu.class);

                startActivity(menu);
                // updateUI(currentUser
            }

        } catch (Exception e)
        {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
