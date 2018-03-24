package com.studyx.urgames.code2job

import android.app.ActionBar
import android.content.Intent
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
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

/**
 * Created by rahula on 15/11/17.
 */

class vid : AppCompatActivity() {
    lateinit var  previos: TextView
    lateinit var next: TextView
    var title: TextView? = null
    lateinit  var next_title: TextView
    lateinit  var prev_title: TextView
    var text: String? = null
    var current: String? = null
    internal var i: Intent? = null
    lateinit internal var importPanel: View
    lateinit  internal var tv: TextView
    lateinit  internal var vidLayout: LinearLayout
    private var mWebChromeClient: MyWebChromeClient? = null
    private var mCustomView: View? = null
    private var mContentView: RelativeLayout? = null
    private var mCustomViewContainer: FrameLayout? = null
    private var mCustomViewCallback: WebChromeClient.CustomViewCallback? = null
    internal val database = FirebaseDatabase.getInstance()
    internal val mDatabase = database.reference
    internal val CourseRef = database.getReference("Course")

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.vid)
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
        tv.text = ab!!.title

        // Set the text color of TextView
        tv.setTextColor(Color.WHITE)
        tv.textSize = 20.0f
        val boldTypeface = Typeface.defaultFromStyle(Typeface.BOLD)

        tv.typeface = boldTypeface

        // Set TextView text alignment to center
        tv.gravity = Gravity.CENTER

        // Set the ActionBar display option
        ab.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM

        // Finally, set the newly created TextView as ActionBar custom view
        ab.customView = tv
        next = findViewById<View>(R.id.next) as TextView
        vidLayout = findViewById<View>(R.id.vid) as LinearLayout
        importPanel = (findViewById<View>(R.id.stub_import) as ViewStub).inflate()
        vidLayout.visibility = View.GONE
        previos = findViewById<View>(R.id.previous) as TextView
        next_title = findViewById<View>(R.id.next_tittle) as TextView
        prev_title = findViewById<View>(R.id.prev_tittle) as TextView
        val current_ref = mDatabase.child("users").child(FirebaseAuth.getInstance().currentUser!!.uid).child("current")
        current_ref.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                current = dataSnapshot.getValue<String>(String::class.java)
                if (current === "1") {
                    previos.visibility = View.GONE
                } else {
                    previos.visibility = View.VISIBLE
                }
                Vid(current)

            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })

        //
        //
        next.setOnClickListener {
            val current_ref = mDatabase.child("users").child(FirebaseAuth.getInstance().currentUser!!.uid).child("current")
            current_ref.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    current = dataSnapshot.getValue<String>(String::class.java)
                    val next = Integer.parseInt(current) + 1
                    mDatabase.child("users").child(FirebaseAuth.getInstance().currentUser!!.uid).child("current").setValue(Integer.toString(next))
                    Vid(next.toString())

                }

                override fun onCancelled(databaseError: DatabaseError) {

                }
            })
        }


        previos.setOnClickListener {
            val current_ref = mDatabase.child("users").child(FirebaseAuth.getInstance().currentUser!!.uid).child("current")
            current_ref.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    current = dataSnapshot.getValue<String>(String::class.java)
                    val prev = Integer.parseInt(current) - 1
                    mDatabase.child("users").child(FirebaseAuth.getInstance().currentUser!!.uid).child("current").setValue(Integer.toString(prev))
                    Vid(prev.toString())


                }

                override fun onCancelled(databaseError: DatabaseError) {

                }
            })
        }


    }

    fun Vid(keys: String?) {
        titles(keys)
        CourseRef.orderByKey().equalTo(keys).addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, s: String) {

                val s1 = dataSnapshot.value!!.toString()
                youtube(s1)

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

    fun youtube(frameVideo: String) {
        val titles = frameVideo.substring(0, frameVideo.indexOf(','))
        val frame = frameVideo.substring(frameVideo.indexOf(',') + 1, frameVideo.length)
        val html = ("<iframe class=\"youtube-player\" " + "style=\"border: 0; width: 100%; height: 100%;"
                + "padding:0px; margin:0px\" " + "id=\"ytplayer\" type=\"text/html\" "
                + "src=\"http://www.youtube.com/embed/" + frame
                + "?modestbranding=1&showinfo=0&fs=0\" frameborder=\"0\" " + "controls onclick=\"this.play()\">\n" + "</iframe>\n")


        val displayYoutubeVideo = findViewById<View>(R.id.webView) as WebView

        mWebChromeClient = MyWebChromeClient()
        displayYoutubeVideo.webChromeClient = mWebChromeClient
        tv.setText(titles)
        val webSettings = displayYoutubeVideo.settings
        webSettings.javaScriptEnabled = true

        displayYoutubeVideo.loadData(html, "text/html", "utf-8")
        importPanel.visibility = View.GONE
        vidLayout.visibility = View.VISIBLE

    }

    private inner class MyWebChromeClient : WebChromeClient() {
        internal var LayoutParameters = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT)

        override fun onShowCustomView(view: View, callback: WebChromeClient.CustomViewCallback) {
            // if a view already exists then immediately terminate the new one
            if (mCustomView != null) {
                callback.onCustomViewHidden()
                return
            }
            val displayYoutubeVideo = findViewById<View>(R.id.webView) as WebView
            mContentView = findViewById<View>(R.id.rlvid) as RelativeLayout
            mContentView!!.visibility = View.GONE
            mCustomViewContainer = FrameLayout(this@vid)
            mCustomViewContainer!!.layoutParams = LayoutParameters
            mCustomViewContainer!!.setBackgroundResource(android.R.color.black)
            view.layoutParams = LayoutParameters
            mCustomViewContainer!!.addView(view)
            mCustomView = view
            mCustomViewCallback = callback

            mCustomViewContainer!!.visibility = View.VISIBLE
            setContentView(mCustomViewContainer)
        }

        override fun onHideCustomView() {
            if (mCustomView == null) {
                return
            } else {

                // Hide the custom view.
                mCustomView!!.visibility = View.GONE
                // Remove the custom view from its container.
                mCustomViewContainer!!.removeView(mCustomView)
                mCustomView = null
                mCustomViewContainer!!.visibility = View.GONE
                mCustomViewCallback!!.onCustomViewHidden()
                // Show the content view.
                mContentView!!.visibility = View.VISIBLE
                setContentView(mContentView)
            }
        }


    }

    fun titles(keys: String?) {
        val next = Integer.toString(Integer.parseInt(keys) + 1)
        val prev = Integer.toString(Integer.parseInt(keys) - 1)
        CourseRef.orderByKey().equalTo(next).addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, s: String) {

                val s1 = dataSnapshot.value!!.toString()
                val next = s1.substring(0, s1.indexOf(','))
                Log.d("NEXT IS", next)
                next_title.setText(next)


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
        CourseRef.orderByKey().equalTo(prev).addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, s: String) {

                val s1 = dataSnapshot.value!!.toString()
                val prev = s1.substring(0, s1.indexOf(','))
                prev_title.setText(prev)


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

}




