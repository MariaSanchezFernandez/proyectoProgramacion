package com.example.proyectomovie_api.ui.adaptadores

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyectomovie_api.data.people.People
import com.example.proyectomovie_api.databinding.FragmentInicioBinding

class AdaptadorPersonTrending (val person : ArrayList<People>, val listener: AdaptadorMovieTrending.MiClick) : RecyclerView.Adapter<AdaptadorPersonTrending.PersonTrending>(){

    interface MiClick{
        fun onHolderClick(person : People)
    }

    inner class PersonTrending(val binding : FragmentInicioBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonTrending {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = FragmentInicioBinding.inflate(layoutInflater, parent, false)
        return  PersonTrending(binding)
    }

    override fun getItemCount(): Int {
        return  person.size
    }

    override fun onBindViewHolder(holder: PersonTrending, position: Int) {
        val persons : People = person[position]
        holder.binding.tvNameActor.text = persons.name
        holder.binding.tvParticipated.text = persons.known_for.toString()
        Glide.with(holder.itemView).load(persons.profile_path).into(holder.binding.ivActor)
    }

}