package com.example.internatura.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.internatura.databinding.ActivityFullScreenPhotoBinding
import com.example.internatura.util.LINK

class FullScreenPhotoActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityFullScreenPhotoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityFullScreenPhotoBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        changeLinkInImage()
    }

    private fun changeLinkInImage() {
        val linkFromFirstActivity = intent.getStringExtra(LINK)
        Glide.with(this).load(linkFromFirstActivity).into(mBinding.fullScreenImage)
    }
}