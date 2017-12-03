package com.studyx.urgames.code2job;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

/**
 * Created by rahula on 14/11/17.
 */

public class account extends menu {
    private ImageView image;
private TextView name,email,city,college,name11;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        super.onCreate(savedInstanceState);
        final DatabaseReference mDatabase=database.getReference();
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.account, null, false);
        mDrawerLayout.addView(contentView, 0);
        image=(ImageView)findViewById(R.id.avtar);
        name=(TextView)findViewById(R.id.name);
        email=(TextView)findViewById(R.id.emai);
        name11=(TextView)findViewById(R.id.name12);
        city=(TextView)findViewById(R.id.city);
        college=(TextView)findViewById(R.id.college);

        Picasso.with(this).load(FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl()).placeholder(R.drawable.common_google_signin_btn_icon_dark_normal_background).error(R.drawable.common_google_signin_btn_icon_dark_normal_background).into(image);
        mDatabase.child("users").child( FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String name1=(String)dataSnapshot.child("name").getValue();
                String college1=(String)dataSnapshot.child("college").getValue();
                String city1=(String)dataSnapshot.child("city").getValue();

                name.setText(name1);
                name11.setText(name1);
                college.setText(college1);
                city.setText(city1);
                email.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }
}
