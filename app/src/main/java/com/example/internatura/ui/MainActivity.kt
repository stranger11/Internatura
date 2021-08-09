package com.example.internatura.ui

import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.bumptech.glide.request.transition.Transition
import com.example.internatura.R
import com.example.internatura.data.CommentResponse
import com.example.internatura.databinding.ActivityMainBinding
import com.example.internatura.util.URL_STR
import com.google.gson.Gson
import timber.log.Timber
import java.net.URL

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)
        readLog()
        glideDownload()
        //replaceFragment()
    }

    private fun replaceFragment() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, FlickFragment()).commit()
    }

    private fun readLog() {
        Thread {
            val response = URL(URL_STR).readText()
            val gson = Gson()
            val flickResponse = gson.fromJson(response, CommentResponse::class.java)
            val list = flickResponse.photos.photo
            val mapper = list.map {
                photo ->
                "https://farm${photo.farm}.staticflickr" +
                        ".com/${photo.server}/${photo.id}_${photo.secret}_m.jpg"
            }

            Timber.d(mapper.toString())

        }.start()
    }

    private fun glideDownload() {
        Glide.with(this)
                .load("https://farm66.staticflickr.com/65535/51368400054_1ea5d9d3e3_m.jpg")
                .into(mBinding.imageView)
   }
}


