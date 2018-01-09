package com.studyx.urgames.code2job;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by rahula on 14/11/17.
 */

public class course extends menu {
    private ImageView image;
    private String first,value,key,src,title,para,handout_key,description,link,title1;
    private Intent i;


    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    private RelativeLayout  Intro,Course,Handouts,Outline;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final DatabaseReference mDatabase=database.getReference();
        final DatabaseReference CourseRef = database.getReference("Course");
        final DatabaseReference HandoutRef=database.getReference("Handouts");

        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.course, null, false);
        mDrawerLayout.addView(contentView, 0);
        Intro=(RelativeLayout)findViewById(R.id.relative1);
        Course=(RelativeLayout)findViewById(R.id.relative);
        Handouts=(RelativeLayout)findViewById(R.id.relative2);
        Outline=(RelativeLayout)findViewById(R.id.relative3);
        Intro.setClickable(true);
        Course.setClickable(true);
        Handouts.setClickable(true);
        Outline.setClickable(true);
        mDatabase.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("handout").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                handout_key= dataSnapshot.getValue().toString();
                HandoutRef.orderByKey().equalTo(handout_key.toString()).addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                        String s1 = dataSnapshot.getValue().toString();
                        String[] mystring = s1.split(",");
                        title1=mystring[0];
                        description=mystring[1];
                        link=mystring[2];

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

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        mDatabase.child("users").child( FirebaseAuth.getInstance().getCurrentUser().getUid()).child("current").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                key = dataSnapshot.getValue().toString();
                Log.d("the key is", key);
                CourseRef.orderByKey().equalTo(key.toString()).addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                        String s1 = dataSnapshot.getValue().toString();
                       // String Current = s1.substring((s1.indexOf('=') + 1), s1.indexOf(','));


                        String[] mystring = s1.split(",");
                        first = mystring[0];
                        src = mystring[1];
                        title=mystring[2];
                        para=mystring[3];


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

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }});

                Intro.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        i = new Intent(view.getContext(), intro.class);
                        startActivity(i);

                    }
                });
                Course.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                      //  Log.d("courses is", first);
                        if (first.equals("text")) {
                            i = new Intent(view.getContext(), text.class);
                            i.putExtra("src", src);
                            i.putExtra("para",para);
                            i.putExtra("title",title);
                            i.putExtra("key", key);


                        } else if (first.equals("quiz")) {
                            Log.d("Value of first", first);
                        } else if (first.equals("vid")) {
                            Log.d("Value of first", first);
                            i = new Intent(view.getContext(), vid.class);

                            i.putExtra("frame", src);
                            i.putExtra("para",para);
                            i.putExtra("title",title);
                            i.putExtra("key", key);
                        }

                         startActivity(i);
                    }
                });
                Handouts.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        i = new Intent(view.getContext(), handouts.class);

                        i.putExtra("title",title1);
                        i.putExtra("description",description);
                        i.putExtra("link",link);
                        i.putExtra("key",handout_key);
                        startActivity(i);

                    }
                });
                Outline.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        i = new Intent(view.getContext(), outline.class);
                        startActivity(i);
                    }

                });


            }



        }