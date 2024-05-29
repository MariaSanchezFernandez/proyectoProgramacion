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
import com.example.proyectomovie_api.ui.adaptadores.AdaptadorCarouselPeliculas

class ImagenCarouselAdaptador (val listado: List<ImagenCarousel>, val listener: MyClick) : ListAdapter<ImagenCarousel, ImagenCarouselAdaptador.ViewHolder>(DiffCallback()){
    class DiffCallback : DiffUtil.ItemCallback<ImagenCarousel>() {
        override fun areItemsTheSame(oldItem: ImagenCarousel, newItem: ImagenCarousel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ImagenCarousel, newItem: ImagenCarousel): Boolean {
            return oldItem == newItem
        }
    }

    interface MyClick {
        fun onHolderClick(imagenCarousel: ImagenCarousel)
    }

    open class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imageView)

        fun bindData(item: ImagenCarousel) {
            Glide.with(itemView)
                .load(item.url)
                .into(imageView)


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.image_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //holder.bindData(getItem(position))
        val data = listado[position]
        holder.bindData(getItem(position))
        holder.itemView.setOnClickListener{
           listener.onHolderClick(data)
        }
    }
}
