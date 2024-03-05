package com.example.kotlinandroidday3_1

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat.getSystemService


private val CHANNEL_ID = "ForegroundServiceChannel"

fun createNotificationChanel(context: Context): NotificationCompat.Builder {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val serviceChannel = NotificationChannel(
            CHANNEL_ID,
            "Foreground Service Channel",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        val manager =
            context.getSystemService(NotificationManager::class.java) as NotificationManager
        manager.createNotificationChannel(serviceChannel)
    }
    val builder = NotificationCompat.Builder(context, CHANNEL_ID)
        .setContentTitle("Download image from URL link")
        .setContentText("download complete")
        .setSmallIcon(R.drawable.ic_launcher_foreground)
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        .setAutoCancel(true)
    return builder

}



