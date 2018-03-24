package com.studyx.urgames.code2job

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewStub
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.*

/**
 * Created by rahula on 09/11/17.
 */

 class MainActivity : Activity() {
    private var mAuth: FirebaseAuth? = null
    private var mGoogleSignInClient: GoogleSignInClient? = null
    private var google_signIn: SignInButton? = null
    private var mDatabase: DatabaseReference? = null
    internal  lateinit  var button: LinearLayout
    internal lateinit var importPanel: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById<View>(R.id.signIn) as LinearLayout
        val linearLayout = findViewById<View>(R.id.relative) as RelativeLayout
        val animationDrawable = linearLayout.background as AnimationDrawable
        animationDrawable.setEnterFadeDuration(1000)
        animationDrawable.setExitFadeDuration(1500)
        animationDrawable.start()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window

            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = resources.getColor(android.R.color.transparent)

            window.setBackgroundDrawableResource(R.drawable.animationlist)
        }


        mAuth = FirebaseAuth.getInstance()
        mDatabase = FirebaseDatabase.getInstance().reference
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        google_signIn = findViewById<View>(R.id.sign_in_button1) as SignInButton
        google_signIn!!.setOnClickListener {
            importPanel = (findViewById<View>(R.id.stub_import) as ViewStub).inflate()
            button.visibility = View.GONE
            val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                val signInIntent = mGoogleSignInClient!!.signInIntent
                startActivityForResult(signInIntent, 9001)
            } else {
                Toast.makeText(applicationContext, "Oops! no internet connection!", Toast.LENGTH_SHORT).show()
            }
        }


    }


    private fun signIn() {
        val signInIntent = mGoogleSignInClient!!.signInIntent
        startActivityForResult(signInIntent, 9001)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 9001) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult<ApiException>(ApiException::class.java!!)
                firebaseAuthWithGoogle(account)
            } catch (e: ApiException) {
                Log.w(TAG, "Google sign in failed", e)
                Toast.makeText(this, e.message + "GOOGLE SIGN IN FAILED", Toast.LENGTH_LONG).show()
            }

        }
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {

        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        mAuth!!.signInWithCredential(credential)
                .addOnCompleteListener(this) { task ->
                    importPanel.visibility = View.GONE
                    button.visibility = View.VISIBLE
                    if (task.isSuccessful) {

                        // Sign in success, update UI with the signed-in user's information
                        Toast.makeText(applicationContext, "Singed in Sucessfully",
                                Toast.LENGTH_SHORT).show()
                        val user = mAuth!!.currentUser
                        val uid = user!!.uid
                        val current_ref = mDatabase!!.child("users").child(uid).child("current")
                        current_ref.addListenerForSingleValueEvent(object : ValueEventListener {
                            override fun onDataChange(dataSnapshot: DataSnapshot) {
                                val current = dataSnapshot.getValue<String>(String::class.java)

                                if (current == null) {
                                    mDatabase!!.child("users").child(uid).child("current").setValue("1")
                                    mDatabase!!.child("users").child(uid).child("freemium").setValue("1")
                                    mDatabase!!.child("users").child(uid).child("paid").setValue("0")
                                }
                            }

                            override fun onCancelled(databaseError: DatabaseError) {

                            }
                        })
                        val menu = Intent(this@MainActivity, menu::class.java)

                        startActivity(menu)
                    } else {
                        importPanel.visibility = View.GONE
                        button.visibility = View.VISIBLE
                        Toast.makeText(applicationContext, "Authentication failed.",
                                Toast.LENGTH_SHORT).show()

                    }

                    // ...
                }
    }

    public override fun onStart() {
        super.onStart()
        //    Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = mAuth!!.currentUser
        try {
            if (currentUser!!.displayName != null) {
                Toast.makeText(this, "Signed In Successfully", Toast.LENGTH_LONG).show()
                val menu = Intent(this@MainActivity, menu::class.java)

                startActivity(menu)
                // updateUI(currentUser
            }

        } catch (e: Exception) {
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }

    }

    companion object {
        private val TAG = "Google Activity"
    }
}
