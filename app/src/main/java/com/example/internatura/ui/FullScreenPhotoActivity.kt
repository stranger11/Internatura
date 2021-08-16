package com.example.internatura.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.example.internatura.R
import com.example.internatura.databinding.ActivityFullScreenPhotoBinding
import com.example.internatura.util.EXTRA_LINK_PHOTO

class FullScreenPhotoActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityFullScreenPhotoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityFullScreenPhotoBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        displayPhoto()
    }

    private fun displayPhoto() {
        val linkPhoto = intent.getStringExtra(EXTRA_LINK_PHOTO)
        Glide.with(this).load(linkPhoto).into(mBinding.imagePhoto)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_favorites -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}