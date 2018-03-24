package com.studyx.urgames.code2job

import android.app.ActionBar
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.PopupWindow
import android.widget.RelativeLayout
import android.widget.TextView

import com.studyx.urgames.code2job.ques.Companion.answers
import com.studyx.urgames.code2job.ques.Companion.questions

/**
 * Created by rahula on 15/01/18.
 */
abstract class Faq : AppCompatActivity() {
    internal abstract var tv: TextView
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.faq)
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
        tv.layoutParams = lp

        // Set text to display in TextView
        tv.text = "FAQ"

        // Set the text color of TextView
        tv.setTextColor(Color.WHITE)
        tv.textSize = 20.0f
        val boldTypeface = Typeface.defaultFromStyle(Typeface.BOLD)

        tv.typeface = boldTypeface

        // Set TextView text alignment to center
        tv.gravity = Gravity.CENTER

        // Set the ActionBar display option
        ab!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM

        // Finally, set the newly created TextView as ActionBar custom view
        ab.customView = tv
        val adapter = ArrayAdapter(this, R.layout.list, questions)
        val listView = findViewById<View>(R.id.question_list) as ListView

        listView.adapter = adapter

        listView.onItemClickListener = AdapterView.OnItemClickListener { adapterView, view, i, l ->
            val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val popupView = inflater.inflate(R.layout.popup, null)

            val faq = findViewById<View>(R.id.faq) as RelativeLayout
            val width = LinearLayout.LayoutParams.WRAP_CONTENT
            val height = LinearLayout.LayoutParams.WRAP_CONTENT

            val focusable = false
            val popupWindow = PopupWindow(popupView, width, height, focusable)
            popupWindow.isOutsideTouchable = false
            val ques = popupView.findViewById<View>(R.id.popupTextQues) as TextView
            val ans = popupView.findViewById<View>(R.id.popupTextAns) as TextView
            val close = popupView.findViewById<View>(R.id.close_button) as Button
            close.bringToFront()
            close.setOnClickListener {
                popupWindow.dismiss()
                popupWindow.isOutsideTouchable = true
            }

            ques.text = questions[i]
            ans.text = answers[i]
            popupWindow.showAtLocation(faq, Gravity.CENTER, 0, 0)
        }
    }

}