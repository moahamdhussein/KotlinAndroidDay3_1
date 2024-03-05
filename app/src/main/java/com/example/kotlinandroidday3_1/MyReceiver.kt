package com.example.kotlinandroidday3_1

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log


private const val TAG = "MyReceiver"

class MyReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Log.i(TAG, "onReceive: ")
        if (intent.action != null && intent.action.equals("image_downloaded")) {
            val imageUriString = intent.getStringExtra("image_uri")
            if (imageUriString != null) {
                startNewActivityWithImage(context, imageUriString)
            }
        }
    }

    private fun startNewActivityWithImage(context: Context, imageUri: String) {
        val intent = Intent(context, MainActivity2::class.java)
        intent.putExtra("image_uri", imageUri)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }
}