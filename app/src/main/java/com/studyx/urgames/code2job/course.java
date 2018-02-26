package com.studyx.urgames.code2job;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by rahula on 17/01/18.
 */

public class course extends Activity {

     private String course;
     private  TextView Name;
     private String[] syllabus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course);
        Name=findViewById(R.id.tvcourse);
        Bundle extras = getIntent().getExtras();
        course= extras.getString("course");

        data(course);
        Name.setText(course);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void init(ArrayList<String> course) {
        Typeface boldTypeface = Typeface.defaultFromStyle(Typeface.BOLD);
        TableLayout stk = (TableLayout) findViewById(R.id.table_main);

        stk.setElevation(3.0f);
        Display display = getWindowManager().getDefaultDisplay();
        int width = display.getWidth();
        TableRow tbrow0 = new TableRow(this);
        TextView tv0 = new TextView(this);

        tv0.setText(" Sl.No ");
        tv0.setWidth(width/7);
        tv0.setTextColor(Color.WHITE);
        tv0.setGravity(Gravity.CENTER);
        tv0.setTypeface(boldTypeface);
        tv0.setTextSize(13.0f);
        tbrow0.addView(tv0);
        TextView tv1 = new TextView(this);
        tv1.setText(" Syllabus ");
        tv1.setGravity(Gravity.CENTER);
        tv1.setTypeface(boldTypeface);
        tv1.setTextColor(Color.WHITE);
        tv1.setWidth(width/100*30);
        tv1.setTextSize(13.0f);
        tv1.setPadding(2,2,2,10);
        tv0.setPadding(2,2,2,10);

        tbrow0.addView(tv1);
        TextView tv2 = new TextView(this);

        tv2.setText(" No. Of Questions ");
        tv2.setGravity(Gravity.CENTER);
        tv2.setWidth(width/4);
        tv2.setTextColor(Color.WHITE);
        tbrow0.addView(tv2);
        tv2.setTypeface(boldTypeface);
        tv2.setTextSize(13.0f);
        tv2.setPadding(2,2,2,10);
        TextView tv3 = new TextView(this);
        tv3.setText(" Weight ");
        tv3.setWidth(width/4+width*2/35);
        tv3.setGravity(Gravity.CENTER);
        tv3.setTextSize(13.0f);
        tv3.setTextColor(Color.WHITE);
        tv3.setTypeface(boldTypeface);
        tv3.setPadding(2,2,2,10);
        tbrow0.addView(tv3);
        stk.addView(tbrow0);


        for (int i = 0; i < course.size(); i++) {
            String current=course.get(i);
            String[] separated = current.split(",");
            String name=separated[0];
            String questions=separated[1];
            String wieght=separated[2];

            TableRow tbrow = new TableRow(this);
            TextView t1v = new TextView(this);
            if (i % 2 == 0) {

                tbrow.setBackgroundColor(Color.parseColor("#D3D3D3"));

            } else {

                tbrow.setBackgroundColor(Color.parseColor("#FFFFFF"));

            }
            t1v.setTextSize(16.0f);
            t1v.setWidth(width/5);
            t1v.setText(String.valueOf(i+1));
            t1v.setTextColor(Color.BLACK);
            t1v.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            t1v.setGravity(Gravity.CENTER);
            t1v.setPadding(0,10,0,10);
            tbrow.addView(t1v);
            TextView t2v = new TextView(this);
            t2v.setText(name);
            t2v.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            t2v.setTextColor(Color.BLACK);
            t2v.setTextSize(16.0f);
            t2v.setWidth(width/4);
            t2v.setPadding(0,10,0,10);
            t2v.setGravity(Gravity.CENTER);
            tbrow.addView(t2v);
            TextView t3v = new TextView(this);
            t3v.setText(questions);
            t3v.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            t3v.setTextSize(16.0f);
            t3v.setPadding(0,10,0,10);
            t3v.setTextColor(Color.BLACK);
            t3v.setWidth(width/4);
            t3v.setGravity(Gravity.CENTER);
            tbrow.addView(t3v);
            TextView t4v = new TextView(this);
            t4v.setText(wieght);
            t4v.setWidth(width/100*30);
t4v.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            t4v.setTextSize(16.0f);
            t4v.setPadding(0,10,0,10);
            t4v.setTextColor(Color.BLACK);
            t4v.setGravity(Gravity.CENTER);
            tbrow.addView(t4v);
            stk.addView(tbrow,new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,0,1f));
        }

    }

   public void data(String course){
      final FirebaseDatabase database = FirebaseDatabase.getInstance();
      DatabaseReference ref = database.getReference("Syllabus");
      ref.orderByKey().equalTo(course).addChildEventListener(new ChildEventListener() {
          @Override
          public void onChildAdded(DataSnapshot dataSnapshot, String s) {
         ArrayList<String>     data = new ArrayList<String>();
              // Result will be holded Here
              for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                  data.add(String.valueOf(dsp.getValue())); //add result into array list

              }
              if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                  init(data);
              }

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
