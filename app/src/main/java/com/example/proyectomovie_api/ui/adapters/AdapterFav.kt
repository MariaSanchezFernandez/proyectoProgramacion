package com.example.proyectomovie_api.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyectomovie_api.data.movie.Movie
import com.example.proyectomovie_api.databinding.HolderCarteleraFavBinding

class AdapterFav(
    val lista: List<Movie>,
    val listener: FavClick
) : RecyclerView.Adapter<AdapterFav.FavoritoHolder>() {

    private lateinit var binding: HolderCarteleraFavBinding

    fun interface FavClick {
        fun onFavClick(movie: Movie)
    }

    inner class FavoritoHolder(val binding: HolderCarteleraFavBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritoHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = HolderCarteleraFavBinding.inflate(layoutInflater, parent, false)
        return FavoritoHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoritoHolder, position: Int) {
        val favorito = lista[position]
        Glide.with(holder.itemView.context)
            //.with(holder.itemView.context)
            //.with(holder.binding.imgPortada)
            .load(favorito.poster_path)
            .into(holder.binding.imgPortada)

        holder.binding.clHolderFav.setOnClickListener {
            // Click en la portada para enviarte a los detalles de la película
        }
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    /*
    fun updateMovieList(arrayList: List<Movie>) {
        lista.clear()
        lista.addAll(arrayList)
        notifyDataSetChanged()
    }
    */

}