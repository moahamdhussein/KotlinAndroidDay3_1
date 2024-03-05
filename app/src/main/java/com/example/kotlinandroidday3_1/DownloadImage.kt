package com.example.kotlinandroidday3_1

import android.annotation.SuppressLint
import android.app.IntentService
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.IOException
import java.net.HttpURLConnection
import java.net.URL


private const val TAG = "DownloadImage"

class DownloadImage : IntentService("DownloadImage") {
    @SuppressLint("ForegroundServiceType")
    override fun onHandleIntent(intent: Intent?) {
        startForeground(100, createNotificationChanel(this).build())
        val bitmapImage = downloadImage()
        saveImageToStorage(bitmapImage)

    }

    private fun downloadImage(): Bitmap? {
        try {
            val url =
                URL("https://images.pexels.com/photos/414612/pexels-photo-414612.jpeg?cs=srgb&dl=pexels-james-wheeler-414612.jpg&fm=jpg")
            val connection = url.openConnection() as HttpURLConnection
            val inputStream = connection.inputStream
            val bitmap = BitmapFactory.decodeStream(inputStream)
            connection.disconnect()
            return bitmap
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }

    private fun saveImageToStorage(bitmap: Bitmap?) {
        val fileName = "downloaded_image.jpg"
        try {
            val outputStream = openFileOutput(fileName, MODE_PRIVATE)
            bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
            sendBroadcast("downloaded_image.jpg")
            outputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun sendBroadcast(imageUri: String) {
        val intent = Intent("image_downloaded")
        intent.putExtra("image_uri", imageUri)
        this.sendBroadcast(intent)
    }
}