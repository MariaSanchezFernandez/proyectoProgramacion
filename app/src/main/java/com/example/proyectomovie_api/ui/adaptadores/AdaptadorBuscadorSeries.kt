package com.example.proyectomovie_api.ui.adaptadores

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ListView.FixedViewInfo
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyectomovie_api.data.tv.TVShow
import com.example.proyectomovie_api.databinding.HolderCeldaBuscadorBinding

class AdaptadorBuscadorSeries(val series : List<TVShow>, val listener : AdaptadorBuscadorSeries.MiClick): RecyclerView.Adapter<AdaptadorBuscadorSeries.BuscadorSeries>() {

    interface MiClick{
        fun onHolcerClick(show : TVShow)
    }

    inner class BuscadorSeries(val binding: HolderCeldaBuscadorBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuscadorSeries {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = HolderCeldaBuscadorBinding.inflate(layoutInflater, parent, false)
        return BuscadorSeries(binding)
    }

    override fun getItemCount(): Int {
        return series.size
    }

    override fun onBindViewHolder(holder: BuscadorSeries, position: Int) {
        val tvShow : TVShow = series[position]

        val posterURL = "https://media.themoviedb.org/t/p/w300_and_h450_bestv2" + tvShow.poster_path

        Glide.with(holder.itemView).load(posterURL).into(holder.binding.ivFotoBuscadorPeli)
        holder.binding.tvName.text = tvShow.name
        holder.binding.tvFechaEstrenoPeli.text = tvShow.first_air_date

        holder.itemView.setOnClickListener {
            listener.onHolcerClick(tvShow)
        }
    }


}