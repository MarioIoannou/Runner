package com.marioioannou.runner.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.NotificationManager.IMPORTANCE_HIGH
import android.app.NotificationManager.IMPORTANCE_LOW
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_UPDATE_CURRENT
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.lifecycle.LifecycleService
import com.marioioannou.runner.R
import com.marioioannou.runner.ui.MainActivity
import com.marioioannou.runner.utlis.Constants
import timber.log.Timber

class TrackingService : LifecycleService() {

    var isFirstRun = true

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let {
            when(it.action){
                Constants.ACTION_START_OR_RESUME_SERVICE -> {
                    if (isFirstRun){
                        foregroundService()
                        isFirstRun = false
                        Timber.d("Service started")
                    }else{
                        Timber.d("Service resuming...")
                    }
                }
                Constants.ACTION_STOP_SERVICE -> {
                    Timber.d("Service stopped")
                }
                Constants.ACTION_PAUSE_SERVICE -> {
                    Timber.d("Service paused")
                }
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun foregroundService(){
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationChannel(notificationManager)
        val notificationBuilder = NotificationCompat.Builder(this,Constants.NOTIFICATION_CHANNEL_ID)
            .setContentTitle("Runner Tracking")
            .setSmallIcon(R.drawable.runner_logo)
            .setAutoCancel(false)
            .setOngoing(true)
            .setContentText("00:00:00")
            .setContentIntent(getPendingIntent())
            .setColor(resources.getColor(R.color.teal_light))
            .setColorized(true)

        startForeground(Constants.NOTIFICATION_ID,notificationBuilder.build())
    }

    private fun getPendingIntent() = PendingIntent.getActivity(
        this,
        0,
        Intent(this,MainActivity::class.java).also {
            it.action = Constants.ACTION_SHOW_TRACKING_FRAGMENT
        },
        PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
    )

    private fun notificationChannel(notificationManager: NotificationManager){
        val channel = NotificationChannel(
            Constants.NOTIFICATION_CHANNEL_ID,
            Constants.NOTIFICATION_CHANNEL_NAME,
            IMPORTANCE_LOW
        )
        notificationManager.createNotificationChannel(channel)
    }
}