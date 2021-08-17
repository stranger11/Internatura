package com.example.internatura.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.internatura.data.CommentResponse
import com.example.internatura.databinding.ActivityMainBinding
import com.example.internatura.util.ADD_TO_FAVORITES_TEXT
import com.example.internatura.util.EXTRA_LINK_PHOTO
import com.example.internatura.util.URL_TO_FLICKR
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import timber.log.Timber
import java.net.URL

private const val COLUMN_COUNT = 2
private const val REQUEST_CODE_FULL_SCREEN_PHOTO_ACTIVITY = 0

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        downloadPhotos()
    }

    private fun downloadPhotos() {
       Thread {
           val response = URL(URL_TO_FLICKR).readText()
           val gson = Gson()
           val flickResponse = gson.fromJson(response, CommentResponse::class.java)
           val list = flickResponse.photos.photo
           val links = list.map {
                photo ->
                "https://farm${photo.farm}.staticflickr" +
                        ".com/${photo.server}/${photo.id}_${photo.secret}_m.jpg"
            }
           Timber.d(links.toString())
           runOnUiThread { mBinding.recyclerView.adapter = FlickAdapter(images = links) { link ->
               val intent = Intent(this, FullScreenPhotoActivity::class.java )
                       .apply {
                           putExtra(EXTRA_LINK_PHOTO, link)
                       }
               startActivityForResult(intent, REQUEST_CODE_FULL_SCREEN_PHOTO_ACTIVITY)
           } }
           initRecyclerView()
        }.start()
    }


    private fun initRecyclerView() {
        mBinding.recyclerView.layoutManager = StaggeredGridLayoutManager(COLUMN_COUNT,
                StaggeredGridLayoutManager.VERTICAL)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_FULL_SCREEN_PHOTO_ACTIVITY) {
            if (resultCode == Activity.RESULT_OK) {
               showSnackbar()
            }
        }
    }

    private fun showSnackbar() {
        val snackbar = Snackbar.make(
                mBinding.activityMain,
                ADD_TO_FAVORITES_TEXT,
                Snackbar.LENGTH_LONG)
        snackbar.show()
    }
}


