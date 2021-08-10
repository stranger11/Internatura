package com.example.internatura.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.internatura.R
import com.example.internatura.databinding.ActivityChosenPhotoBinding
import com.example.internatura.databinding.ActivityMainBinding

class ChosenPhotoActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityChosenPhotoBinding
    private lateinit var stringLink: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityChosenPhotoBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        fullSizePhoto()
    }

    private fun fullSizePhoto() {
        stringLink = intent.getStringExtra("EXTRA").toString()
        Glide.with(this).load(stringLink).into(mBinding.chosenImageView)
    }
}