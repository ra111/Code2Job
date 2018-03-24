package com.studyx.urgames.code2job

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.net.Uri
import android.support.v4.app.NotificationCompat
import android.util.Log

import com.google.firebase.messaging.RemoteMessage

/**
 * Created by rahula on 09/03/18.
 */

class MyFirebaseMessagingService : com.google.firebase.messaging.FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {

        //It is optional
        Log.e(TAG, "From: " + remoteMessage!!.from!!)
        Log.e(TAG, "Notification Message Body: " + remoteMessage.notification!!.body!!)

        //Calling method to generate notification
        sendNotification(remoteMessage.notification!!.title, remoteMessage.notification!!.body)
    }

    //This method is only generating push notification
    private fun sendNotification(title: String?, messageBody: String?) {
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT)

        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.notify(0, notificationBuilder.build())
    }

    companion object {

        private val TAG = "Android News App"
    }
}
