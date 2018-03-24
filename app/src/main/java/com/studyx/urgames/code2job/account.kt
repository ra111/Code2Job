package com.studyx.urgames.code2job

import android.app.ActionBar
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.WindowManager
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso

/**
 * Created by rahula on 14/11/17.
 */

 class account : AppCompatActivity() {
    private var image: ImageView? = null
    internal lateinit var tv: TextView
    private var name: TextView? = null
    private var email: TextView? = null
    private val city: TextView? = null
    private val college: TextView? = null
    private var name11: TextView? = null
    public override fun onCreate(savedInstanceState: Bundle?) {
        val database = FirebaseDatabase.getInstance()
        super.onCreate(savedInstanceState)
        val mDatabase = database.reference
        setContentView(R.layout.account)
        image = findViewById<ImageView>(R.id.avtar)
        name = findViewById<TextView>(R.id.name)
        email = findViewById<TextView>(R.id.emai)
        name11 = findViewById<TextView>(R.id.name12)
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
        tv.text = "ACCOUNT"

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


        Picasso.with(this).load(FirebaseAuth.getInstance().currentUser!!.photoUrl).placeholder(R.drawable.common_google_signin_btn_icon_dark_normal_background).error(R.drawable.common_google_signin_btn_icon_dark_normal_background).into(image)
        mDatabase.child("users").child(FirebaseAuth.getInstance().currentUser!!.uid).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val name1 = dataSnapshot.child("name").value as String?
                name!!.text = FirebaseAuth.getInstance().currentUser!!.displayName
                name11!!.text = FirebaseAuth.getInstance().currentUser!!.displayName
                email!!.text = FirebaseAuth.getInstance().currentUser!!.email


            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })


    }
}
