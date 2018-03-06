package com.studyx.urgames.code2job;

import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewStub;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by rahula on 17/01/18.
 */

public class course extends AppCompatActivity implements OnChartValueSelectedListener {

     private String course;PieChart pieChart;
     private  TextView tv;
    ArrayList<String> labels = new ArrayList<String>();
    ArrayList<BarEntry> bargroup1 = new ArrayList<>();
    BarChart barChart;
    ArrayList<Entry> yvalues = new ArrayList<Entry>();
    ArrayList<String> xVals = new ArrayList<String>();
     private String[] syllabus;
     LinearLayout Table;
     View importPanel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course);
        pieChart = (PieChart) findViewById(R.id.piechart);
         barChart = (HorizontalBarChart) findViewById(R.id.barchart);

        importPanel = ((ViewStub) findViewById(R.id.stub_import)).inflate();

        Bundle extras = getIntent().getExtras();
        course= extras.getString("course");
        getSupportActionBar().setBackgroundDrawable(
                new ColorDrawable(Color.parseColor("#505050")));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();

            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(android.R.color.transparent));

            window.setBackgroundDrawableResource(R.drawable.animationlist);
        }
        android.support.v7.app.ActionBar ab = getSupportActionBar();

        // Create a TextView programmatically.
        tv = new TextView(getApplicationContext());

        // Create a LayoutParams for TextView
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                RelativeLayout.LayoutParams.WRAP_CONTENT); // Height of TextView

        // Apply the layout parameters to TextView widget
        tv.setLayoutParams(lp);

        // Set text to display in TextView
        tv.setText(course.toString());

        // Set the text color of TextView
        tv.setTextColor(Color.WHITE);
        tv.setTextSize(20.0f);
        Typeface boldTypeface = Typeface.defaultFromStyle(Typeface.BOLD);

        tv.setTypeface(boldTypeface);

        // Set TextView text alignment to center
        tv.setGravity(Gravity.CENTER);

        // Set the ActionBar display option
        ab.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        ab.setCustomView(tv);
        data(course);
    }

    public void init(ArrayList<String> course) {
        for (int i = 0; i < course.size(); i++) {
            String current=course.get(i);
            String[] separated = current.split(",");
            String name=separated[0];
            String questions=separated[1];
            labels.add(name);
            bargroup1.add(new BarEntry(Integer.parseInt(questions), i));
            yvalues.add(new Entry(Integer.parseInt(questions), i));
            xVals.add(name);
        }
        BarDataSet bardataset = new BarDataSet(bargroup1, "Number of Questions");
        PieDataSet dataSet = new PieDataSet(yvalues, course.toString().trim());

        PieData data = new PieData(xVals, dataSet);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        data.setValueTextSize(10f);barChart.setDrawGridBackground(false);
        data.setValueTextColor(Color.WHITE);
        pieChart.setDrawHoleEnabled(false);
        pieChart.setUsePercentValues(true);
        data.setValueFormatter(new PercentFormatter());
        pieChart.setData(data);
        pieChart.invalidate();
        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
        pieChart.setOnChartValueSelectedListener(this);
        BarData dataa = new BarData(labels, bardataset);
        barChart.setData(dataa);
        barChart.getAxisLeft().setDrawGridLines(false);
        barChart.getAxisRight().setDrawGridLines(false);

        barChart.animateXY(1400,1400);
        barChart.setBackgroundColor(getResources().getColor(R.color.Light));
        barChart.invalidate();
        pieChart.animateXY(1400, 1400);
    }


   public void data(String course){
      final FirebaseDatabase database = FirebaseDatabase.getInstance();
      DatabaseReference ref = database.getReference("Syllabus");
       importPanel.setVisibility(View.GONE);

      ref.orderByKey().equalTo(course).addChildEventListener(new ChildEventListener() {
          @Override
          public void onChildAdded(DataSnapshot dataSnapshot, String s) {
         ArrayList<String>     data = new ArrayList<String>();
              // Result will be holded Here
              for (DataSnapshot dsp : dataSnapshot.getChildren()) {
                  data.add(String.valueOf(dsp.getValue())); //add result into array list

              }

                  init(data);


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
    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
        if (e == null)
            return;
        Log.i("VAL SELECTED",
                "Value: " + e.getVal() + ", xIndex: " + e.getXIndex()
                        + ", DataSet index: " + dataSetIndex);
    }

    @Override
    public void onNothingSelected() {
        Log.i("PieChart", "nothing selected");
    }
}
