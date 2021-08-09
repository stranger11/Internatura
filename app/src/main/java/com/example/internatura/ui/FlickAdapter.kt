package com.example.internatura.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.internatura.R
import com.example.internatura.data.CommentResponse
import com.example.internatura.databinding.CustomLayoutBinding

class FlickAdapter(private val imageList: List<String>,
                   private val onClick: (CommentResponse) -> Unit) :
    RecyclerView.Adapter<FlickAdapter.ViewHolder>() {


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //val myImageList: ImageView = view.findViewById(R.id.image)
        val binding = CustomLayoutBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.custom_layout,
                parent,
                false)
        return ViewHolder(view)
    }

    override fun getItemCount() = imageList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = imageList[position]

    }

}