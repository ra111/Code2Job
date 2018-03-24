package com.studyx.urgames.code2job

import android.app.ActionBar
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewStub
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.HorizontalBarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import java.util.*

/**
 * Created by rahula on 17/01/18.
 */

class course : AppCompatActivity(), OnChartValueSelectedListener {

    private var course: String? = null
    internal  lateinit var pieChart: PieChart
    private var tv: TextView? = null
    internal var labels = ArrayList<String>()
    internal var bargroup1 = ArrayList<BarEntry>()
    internal  lateinit var barChart: BarChart
    internal var yvalues = ArrayList<Entry>()
    internal var xVals = ArrayList<String>()
    private val syllabus: Array<String>? = null
    internal var Table: LinearLayout? = null
    internal lateinit var importPanel: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.course)
        pieChart = findViewById<View>(R.id.piechart) as PieChart
        barChart = findViewById<View>(R.id.barchart) as HorizontalBarChart

        importPanel = (findViewById<View>(R.id.stub_import) as ViewStub).inflate()

        val extras = intent.extras
        course = extras!!.getString("course")
        supportActionBar!!.setBackgroundDrawable(
                ColorDrawable(Color.parseColor("#505050")))

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window

            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = resources.getColor(android.R.color.transparent)

            window.setBackgroundDrawableResource(R.drawable.animationlist)
        }
        val ab = supportActionBar

        // Create a TextView programmatically.
        tv = TextView(applicationContext)

        // Create a LayoutParams for TextView
        val lp = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                RelativeLayout.LayoutParams.WRAP_CONTENT) // Height of TextView

        // Apply the layout parameters to TextView widget
        tv!!.layoutParams = lp

        // Set text to display in TextView
        tv!!.text = course!!.toString()

        // Set the text color of TextView
        tv!!.setTextColor(Color.WHITE)
        tv!!.textSize = 20.0f
        val boldTypeface = Typeface.defaultFromStyle(Typeface.BOLD)

        tv!!.typeface = boldTypeface

        // Set TextView text alignment to center
        tv!!.gravity = Gravity.CENTER

        // Set the ActionBar display option
        ab!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        ab.customView = tv
        data(course!!)
    }

    fun init(course: ArrayList<String>) {
        for (i in course.indices) {
            val current = course[i]
            val separated = current.split(",".toRegex()).dropLastWhile({ it.isEmpty() }).toTypedArray()
            val name = separated[0]
            val questions = separated[1]
            labels.add(name)
            bargroup1.add(BarEntry(Integer.parseInt(questions).toFloat(), i))
            yvalues.add(Entry(Integer.parseInt(questions).toFloat(), i))
            xVals.add(name)
        }
        val bardataset = BarDataSet(bargroup1, "Number of Questions")
        val dataSet = PieDataSet(yvalues, course.toString().trim { it <= ' ' })

        val data = PieData(xVals, dataSet)
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS)

        data.setValueTextSize(10f)
        barChart.setDrawGridBackground(false)
        data.setValueTextColor(Color.WHITE)
        pieChart.isDrawHoleEnabled = false
        pieChart.setUsePercentValues(true)
        data.setValueFormatter(PercentFormatter())
        pieChart.data = data
        pieChart.invalidate()
        bardataset.setColors(ColorTemplate.COLORFUL_COLORS)
        pieChart.setOnChartValueSelectedListener(this)
        val dataa = BarData(labels, bardataset)
        barChart.data = dataa
        barChart.axisLeft.setDrawGridLines(false)
        barChart.axisRight.setDrawGridLines(false)

        barChart.animateXY(1400, 1400)
        barChart.setBackgroundColor(resources.getColor(R.color.Light))
        barChart.invalidate()
        pieChart.animateXY(1400, 1400)
    }


    fun data(course: String) {
        val database = FirebaseDatabase.getInstance()
        val ref = database.getReference("Syllabus")
        importPanel.visibility = View.GONE

        ref.orderByKey().equalTo(course).addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, s: String) {
                val data = ArrayList<String>()
                // Result will be holded Here
                for (dsp in dataSnapshot.children) {
                    data.add(dsp.value.toString()) //add result into array list

                }

                init(data)


            }

            override fun onChildChanged(dataSnapshot: DataSnapshot, s: String) {

            }

            override fun onChildRemoved(dataSnapshot: DataSnapshot) {

            }

            override fun onChildMoved(dataSnapshot: DataSnapshot, s: String) {

            }

            override fun onCancelled(databaseError: DatabaseError) {

            }


        })


    }

    override fun onValueSelected(e: Entry?, dataSetIndex: Int, h: Highlight) {
        if (e == null)
            return
        Log.i("VAL SELECTED",
                "Value: " + e.`val` + ", xIndex: " + e.xIndex
                        + ", DataSet index: " + dataSetIndex)
    }

    override fun onNothingSelected() {
        Log.i("PieChart", "nothing selected")
    }
}
