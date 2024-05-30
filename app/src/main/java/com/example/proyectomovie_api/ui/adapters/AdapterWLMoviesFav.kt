package com.example.proyectomovie_api.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyectomovie_api.data.movie.Movie
import com.example.proyectomovie_api.data.watchlist.addWatchListBody
import com.example.proyectomovie_api.databinding.HolderCarteleraFavBinding

class AdapterWLMoviesFav(
    val lista: List<Movie>,
    val listener: FavClick
) : RecyclerView.Adapter<AdapterWLMoviesFav.FavoritoHolder>() {

    private var _binding: HolderCarteleraFavBinding? = null
    private val binding get() = _binding!!

    interface FavClick {
        fun onHolderClick(movie: Movie)
    }

    inner class FavoritoHolder(val binding: HolderCarteleraFavBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritoHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = HolderCarteleraFavBinding.inflate(layoutInflater, parent, false)
        return FavoritoHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoritoHolder, position: Int) {
        val watchList = lista[position]

        val posterURL = "https://media.themoviedb.org/t/p/w300_and_h450_bestv2/" + watchList.poster_path

        Glide.with(holder.itemView.context)
            .load(posterURL)
            //.load(watchList.poster_path)
            .into(holder.binding.imgPortada)

        holder.binding.clHolderFav.setOnClickListener{
            listener.onHolderClick(watchList)
        }
    }

    override fun getItemCount(): Int {
        return lista.size
    }

}