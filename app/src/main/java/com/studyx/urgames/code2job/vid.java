package com.studyx.urgames.code2job;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by rahula on 15/11/17.
 */

public class vid extends AppCompatActivity {
    public TextView previos, next,title,next_title,prev_title;
    public String  text, current;
    Intent i;

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference mDatabase = database.getReference();
    final DatabaseReference CourseRef = database.getReference("Course");

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vid);
        title=(TextView)findViewById(R.id.textView10);
        next = (TextView) findViewById(R.id.next);
        previos = (TextView) findViewById(R.id.previous);
        next_title=(TextView)findViewById(R.id.next_tittle);
        prev_title=(TextView)findViewById(R.id.prev_tittle);
        DatabaseReference current_ref = mDatabase.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("current");
        current_ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                current = dataSnapshot.getValue(String.class);

                Vid(current);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

//
//
        next.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(final View view) {
                                        DatabaseReference current_ref = mDatabase.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("current");
                                        current_ref.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                current = dataSnapshot.getValue(String.class);
                                               int next=Integer.parseInt(current)+1;
                                                mDatabase.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("current").setValue(Integer.toString(next));
                                                Vid(String.valueOf(next));

                                            }

                                            @Override
                                            public void onCancelled(DatabaseError databaseError) {

                                            }
                                        });

                                };

                                });


        previos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                DatabaseReference current_ref = mDatabase.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("current");
                current_ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        current = dataSnapshot.getValue(String.class);
                        int prev=Integer.parseInt(current)-1;
                       mDatabase.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("current").setValue(Integer.toString(prev));
                       Vid(String.valueOf(prev));


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            };



        });




    }

    public void Vid(String keys) {
        titles(keys);
        CourseRef.orderByKey().equalTo(keys).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                String s1 = dataSnapshot.getValue().toString();
                youtube(s1);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }


        });
    }
        public void youtube( String frameVideo)
    {
        String titles =frameVideo.substring(0,frameVideo.indexOf(','));
        String frame=frameVideo.substring(frameVideo.indexOf(',')+1,frameVideo.length());

        WebView displayYoutubeVideo = (WebView) findViewById(R.id.webView);
        displayYoutubeVideo.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
       title.setText(titles);
        WebSettings webSettings = displayYoutubeVideo.getSettings();
        webSettings.setJavaScriptEnabled(true);
        displayYoutubeVideo.loadData(frame, "text/html", "utf-8");
    }
public void titles( String keys)

{
    String next=Integer.toString((Integer.parseInt(keys)+1));
    String prev=Integer.toString((Integer.parseInt(keys)-1));
    CourseRef.orderByKey().equalTo(next).addChildEventListener(new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            String s1 = dataSnapshot.getValue().toString();
             String next= s1.substring(0,s1.indexOf(','));
             next_title.setText(next);


        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {

        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    });
    CourseRef.orderByKey().equalTo(prev).addChildEventListener(new ChildEventListener() {
        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            String s1 = dataSnapshot.getValue().toString();
            String prev= s1.substring(0,s1.indexOf(','));
            prev_title.setText(prev);


        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {

        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    });

}

    }




