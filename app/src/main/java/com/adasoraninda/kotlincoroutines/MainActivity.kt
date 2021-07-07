package com.adasoraninda.kotlincoroutines

import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
    private lateinit var image: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        image = findViewById(R.id.image)

        val mainLooper = mainLooper

        GlobalScope.launch {
            val imageUrl = URL("https://wallpaperplay.com/walls/full/1/c/7/38027.jpg")

            val connection = imageUrl.openConnection() as HttpURLConnection
            connection.doInput = true
            connection.connect()

            val inputStream = connection.inputStream
            val bitmap = BitmapFactory.decodeStream(inputStream)

            Handler(mainLooper).post {
                image.setImageBitmap(bitmap)
            }
        }
    }
}