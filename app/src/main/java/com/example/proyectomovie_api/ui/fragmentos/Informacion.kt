package com.example.proyectomovie_api.ui.fragmentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.proyectomovie_api.R
import com.example.proyectomovie_api.data.movie.Movie
import com.example.proyectomovie_api.databinding.FragmentInformacionBinding
import com.example.proyectomovie_api.ui.MainActivity

class Informacion : Fragment() {

    private lateinit var binding: FragmentInformacionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInformacionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    private fun rellenaDatos(peli: Movie) {
        val posterURL = "https://media.themoviedb.org/t/p/w300_and_h450_bestv2/" + peli.poster_path
        val backgroundURL = "https://media.themoviedb.org/t/p/w1920_and_h800_multi_faces/" + peli.backdrop_path
        with(binding) {

            tvTituloDetalles.text = peli.title
            tvReleaseDateDetalles.text = peli.release_date

            Glide.with(requireContext())
                .load(posterURL)
                .into(binding.ivCartelDetalles)

            Glide.with(requireContext())
                .load(backgroundURL)
                .into(binding.ivFondoDetalles)

            (requireActivity() as MainActivity).supportActionBar?.setTitle(peli.title)

        }
    }
}