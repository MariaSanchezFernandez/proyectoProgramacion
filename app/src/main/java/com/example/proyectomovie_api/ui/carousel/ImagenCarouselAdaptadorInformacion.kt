package com.example.proyectomovie_api.ui.carousel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyectomovie_api.R
import com.example.proyectomovie_api.databinding.MisFavoritosPeliculasBinding
import com.example.proyectomovie_api.databinding.MisFotosInformacionBinding
import com.example.proyectomovie_api.ui.adaptadores.AdaptadorCarouselInformacionPeliculas


class ImagenCarouselAdaptadorInformacion(val listado: List<ImagenCarousel>) :
    RecyclerView.Adapter<ImagenCarouselAdaptadorInformacion.VistaCelda>() {

    inner class VistaCelda (val binding: MisFotosInformacionBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ImagenCarouselAdaptadorInformacion.VistaCelda {
        return VistaCelda(MisFotosInformacionBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VistaCelda, position: Int) {
        val data = listado[position]
        Glide.with(holder.itemView.context)
            .load(data.url)
            .into(holder.binding.imageView4)
    }

    override fun getItemCount(): Int {
        return listado.size
    }


}
