package com.example.proyectomovie_api.ui.adaptadores

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyectomovie_api.data.movie.Movie
import com.example.proyectomovie_api.databinding.FragmentInicioBinding
import com.example.proyectomovie_api.ui.fragmentos.Inicio

class AdaptadorMovieTrending (val movie : ArrayList<Movie>, val listener : MiClick) : RecyclerView.Adapter<AdaptadorMovieTrending.MovieTrending>() {

    interface MiClick{
        fun onHolderClick(movie: Movie)
    }

    inner class MovieTrending (val binding: FragmentInicioBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieTrending {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = FragmentInicioBinding.inflate(layoutInflater, parent, false)
        return MovieTrending(binding)
    }

    override fun getItemCount(): Int {
        return movie.size
    }

    override fun onBindViewHolder(holder: MovieTrending, position: Int) {
        val movies : Movie = movie[position]

        holder.binding.tTituloDia.text = movies.title
        holder.binding.tFechaDia.text = movies.release_date
        Glide.with(holder.itemView).load(movies.poster_path).into(holder.binding.ivCarteleraDia)
    }
}