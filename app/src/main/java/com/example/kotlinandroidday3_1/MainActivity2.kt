package com.example.kotlinandroidday3_1

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import java.io.IOException

private const val TAG = "MainActivity2"
class MainActivity2 : AppCompatActivity() {
    lateinit var imageView: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        imageView=findViewById(R.id.iv_downloaded_image)
        val filename = intent.getStringExtra("image_uri")
        Log.i(TAG, "onCreate: "+filename)
        imageView.setImageBitmap(loadImageFromStorage(filename!!))

    }
    private fun loadImageFromStorage(fileName: String): Bitmap? {
        return try {
            val inputStream = openFileInput(fileName)
            val bitmap = BitmapFactory.decodeStream(inputStream)
            inputStream.close()
            bitmap
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }
}