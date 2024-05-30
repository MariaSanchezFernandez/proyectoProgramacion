package com.example.proyectomovie_api.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyectomovie_api.data.movie.Movie
import com.example.proyectomovie_api.data.watchlist.addWatchListBody
import com.example.proyectomovie_api.databinding.HolderCarteleraFavBinding

class AdapterWLMoviesFav(
    val lista: List<addWatchListBody>,
    val listener: FavClick
) : RecyclerView.Adapter<AdapterWLMoviesFav.FavoritoHolder>() {

    private var _binding: HolderCarteleraFavBinding? = null
    private val binding get() = _binding!!

    fun interface FavClick {
        fun onHolderClick(addWatchListBody: addWatchListBody)
    }

    inner class FavoritoHolder(val binding: HolderCarteleraFavBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritoHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = HolderCarteleraFavBinding.inflate(layoutInflater, parent, false)
        return FavoritoHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoritoHolder, position: Int) {
        val watchList = lista[position]
        Glide.with(holder.itemView.context)
            .load(watchList)
            //.load(watchList.poster_path)
            .into(holder.binding.imgPortada)

        holder.binding.clHolderFav.setOnClickListener{
            // Click en la portada para enviarte a los detalles de la película
        }
    }

    override fun getItemCount(): Int {
        return lista.size
    }

}