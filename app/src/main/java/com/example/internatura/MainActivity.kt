package com.example.internatura

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import kotlinx.coroutines.*
import timber.log.Timber
import java.net.URL

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        Thread {
            val response = URL("https://api.flickr.com/services/rest/?method=flickr.photos" +
                    ".search&api_key=ff49fcd4d4a08aa6aafb6ea3de826464&tags=cat&format" +
                    "=json&nojsoncallback=1").readText()
            Timber.d(response)
        }.start()


       // GlobalScope.launch(Dispatchers.IO) {
       //     val response = URL("https://api.flickr.com/services/rest/?method=flickr.photos" +
       //             ".search&api_key=ff49fcd4d4a08aa6aafb6ea3de826464&tags=cat&format" +
       //             "=json&nojsoncallback=1").readText()
       //     Timber.d(response)

          //  val products = Gson().fromJson(response, Array<Photo>::class.java).toList()

      // }






    }









}


