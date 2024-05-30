package com.example.proyectomovie_api.ui.adaptadores

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyectomovie_api.data.movie.Movie
import com.example.proyectomovie_api.databinding.HolderCeldaBuscadorBinding

class AdaptadorBuscadorPeliculas(val peliculas: List<Movie>, val listener: AdaptadorBuscadorPeliculas.MiClick): RecyclerView.Adapter<AdaptadorBuscadorPeliculas.BuscadorPeliculas>() {

    interface MiClick{
        fun onHolderClick(movie : Movie)
    }

    inner class BuscadorPeliculas(val binding: HolderCeldaBuscadorBinding) : RecyclerView.ViewHolder(binding.root)



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuscadorPeliculas {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = HolderCeldaBuscadorBinding.inflate(layoutInflater, parent, false)
        return BuscadorPeliculas(binding)
    }

    override fun getItemCount(): Int {
        return peliculas.size
    }

    override fun onBindViewHolder(holder: BuscadorPeliculas, position: Int) {
        val movie : Movie =  peliculas[position]

        val posterURL = "https://media.themoviedb.org/t/p/w300_and_h450_bestv2" + movie.poster_path


        Glide.with(holder.itemView).load(posterURL).into(holder.binding.ivFotoBuscadorPeli)

        holder.binding.tvName.text = movie.title
        holder.binding.tvFechaEstrenoPeli.text = movie.release_date

        holder.itemView.setOnClickListener {
            listener.onHolderClick(movie)
        }
    }

}