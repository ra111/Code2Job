package com.studyx.urgames.code2job

import android.app.ActionBar
import android.app.AlertDialog
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import com.android.vending.billing.IInAppBillingService
import com.google.firebase.auth.FirebaseAuth


/**
 * Created by rahula on 13/01/18.
 */


class premium : AppCompatActivity(), IabBroadcastReceiver.IabBroadcastListener, View.OnClickListener {
    internal lateinit var Imp: Button
    internal lateinit var Vids: Button
    internal lateinit var Sample: Button
    internal var TAG = "THIS"
    internal lateinit var tv: TextView

    internal var mHelper: IabHelper? = null
    internal var mIsPremium = false
    internal var payload = FirebaseAuth.getInstance().currentUser!!.email
    internal var mBroadcastReceiver: IabBroadcastReceiver? = null
    internal var mService: IInAppBillingService? = null
    internal var base64EncodedPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlXdN0bN8VsFYNRT+KtczkL6M7egft3fljFAhYuCLktM/ZIRNNNS/LwB4erZnijDIZqVJUqrSfPFNxsnYcxJaVfRgJ+Eiqxqv2C3fXRNzDTbrAQOA6vJJCLxtH2B+UF5IEPEjjR0EnJIUpYbb00QqjVlfzjbwmJEiQ3WituX/IX14tYGEzNh4eZbpyeain7OUgf/x+LFMDznjSCk81anGUPplD343wXTxiSopfsJb1X/96Lw1in0H+Zfsd0MWaAYUJ5pVWZckbaVroiua3ApCVaMfz51iXZGKRIxXwexV9I3RHTGAXPlHA2MAu6k8ZUBuOB6eGV0AdPillEm9JfPGeQIDAQAB"

