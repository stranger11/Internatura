package com.example.internatura.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.internatura.R

class FlickAdapter(private val images: List<String>,
                   private val onClick: (String) -> Unit) :
    RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.custom_layout,
                parent,
                false)
        return ViewHolder(view)
    }

    override fun getItemCount() = images.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val images = images[position]
        holder.bind(images, onClick)
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private var imageView: ImageView = view.findViewById(R.id.image)

    fun bind(image: String, onClick: (String) -> Unit){
        Glide.with(imageView.context)
                .load(image)
                .into(imageView)

        imageView.setOnClickListener {
            onClick(image)
        }
    }
}