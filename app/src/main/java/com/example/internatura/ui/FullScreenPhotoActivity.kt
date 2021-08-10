package com.example.internatura.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.internatura.databinding.ActivityChosenPhotoBinding
import com.example.internatura.util.EXTRA

class FullScreenPhotoActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityChosenPhotoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityChosenPhotoBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        fullSizePhoto()
    }

    private fun fullSizePhoto() {
        val linkFromFirstActivity = intent.getStringExtra(EXTRA)
        Glide.with(this).load(linkFromFirstActivity).into(mBinding.chosenImageView)
    }
}