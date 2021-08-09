package com.example.internatura

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import kotlinx.coroutines.*
import timber.log.Timber
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        Thread {
            val response = URL("https://api.flickr.com/services/rest/?method=flickr.photos" +
                    ".search&api_key=ff49fcd4d4a08aa6aafb6ea3de826464&tags=cat&format" +
                    "=json&nojsoncallback=1").readText()
            val json = Gson()

            val d = json.fromJson(response, CommentResponse::class.java)

            Timber.d(d.toString())
        }.start()

    }

}


