package com.example.proyectomovie_api.ui.adaptadores

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyectomovie_api.data.tv.TVShow
import com.example.proyectomovie_api.databinding.MisFavoritosPeliculasBinding

class AdaptadorMiListaSerie(val listado: List<TVShow>) : RecyclerView.Adapter<AdaptadorMiListaSerie.VistaCelda>() {
    inner class VistaCelda (val binding: MisFavoritosPeliculasBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VistaCelda {
        return VistaCelda(MisFavoritosPeliculasBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return listado.size
    }

    override fun onBindViewHolder(holder: VistaCelda, position: Int) {
        val nombre = listado[position]
    }
}