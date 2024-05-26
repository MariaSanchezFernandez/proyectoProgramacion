package com.example.proyectomovie_api.ui.fragmentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.proyectomovie_api.data.movie.Movie
import com.example.proyectomovie_api.databinding.FragmentInformacionPeliculasBinding
import com.example.proyectomovie_api.ui.MainActivity
import com.example.proyectomovie_api.ui.view.MyViewModel
import com.example.proyectomovie_api.watchlist.addWatchListBody

class InformacionPeliculas : Fragment() {

    private lateinit var binding: FragmentInformacionPeliculasBinding
    private val viewModel by activityViewModels<MyViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInformacionPeliculasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getPelicula().observe(viewLifecycleOwner){ movie ->
            rellenaDatos(movie)

            binding.floatingbtnWatchListDetallesPelicula.setOnClickListener {
                val data = addWatchListBody("movie", movie.id, true)
                viewModel.addToWatchList("test", 21314, data)
            }
        }
    }

    private fun rellenaDatos(peli: Movie) {
        val originalURL = "https://media.themoviedb.org/t/p/original/"
        val posterURL = "https://media.themoviedb.org/t/p/w300_and_h450_bestv2/" + peli.poster_path
        val backgroundURL = "https://media.themoviedb.org/t/p/w1920_and_h800_multi_faces/" + peli.backdrop_path
        with(binding) {

            tvTituloDetallesPelicula.text = peli.title
            tvReleaseDateDetallesPelicula.text = peli.release_date

            Glide.with(requireContext())
                .load(posterURL)
                .into(binding.ivCartelDetallesPelicula)

            Glide.with(requireContext())
                .load(backgroundURL)
                .into(binding.ivFondoDetallesPelicula)

            (requireActivity() as MainActivity).supportActionBar?.setTitle(peli.title)

        }
    }
}