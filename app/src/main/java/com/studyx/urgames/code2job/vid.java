package com.studyx.urgames.code2job;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

/**
 * Created by rahula on 15/11/17.
 */

public class vid extends AppCompatActivity {
    private TextView previos, next;
    public String prev_key, next_key, s1, s2, first, value,text,src,title,paras;
    Intent i;

    private String frameVideo, key;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference mDatabase = database.getReference();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();

        frameVideo = intent.getStringExtra("frame");
        key = intent.getStringExtra("key");
        prev_key = Integer.toString(Integer.parseInt(key) - 1);
        next_key = Integer.toString(Integer.parseInt(key) + 1);

        final DatabaseReference CourseRef = database.getReference("Course");
        setContentView(R.layout.vid);
        next = (TextView) findViewById(R.id.next);
        previos = (TextView) findViewById(R.id.previous);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                mDatabase.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("current").setValue(next_key.toString());

                CourseRef.orderByKey().equalTo(next_key.toString()).addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                        String s1 = dataSnapshot.getValue().toString();
                        // String Current = s1.substring((s1.indexOf('=') + 1), s1.indexOf(','));


                        String[] mystring = s1.split(",");
                        first = mystring[0];
                        src = mystring[1];
                        title=mystring[2];
                        paras=mystring[3];
                        if (first.equals("text")) {
                            i=new Intent(view.getContext(),text.class);
                            i.putExtra("src", src);
                            i.putExtra("para",paras);
                            i.putExtra("title",title);
                            i.putExtra("key", next_key);;

                        } else if (first.equals("quiz")) {
                            Log.d("Value of first", first);
                        } else if (first.equals("vid")) {
                            Log.d("Value of first", first);
                            i = new Intent(view.getContext(), vid.class);

                            i.putExtra("frame", src);
                            i.putExtra("para",paras);
                            i.putExtra("title",title);

                            i.putExtra("key", next_key);
                        }
                        startActivity(i);
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
        });

        previos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                if (Integer.parseInt(prev_key) != 0) {

                    mDatabase.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("current").setValue(prev_key.toString());


                    CourseRef.orderByKey().equalTo(prev_key.toString()).addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                            String s1 = dataSnapshot.getValue().toString();



                            String[] mystring = s1.split(",");
                            first = mystring[0];
                            src = mystring[1];
                            title=mystring[2];
                            paras=mystring[3];
                            if (first.equals("text")) {
                                i=new Intent(view.getContext(),text.class);
                                i.putExtra("src", src);
                                i.putExtra("para",paras);
                                i.putExtra("title",title);
                                i.putExtra("key", prev_key);
                            } else if (first.equals("quiz")) {
                                Log.d("Value of first", first);
                            } else if (first.equals("vid")) {
                                Log.d("Value of first", first);
                                i = new Intent(view.getContext(), vid.class);

                                i.putExtra("frame", src);
                                i.putExtra("para",paras);
                                i.putExtra("title",title);

                                i.putExtra("key", prev_key);
                            }
                            startActivity(i);
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
        });







                WebView displayYoutubeVideo = (WebView) findViewById(R.id.webView);
                displayYoutubeVideo.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        return false;
                    }
                });
                WebSettings webSettings = displayYoutubeVideo.getSettings();
                webSettings.setJavaScriptEnabled(true);
                displayYoutubeVideo.loadData(frameVideo, "text/html", "utf-8");
            }



    }



