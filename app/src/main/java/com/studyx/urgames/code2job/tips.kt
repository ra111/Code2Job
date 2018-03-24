package com.studyx.urgames.code2job

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button

/**
 * Created by rahula on 10/01/18.
 */

class tips : Activity() {
    private var Faq: Button? = null
    private var Dday: Button? = null
    private var Imp: Button? = null
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tips)
        Faq = findViewById<View>(R.id.faq) as Button
        Dday = findViewById<View>(R.id.d_day) as Button
        Imp = findViewById<View>(R.id.important_topics) as Button
        Faq!!.setOnClickListener { view ->
            val i = Intent(view.context, Faq!!::class.java)
            startActivity(i)
        }
        Dday!!.setOnClickListener { view ->
            val i = Intent(view.context, dday::class.java)
            startActivity(i)
        }
        Imp!!.setOnClickListener { view ->
            val i = Intent(view.context, importaant_tips::class.java)
            startActivity(i)
        }
    }
}