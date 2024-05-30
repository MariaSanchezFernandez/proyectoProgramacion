package com.example.proyectomovie_api.ui.adaptadores

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyectomovie_api.data.people.People
import com.example.proyectomovie_api.databinding.HolderCeldaBuscadorBinding

class AdaptadorBuscadorPersonas(val person: ArrayList<People>, val listener: MiClick) : RecyclerView.Adapter<AdaptadorBuscadorPersonas.PersonBuscador>(){

    interface MiClick{
        fun onHolderClick(person : People)
    }

    inner class PersonBuscador(val binding : HolderCeldaBuscadorBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonBuscador {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = HolderCeldaBuscadorBinding.inflate(layoutInflater, parent, false)
        return  PersonBuscador(binding)
    }

    override fun getItemCount(): Int {
        return  person.size
    }

    override fun onBindViewHolder(holder: PersonBuscador, position: Int) {
        val persons : People = person[position]
        holder.binding.tvName.text = persons.name
        Glide.with(holder.itemView).load(persons.profile_path).into(holder.binding.imageView4)
    }

}