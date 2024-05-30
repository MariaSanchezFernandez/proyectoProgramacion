package com.example.proyectomovie_api.ui.adaptadores

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyectomovie_api.data.movie.Movie
import com.example.proyectomovie_api.data.serie_detalles.SerieDetallesResponse
import com.example.proyectomovie_api.data.tv.TVShow
import com.example.proyectomovie_api.databinding.MisFavoritosPeliculasBinding

class AdaptadorMiListaSerie(val listado: List<TVShow>, val listener: MyClick) : RecyclerView.Adapter<AdaptadorMiListaSerie.VistaCelda>() {
    inner class VistaCelda (val binding: MisFavoritosPeliculasBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VistaCelda {
        return VistaCelda(MisFavoritosPeliculasBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    interface MyClick {
        fun onHolderClick(serie: TVShow)
    }

    override fun getItemCount(): Int {
        return listado.size
    }

    override fun onBindViewHolder(holder: VistaCelda, position: Int) {
        val data = listado[position]
        val posterURL = "https://media.themoviedb.org/t/p/w300_and_h450_bestv2" + data.poster_path
        Glide.with(holder.itemView.context)
            .load(posterURL)
            .into(holder.binding.imageView3)

        holder.itemView.setOnClickListener {
            listener.onHolderClick(data)
        }
    }
}