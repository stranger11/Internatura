package com.example.internatura.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
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
        val linkFromFirstActivity = intent.getStringExtra(EXTRA_LINK_PHOTO)
        Glide.with(this).load(linkFromFirstActivity).into(mBinding.imagePhoto)
    }
}