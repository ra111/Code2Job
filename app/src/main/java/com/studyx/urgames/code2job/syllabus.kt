package com.studyx.urgames.code2job

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button

/**
 * Created by rahula on 10/01/18.
 */

class syllabus : Activity() {
    private var Verbal: Button? = null
    private var Logic: Button? = null
    private var Quants: Button? = null
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.syllabus)
        Verbal = findViewById<View>(R.id.verbal) as Button
        Logic = findViewById<View>(R.id.logical) as Button
        Quants = findViewById<View>(R.id.quants) as Button
        Verbal!!.setOnClickListener { view ->
            val i = Intent(view.context, course::class.java)
            i.putExtra("course", "Verbal")
            startActivity(i)
        }
        Quants!!.setOnClickListener { view ->
            val i = Intent(view.context, course::class.java)
            i.putExtra("course", "Quants")
            startActivity(i)
        }
        Logic!!.setOnClickListener { view ->
            val i = Intent(view.context, course::class.java)
            i.putExtra("course", "Logical")
            startActivity(i)
        }
    }
}

