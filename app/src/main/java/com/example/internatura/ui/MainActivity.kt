package com.example.internatura.ui

import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.bumptech.glide.request.transition.Transition
import com.example.internatura.R
import com.example.internatura.data.CommentResponse
import com.example.internatura.databinding.ActivityMainBinding
import com.example.internatura.util.URL_STR
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber
import java.net.URL

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        readLog()
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

           runOnUiThread { mBinding.recyclerr.adapter = FlickAdapter(mapper) }
           mBinding.recyclerr.layoutManager = StaggeredGridLayoutManager(2,
                       StaggeredGridLayoutManager.VERTICAL)
        }.start()
    }
}


