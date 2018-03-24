package com.studyx.urgames.code2job

import android.util.Log

import com.google.firebase.iid.FirebaseInstanceId

/**
 * Created by rahula on 09/03/18.
 */

class MyFirebaseInstanceIdService : com.google.firebase.iid.FirebaseInstanceIdService() {

    override fun onTokenRefresh() {

        //Getting registration token
        val refreshedToken = FirebaseInstanceId.getInstance().token

        //Displaying token in logcat
        Log.e(TAG, "Refreshed token: " + refreshedToken!!)

    }

    private fun sendRegistrationToServer(token: String) {
        //You can implement this method to store the token on your server
        //Not required for current project
    }

    companion object {

        private val TAG = "MyFirebaseIIDService"
    }
}
