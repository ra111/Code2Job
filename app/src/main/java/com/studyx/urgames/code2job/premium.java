package com.studyx.urgames.code2job;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.vending.billing.IInAppBillingService;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * Created by rahula on 13/01/18.
 */


public class premium extends AppCompatActivity   implements IabBroadcastReceiver.IabBroadcastListener, View.OnClickListener {
    Button Imp,Vids;
    String TAG="THIS";
    TextView tv;
    static final int RC_REQUEST = 10001;
    IabHelper mHelper;boolean mIsPremium = false;
    static final String SKU_PREMIUM = "saved_paper";
    IabBroadcastReceiver mBroadcastReceiver;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference mDatabase = database.getReference();

    IInAppBillingService mService;
    String base64EncodedPublicKey="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAlXdN0bN8VsFYNRT+KtczkL6M7egft3fljFAhYuCLktM/ZIRNNNS/LwB4erZnijDIZqVJUqrSfPFNxsnYcxJaVfRgJ+Eiqxqv2C3fXRNzDTbrAQOA6vJJCLxtH2B+UF5IEPEjjR0EnJIUpYbb00QqjVlfzjbwmJEiQ3WituX/IX14tYGEzNh4eZbpyeain7OUgf/x+LFMDznjSCk81anGUPplD343wXTxiSopfsJb1X/96Lw1in0H+Zfsd0MWaAYUJ5pVWZckbaVroiua3ApCVaMfz51iXZGKRIxXwexV9I3RHTGAXPlHA2MAu6k8ZUBuOB6eGV0AdPillEm9JfPGeQIDAQAB";
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.premium);
        mHelper = new IabHelper(this, base64EncodedPublicKey);
        Intent serviceIntent =
                new Intent("com.android.vending.billing.InAppBillingService.BIND");
        serviceIntent.setPackage("com.android.vending");
        bindService(serviceIntent, mServiceConn, Context.BIND_AUTO_CREATE);
        mHelper.enableDebugLogging(true);

        Imp=(Button)findViewById(R.id.premium_tips);
        Vids=(Button)findViewById(R.id.shortcut);
        Imp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), imp_topics.class);
                startActivity(i);
            }
        });
                Vids.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d(TAG, "Upgrade button clicked; launching purchase flow for upgrade.");
                setWaitScreen(true);

        /* TODO: for security, generate your payload here for verification. See the comments on
         *        verifyDeveloperPayload() for more info. Since this is a SAMPLE, we just use
         *        an empty string, but on a production app you should carefully generate this. */
                String payload = "";

                if (mIsPremium) {
                    try {
                        mHelper.launchPurchaseFlow(premium.this, SKU_PREMIUM, RC_REQUEST,
                                mPurchaseFinishedListener);
                    } catch (IabHelper.IabAsyncInProgressException e) {
                        complain("Error launching purchase flow. Another async operation in progress.");
                        setWaitScreen(false);
                    }
                }
                else if(!mIsPremium)

                {
                    Intent i = new Intent(view.getContext(), vid.class);
                    startActivity(i);
                }
            }
        });
        mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
                               public void onIabSetupFinished(IabResult result) {
                                   Log.d("@HIs", "Setup finished.");

                                   if (!result.isSuccess()) {
                                       // Oh noes, there was a problem.
                                       complain("Problem setting up in-app billing: " + result);
                                       return;
                                   }

                                   // Have we been disposed of in the meantime? If so, quit.
                                   if (mHelper == null) return;
                                   mBroadcastReceiver = new IabBroadcastReceiver(premium.this);
                                   IntentFilter broadcastFilter = new IntentFilter(IabBroadcastReceiver.ACTION);
                                   registerReceiver(mBroadcastReceiver, broadcastFilter);

                                   // IAB is fully set up. Now, let's get an inventory of stuff we own.
                                   Log.d("THIS", "Setup successful. Querying inventory.");
                                   try {
                                       mHelper.queryInventoryAsync(mGotInventoryListener);
                                   } catch (IabHelper.IabAsyncInProgressException e) {
                                       complain("Error querying inventory. Another async operation in progress.");
                                   }
                               }
                           });

        getSupportActionBar().setBackgroundDrawable(
        new ColorDrawable(Color.parseColor("#505050")));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();

            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(android.R.color.transparent));

            window.setBackgroundDrawableResource(R.drawable.animationlist);
        }
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
            public void onIabSetupFinished(IabResult result) {
                if (!result.isSuccess()) {
                    // Oh no, there was a problem.
                    Log.d("THIS", "Problem setting up In-app Billing: " + result);
                }
                // Hooray, IAB is fully set up!
            }
        });


                // Create a TextView programmatically.
        tv = new TextView(getApplicationContext());

        // Create a LayoutParams for TextView
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT, // Width of TextView
                RelativeLayout.LayoutParams.WRAP_CONTENT); // Height of TextView

        // Apply the layout parameters to TextView widget
        tv.setLayoutParams(lp);

        // Set text to display in TextView
        tv.setText("PREMUIM");

        // Set the text color of TextView
        tv.setTextColor(Color.WHITE);
        tv.setTextSize(20.0f);
        Typeface boldTypeface = Typeface.defaultFromStyle(Typeface.BOLD);

        tv.setTypeface(boldTypeface);

        // Set TextView text alignment to center
        tv.setGravity(Gravity.CENTER);

        // Set the ActionBar display option
        ab.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        // Finally, set the newly created TextView as ActionBar custom view
        ab.setCustomView(tv);
    }
    ServiceConnection mServiceConn = new ServiceConnection() {
        @Override
        public void onServiceDisconnected(ComponentName name) {
            mService = null;
        }

        @Override
        public void onServiceConnected(ComponentName name,
                                       IBinder service) {
            mService = IInAppBillingService.Stub.asInterface(service);
        }

    };
    public  void onPremiumClicked(View arg0)
    {
        //                Intent i = new Intent(view.getContext(), vid.class);
//                startActivity(i);


    }
    IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener = new IabHelper.OnIabPurchaseFinishedListener() {
        public void onIabPurchaseFinished(IabResult result, Purchase purchase) {
            Log.d(TAG, "Purchase finished: " + result + ", purchase: " + purchase);

            // if we were disposed of in the meantime, quit.
            if (mHelper == null) return;

            if (result.isFailure()) {
                complain("Error purchasing: " + result);
                setWaitScreen(false);
                return;
            }
            if (!verifyDeveloperPayload(purchase)) {
                complain("Error purchasing. Authenticity verification failed.");
                setWaitScreen(false);
                return;
            }

            Log.d(TAG, "Purchase successful.");
            Log.d(TAG, "Purchase is premium upgrade. Congratulating user.");
            alert("Thank you for upgrading to premium!");
            mIsPremium = true;

            setWaitScreen(false);
        }


    };

    IabHelper.QueryInventoryFinishedListener mGotInventoryListener = new IabHelper.QueryInventoryFinishedListener() {
        public void onQueryInventoryFinished(IabResult result, Inventory inventory) {
            Log.d(TAG, "Query inventory finished.");

            // Have we been disposed of in the meantime? If so, quit.
            if (mHelper == null) return;

            // Is it a failure?
            if (result.isFailure()) {
                complain("Failed to query inventory: " + result);
                return;
            }

            Log.d(TAG, "Query inventory was successful.");

            /*
             * Check for items we own. Notice that for each purchase, we check
             * the developer payload to see if it's correct! See
             * verifyDeveloperPayload().
             */

            // Do we have the premium upgrade?
            Purchase premiumPurchase = inventory.getPurchase(SKU_PREMIUM);
            mIsPremium = (premiumPurchase != null && verifyDeveloperPayload(premiumPurchase));
            Log.d(TAG, "User is " + (mIsPremium ? "PREMIUM" : "NOT PREMIUM"));
        }
    };
        boolean verifyDeveloperPayload(Purchase p) {
            String payload = p.getDeveloperPayload();

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

            return true;
        }

        void complain(String message) {
            Log.e("THIS", "**** TrivialDrive Error: " + message);
            alert("Error: " + message);
        }

        void alert(String message) {
            AlertDialog.Builder bld = new AlertDialog.Builder(this);
            bld.setMessage(message);
            bld.setNeutralButton("OK", null);
            Log.d("THIS", "Showing alert dialog: " + message);
            bld.create().show();
        }



    @Override
    public void receivedBroadcast() {
        Log.d("THIS", "Received broadcast notification. Querying inventory.");
        try {
            mHelper.queryInventoryAsync(mGotInventoryListener);
        } catch (IabHelper.IabAsyncInProgressException e) {
            complain("Error querying inventory. Another async operation in progress.");
        }

    }
    void setWaitScreen(boolean set) {
//        findViewById(R.id.screen_main).setVisibility(set ? View.GONE : View.VISIBLE);
//        findViewById(R.id.screen_wait).setVisibility(set ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onClick(View view) {

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (mHelper != null
                && !mHelper.handleActivityResult(requestCode, resultCode, data)) {
// not handled, so handle it ourselves (here's where you'd
// perform any handling of activity results not related to in-app
// billing...
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}

