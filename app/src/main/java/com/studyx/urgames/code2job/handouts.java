package com.studyx.urgames.code2job;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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

public class handouts extends AppCompatActivity {
    public String prev_key, next_key,key,title,description,link,title_page,description_page,link_page;
    private TextView previous, next;
    Intent i;
    public void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        super.onCreate(savedInstanceState);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference mDatabase = database.getReference();
        key = intent.getStringExtra("key");
        title_page=intent.getStringExtra("title");
        description_page=intent.getStringExtra("description");
        link_page=intent.getStringExtra("link");
        prev_key = Integer.toString(Integer.parseInt(key) - 1);
        next_key = Integer.toString(Integer.parseInt(key) + 1);
        final DatabaseReference HandoutsRef = database.getReference("Handouts");
        setContentView(R.layout.handout);
        next = (TextView) findViewById(R.id.next);
        previous = (TextView) findViewById(R.id.previous);
next.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(final View view) {
        mDatabase.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("handout").setValue(next_key.toString());
        HandoutsRef.orderByKey().equalTo(next_key.toString()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String s1 = dataSnapshot.getValue().toString();

                String[] mystring = s1.split(",");
                title=mystring[0];
                description=mystring[1];
                link=mystring[2];
                i = new Intent(view.getContext(), handouts.class);
                i.putExtra("title",title);
                i.putExtra("description",description);
                i.putExtra("link",link);
                i.putExtra("key", next_key);
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
previous.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(final View view) {
        mDatabase.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("handout").setValue(prev_key.toString());
        HandoutsRef.orderByKey().equalTo(prev_key.toString()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String s1 = dataSnapshot.getValue().toString();

                String[] mystring = s1.split(",");
                title=mystring[0];
                description=mystring[1];
                link=mystring[2];
                i = new Intent(view.getContext(), handouts.class);
                i.putExtra("title",title);
                i.putExtra("description",description);
                i.putExtra("link",link);
                i.putExtra("key", prev_key);
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
    }
}