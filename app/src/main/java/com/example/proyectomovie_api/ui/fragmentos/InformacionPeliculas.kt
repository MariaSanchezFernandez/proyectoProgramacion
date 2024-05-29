package com.example.proyectomovie_api.ui.fragmentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.proyectomovie_api.data.favorite.addFavoriteBody
import com.example.proyectomovie_api.data.movie.Movie
import com.example.proyectomovie_api.data.movie_detalles.MovieDetallesResponse
import com.example.proyectomovie_api.databinding.FragmentInformacionPeliculasBinding
import com.example.proyectomovie_api.ui.MainActivity
import com.example.proyectomovie_api.ui.view.MyViewModel
import com.example.proyectomovie_api.data.watchlist.addWatchListBody
import com.example.proyectomovie_api.ui.carousel.ImagenCarouselAdaptador
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.HeroCarouselStrategy
import com.google.android.material.snackbar.Snackbar

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

            movie.id?.let { viewModel.getMovieImages(it).observe(viewLifecycleOwner){ it2 ->
                val sizeRespuesta = it2?.backdrops?.size
                val listaURLs = ArrayList<String>()
                var i = 0
                while(i < sizeRespuesta!!){
                    it2.backdrops.get(i).let { listaURLs.add("https://image.tmdb.org/t/p/original" + it.file_path) }
                    ++i
                }
                }
            }

            binding.floatingbtnWatchListDetallesPelicula.setOnClickListener {
                viewModel.getSessionID().observe(viewLifecycleOwner){ sessionId ->
                    viewModel.getAccountID(sessionId).observe(viewLifecycleOwner){accountId ->
                        val data = movie.id?.let { it1 -> addWatchListBody("movie", it1, true) }
                        if (data != null) {
                            viewModel.addToWatchList( 21209376, data)
                        }
                        val snackbar = Snackbar.make(binding.root, "Pelicula añadida a tu watchlist", Snackbar.LENGTH_SHORT)
                        snackbar.show()
                    }
                }
            }

            binding.floatingbtMiListaDetallesPelicula.setOnClickListener {
                viewModel.getSessionID().observe(viewLifecycleOwner){ sessionId ->
                    viewModel.getAccountID(sessionId).observe(viewLifecycleOwner){accountId ->
                        val data = movie.id?.let { it1 -> addFavoriteBody("movie", it1, true) }
                        if (data != null) {
                            viewModel.addToFavorite(requireContext(),accountId, data)
                        }
                        val snackbar = Snackbar.make(binding.root, "Pelicula añadida a tus favoritos", Snackbar.LENGTH_SHORT)
                        snackbar.show()
                    }
                }
            }
        }
    }

    private fun rellenaDatos(peli: MovieDetallesResponse) {
        val originalURL = "https://media.themoviedb.org/t/p/original" + peli.backdropPath
        val posterURL = "https://media.themoviedb.org/t/p/w300_and_h450_bestv2" + peli.posterPath
        val backgroundURL = "https://media.themoviedb.org/t/p/w1920_and_h800_multi_faces"
        with(binding) {

            tvTituloDetallesPelicula.text = peli.title
            tvReleaseDateDetallesPelicula.text = peli.releaseDate

            Glide.with(requireContext())
                .load(posterURL)
                .into(binding.ivCartelDetallesPelicula)

            Glide.with(requireContext())
                .load(originalURL)
                .into(binding.ivFondoDetallesPelicula)
            tvGenresDetallesPelicula.text = peli.genres?.get(0)?.name.toString()
            tvOriginCountryDetallesPelicula.text = peli.originCountry?.get(0).toString() + " · "
            tvDuracionDetallesPelicula.text = peli.runtime.toString() + " min"
            tvOverviewDetallesPelicula.text = peli.overview

            (requireActivity() as MainActivity).supportActionBar?.setTitle(peli.title)

        }
    }
}