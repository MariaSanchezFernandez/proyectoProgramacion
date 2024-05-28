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


        myViewModel.getInfoMovie().observe(viewLifecycleOwner){

            val topMovie = it[0]

            binding.tTituloDia.text = topMovie.title
        }

        myViewModel.getInfoPeople().observe(viewLifecycleOwner){people ->

        }
    }


}