package com.example.proyectomovie_api.ui.carousel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyectomovie_api.R

import com.example.proyectomovie_api.databinding.ImageItemBinding

class ImagenCarouselAdaptador : ListAdapter<ImagenCarousel,ImagenCarouselAdaptador.ViewHolder>(DiffCallback()) {
    class DiffCallback : DiffUtil.ItemCallback<ImagenCarousel>(){
        override fun areItemsTheSame(oldItem: ImagenCarousel, newItem: ImagenCarousel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ImagenCarousel, newItem: ImagenCarousel): Boolean {
            return oldItem == newItem
        }

    }

    class ViewHolder(iteView: View): RecyclerView.ViewHolder(iteView){
        private val imageView = iteView.findViewById<ImageView>(R.id.imageView)

        fun bindData(item: ImagenCarousel){
            Glide.with(itemView)
                .load(item.url)
                .into(imageView)
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ImagenCarouselAdaptador.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.image_item,parent,false)
        )
    }

    override fun onBindViewHolder(holder: ImagenCarouselAdaptador.ViewHolder, position: Int) {
        val imageItem = getItem(position)
        holder.bindData(imageItem)
    }
}