package com.example.internatura.ui

import android.content.Context
import android.content.Intent
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.internatura.R

import com.example.internatura.data.CommentResponse
import com.example.internatura.databinding.CustomLayoutBinding
import com.example.internatura.util.URL_STR
import com.google.gson.Gson
import timber.log.Timber
import java.net.URL

class FlickAdapter(private var images: List<String>) :
    RecyclerView.Adapter<FlickAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var root: ImageView = view.findViewById(R.id.image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.custom_layout,
                parent,
                false)
        return ViewHolder(view)
    }

    override fun getItemCount() = images.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = images[position]
        Glide.with(holder.root.context)
                .load(item)
                .into(holder.root)
        holder.root.setOnClickListener {
            val intent = Intent(holder.root.context, ChosenPhotoActivity::class.java ).apply {
                putExtra("EXTRA", item)
            }
            holder.root.context.startActivity(intent)
        }
    }
}