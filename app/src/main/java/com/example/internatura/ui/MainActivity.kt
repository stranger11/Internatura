package com.example.internatura.ui

import android.content.Intent
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
import com.example.internatura.util.EXTRA
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
        parseJsonAndDownloadPhotos()
    }

    private fun parseJsonAndDownloadPhotos() {
       Thread {
           val response = URL(URL_STR).readText()
           val gson = Gson()
           val flickResponse = gson.fromJson(response, CommentResponse::class.java)
           val list = flickResponse.photos.photo
           val listOfLinks = list.map {
                photo ->
                "https://farm${photo.farm}.staticflickr" +
                        ".com/${photo.server}/${photo.id}_${photo.secret}_m.jpg"
            }
           Timber.d(listOfLinks.toString())
           openPhotoInFullScreen(listOfLinks)
           initRecyclerView()
        }.start()
    }

    private fun initRecyclerView() {
        mBinding.recyclerView.layoutManager = StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL)
    }

    private fun openPhotoInFullScreen(images: List<String>) {
        runOnUiThread { mBinding.recyclerView.adapter = FlickAdapter(images = images) {
            val intent = Intent(this, FullScreenPhotoActivity::class.java )
                    .apply {
                        putExtra(EXTRA, it)
                    }
            startActivity(intent)
        } }
    }
}


