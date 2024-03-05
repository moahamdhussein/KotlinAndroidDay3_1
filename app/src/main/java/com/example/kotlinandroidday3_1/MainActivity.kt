package com.example.kotlinandroidday3_1

import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    lateinit var btnDownload: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnDownload = findViewById(R.id.btn_download)
        registerReceiver(MyReceiver(), IntentFilter("image_downloaded"))
        btnDownload.setOnClickListener {
            startService(Intent(this, DownloadImage::class.java))
        }

    }
}