package com.example.proyectomovie_api.ui.fragmentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.proyectomovie_api.R
import com.example.proyectomovie_api.databinding.FragmentInicioBinding
import com.example.proyectomovie_api.ui.MainActivity
import com.example.proyectomovie_api.ui.view.MyViewModel

class Inicio : Fragment() {


    private lateinit var binding: FragmentInicioBinding
    private val myViewModel by activityViewModels<MyViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding =FragmentInicioBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).supportActionBar?.title = "Inicio"


        myViewModel.getInfoMovie().observe(viewLifecycleOwner){pelis ->

            binding.tTituloDia.text = pelis.
            binding.tFechaDia.text = pelis.release_date
            Glide.with(this).load(pelis.poster_path).into(binding.ivCarteleraDia)
        }

        myViewModel.getInfoPeople().observe(viewLifecycleOwner){people ->

            binding.tvNameActor.text = people.name
            binding.tvParticipated.text = people.known_for_department
            Glide.with(this).load(people.profile_path).into(binding.ivActor)
        }
    }


}