    internal var mServiceConn: ServiceConnection? = object : ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName) {
            mService = null
        }

        override fun onServiceConnected(name: ComponentName,
                                        service: IBinder) {
            mService = IInAppBillingService.Stub.asInterface(service)
        }

    }
    internal var mPurchaseFinishedListener: IabHelper.OnIabPurchaseFinishedListener = object :IabHelper.OnIabPurchaseFinishedListener {
        override fun onIabPurchaseFinished(result: IabResult, purchase: Purchase?) {
            Log.d(TAG, "Purchase finished: $result, purchase: $purchase")

            // if we were disposed of in the meantime, quit.
            if (mHelper == null) return

            if (result.isFailure) {
                complain("Error purchasing: " + result)
                setWaitScreen(false)
                return
            }
            if (!verifyDeveloperPayload(purchase)) {
                complain("Error purchasing. Authenticity verification failed.")
                setWaitScreen(false)
                return
            }

            Log.d(TAG, "Purchase successful.")
            Log.d(TAG, "Purchase is premium upgrade. Congratulating user.")
            alert("Thank you for upgrading to premium!")
            mIsPremium = true

            setWaitScreen(false)
        } //To change body of created functions use File | Settings | File Templates.
        }




    internal var mGotInventoryListener: IabHelper.QueryInventoryFinishedListener = object:IabHelper.QueryInventoryFinishedListener {
        override fun onQueryInventoryFinished(result: IabResult, inventory: Inventory?) {
            if (mHelper == null) return

            // Is it a failure?
            if (result.isFailure) {
                complain("Failed to query inventory: " + result)
                return
            }

            Log.d(TAG, "Query inventory was successful.")

            /*
             * Check for items we own. Notice that for each purchase, we check
             * the developer payload to see if it's correct! See
             * verifyDeveloperPayload().
             */

            // Do we have the premium upgrade?
            val premiumPurchase = inventory!!.getPurchase(SKU_PREMIUM)
            mIsPremium = premiumPurchase != null && verifyDeveloperPayload(premiumPurchase)
            Log.d(TAG, "User is " + if (mIsPremium) "PREMIUM" else "NOT PREMIUM")
        }


    }
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.premium)
        mHelper = IabHelper(this, base64EncodedPublicKey)
        val serviceIntent = Intent("com.android.vending.billing.InAppBillingService.BIND")
        serviceIntent.`package` = "com.android.vending"
        bindService(serviceIntent, mServiceConn, Context.BIND_AUTO_CREATE)
        mHelper!!.enableDebugLogging(true)

        Imp = findViewById<View>(R.id.premium_tips) as Button
        Vids = findViewById<View>(R.id.shortcut) as Button
        Sample = findViewById<View>(R.id.ask_question) as Button
        Sample.setOnClickListener { view ->
            val i = Intent(view.context, sample_paper::class.java)
            startActivity(i)
        }

        Imp.setOnClickListener { view ->
            if (!mIsPremium) {
                try {
                    mHelper!!.launchPurchaseFlow(this@premium, SKU_PREMIUM, RC_REQUEST,
                            mPurchaseFinishedListener, payload.toString())
                } catch (e: IabHelper.IabAsyncInProgressException) {
                    complain("Error launching purchase flow. Another async operation in progress.")
                    setWaitScreen(false)
                }

            } else if (mIsPremium) {
                val i = Intent(view.context, imp_topics::class.java)
                startActivity(i)
            }
        }



        Vids.setOnClickListener { view ->
            Log.d(TAG, "Upgrade button clicked; launching purchase flow for upgrade.")
            setWaitScreen(true)

            /* TODO: for security, generate your payload here for verification. See the comments on
         *        verifyDeveloperPayload() for more info. Since this is a SAMPLE, we just use
         *        an empty string, but on a production app you should carefully generate this. */


            if (!mIsPremium) {
                try {
                    mHelper!!.launchPurchaseFlow(this@premium, SKU_PREMIUM, RC_REQUEST,
                            mPurchaseFinishedListener, payload.toString())
                } catch (e: IabHelper.IabAsyncInProgressException) {
                    complain("Error launching purchase flow. Another async operation in progress.")
                    setWaitScreen(false)
                }

            } else if (mIsPremium) {
                val i = Intent(view.context, section::class.java)
                startActivity(i)
            }
        }

        mHelper!!.startSetup(object:IabHelper.OnIabSetupFinishedListener {
            override fun onIabSetupFinished(result: IabResult) {

                Log.d("@HIs", "Setup finished.")

                if (!result.isSuccess) {
                    // Oh noes, there was a problem.
                    complain("Problem setting up in-app billing: " + result)
                    return
                }

                // Have we been disposed of in the meantime? If so, quit.
                //                                   if (mHelper == null) return;
                //                                   mBroadcastReceiver = new IabBroadcastReceiver(premium.this);
                //                                   IntentFilter broadcastFilter = new IntentFilter(IabBroadcastReceiver.ACTION);
                //                                   registerReceiver(mBroadcastReceiver, broadcastFilter);

                // IAB is fully set up. Now, let's get an inventory of stuff we own.
                Log.d("THIS", "Setup successful. Querying inventory.")
                try {
                    mHelper!!.queryInventoryAsync(mGotInventoryListener)
                } catch (e: IabHelper.IabAsyncInProgressException) {
                    complain("Error querying inventory. Another async operation in progress.")
                }
            }

        })

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
        tv.text = "PREMUIM"

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

    fun onPremiumClicked(arg0: View) {
        //                Intent i = new Intent(view.getContext(), vid.class);
        //                startActivity(i);


    }

    internal fun verifyDeveloperPayload(p: Purchase?): Boolean {
        val payload_return = p!!.developerPayload
        Log.d("Payload", payload)
/*
         * TODO: verify that the developer payload of the purchase is correct. It will be
         * the same one that you sent when initiating the purchase.
         *
         * WARNING: Locally generating a random string when starting a purchase and
         * verifying it here might seem like a good approach, but this will fail in the
         * case where the user purchases an item on one device and then uses your app on
         * a different device, because on the other device you will not have access to the
         * random string you originally generated.
         *
         * So a good developer payload has these characteristics:
         *
         * 1. If two different users purchase an item, the payload is different between them,
         *    so that one user's purchase can't be replayed to another user.
         *
         * 2. The payload must be such that you can verify it even when the app wasn't the
         *    one who initiated the purchase flow (so that items purchased by the user on
         *    one device work on other devices owned by the user).
         *
         * Using your own server to store and verify developer payloads across app
         * installations is recommended.
         */

        return payload_return == payload
    }

    internal fun complain(message: String) {
        Log.e("THIS", "**** TrivialDrive Error: " + message)
        alert("Error: " + message)
    }

    internal fun alert(message: String) {
        val bld = AlertDialog.Builder(this)
        bld.setMessage(message)
        bld.setNeutralButton("OK", null)
        Log.d("THIS", "Showing alert dialog: " + message)
        bld.create().show()
    }


    override fun receivedBroadcast() {
        Log.d("THIS", "Received broadcast notification. Querying inventory.")
        try {
            mHelper!!.queryInventoryAsync(mGotInventoryListener)
        } catch (e: IabHelper.IabAsyncInProgressException) {
            complain("Error querying inventory. Another async operation in progress.")
        }

    }

    internal fun setWaitScreen(set: Boolean) {
        //        findViewById(R.id.screen_main).setVisibility(set ? View.GONE : View.VISIBLE);
        //        findViewById(R.id.screen_wait).setVisibility(set ? View.VISIBLE : View.GONE);
    }

    override fun onClick(view: View) {

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        if (mHelper != null && !mHelper!!.handleActivityResult(requestCode, resultCode, data)) {
            // not handled, so handle it ourselves (here's where you'd
            // perform any handling of activity results not related to in-app
            // billing...
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    public override fun onDestroy() {
        super.onDestroy()

        if (mServiceConn != null) {
            unbindService(mServiceConn)
        }
    }

    companion object {
        internal val RC_REQUEST = 10001
        internal val SKU_PREMIUM = "saved_paper"
    }
}
