package com.studyx.urgames.code2job

import android.app.ActionBar
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.Window
import android.view.WindowManager
import android.widget.RelativeLayout
import android.widget.TextView

/**
 * Created by rahula on 10/03/18.
 */

class importaant_tips : AppCompatActivity() {
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.genric_tips)
        val tv: TextView
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
        tv.text = "Important Tips"

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
    }
}
