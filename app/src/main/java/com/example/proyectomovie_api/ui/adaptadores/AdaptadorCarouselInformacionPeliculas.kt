package com.example.proyectomovie_api.ui.adaptadores

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectomovie_api.databinding.MiListaPeliculasHolderBinding

class AdaptadorCarouselInformacionPeliculas (val listado: ArrayList<String>) : RecyclerView.Adapter<AdaptadorCarouselInformacionPeliculas.vistaCelda>() {
    inner class vistaCelda(val binding: MiListaPeliculasHolderBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): vistaCelda {
        return vistaCelda(MiListaPeliculasHolderBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    override fun getItemCount(): Int {
        return listado.size
    }

    override fun onBindViewHolder(holder: vistaCelda, position: Int) {
    }
}