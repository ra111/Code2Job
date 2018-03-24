package com.studyx.urgames.code2job

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.LinearLayout
import com.firebase.ui.auth.AuthUI
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds

/**
 * Created by rahula on 13/11/17.
 */

class menu : AppCompatActivity() {
    protected var mDrawerLayout: DrawerLayout? = null
    private var Formula: ImageButton? = null
    private val Vids: ImageButton? = null
    private var Tips: ImageButton? = null
    private var Syllabus: ImageButton? = null
    private var Premium: ImageButton? = null
    private var mInterstitialAd: InterstitialAd? = null
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu)
        MobileAds.initialize(this, "ca-app-pub-7368274303704514~4366450537")
        val constraintLayout = findViewById<View>(R.id.mainContent) as LinearLayout
        val animationDrawable = constraintLayout.background as AnimationDrawable
        supportActionBar!!.setBackgroundDrawable(
                ColorDrawable(Color.parseColor("#505050")))
        mInterstitialAd = InterstitialAd(this)
        mInterstitialAd!!.adUnitId = "ca-app-pub-7368274303704514/8091386200"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window

            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = resources.getColor(android.R.color.transparent)

            window.setBackgroundDrawableResource(R.drawable.animationlist)
        }
        Formula = findViewById<View>(R.id.formula) as ImageButton
        Tips = findViewById<View>(R.id.tips) as ImageButton
        Syllabus = findViewById<View>(R.id.course) as ImageButton
        Premium = findViewById<View>(R.id.premium) as ImageButton

        mInterstitialAd!!.loadAd(AdRequest.Builder().build())

        mInterstitialAd!!.adListener = object : AdListener() {
            override fun onAdClosed() {
                // Load the next interstitial.
                mInterstitialAd!!.loadAd(AdRequest.Builder().build())
            }

        }

        Formula!!.setOnClickListener { view ->
            val i = Intent(view.context, Formula!!::class.java)
            if (mInterstitialAd!!.isLoaded) {
                mInterstitialAd!!.show()
                startActivity(i)
            }
        }
        Tips!!.setOnClickListener { view ->
            val i = Intent(view.context, tips::class.java)
            if (mInterstitialAd!!.isLoaded) {
                mInterstitialAd!!.show()
                startActivity(i)
            }
        }
        Syllabus!!.setOnClickListener { view ->
            val i = Intent(view.context, syllabus::class.java)
            if (mInterstitialAd!!.isLoaded) {
                mInterstitialAd!!.show()
                startActivity(i)
            }
        }
        Premium!!.setOnClickListener { view ->
            val i = Intent(view.context, premium::class.java)
            if (mInterstitialAd!!.isLoaded) {
                mInterstitialAd!!.show()
                startActivity(i)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.overflow_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_facebook -> {
                val uri = Uri.parse("https://www.facebook.com/groups/114673289208612/") // missing 'http://' will cause crashed
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
                return true
            }
            R.id.menu_profile -> {
                val i = Intent(this, account::class.java)
                startActivity(i)
                return true
            }
            R.id.menu_signout -> {
                AuthUI.getInstance()
                        .signOut(this)
                        .addOnCompleteListener {
                            // user is now signed out
                            val i = Intent(applicationContext, MainActivity::class.java)
                            startActivity(i)
                            finish()
                        }
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
