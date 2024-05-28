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
import com.example.proyectomovie_api.databinding.FragmentInformacionPeliculasBinding
import com.example.proyectomovie_api.ui.MainActivity
import com.example.proyectomovie_api.ui.view.MyViewModel
import com.example.proyectomovie_api.data.watchlist.addWatchListBody
import com.example.proyectomovie_api.ui.carousel.ImagenCarousel
import com.example.proyectomovie_api.ui.carousel.ImagenCarouselAdaptador
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.CarouselSnapHelper
import com.google.android.material.carousel.HeroCarouselStrategy
import com.google.android.material.snackbar.Snackbar
import java.util.UUID

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

            val respuestaImagenes = viewModel.getMovieImages("test", movie.id)
            val sizeRespuesta = respuestaImagenes.value?.backdrops?.size
            val listaURLs = ArrayList<String>()
            var i = 0
            while(i < sizeRespuesta!!){
                respuestaImagenes.value?.backdrops?.get(i)?.let { listaURLs.add("https://image.tmdb.org/t/p/original" + it.file_path) }
                ++i
            }

            binding.floatingbtnWatchListDetallesPelicula.setOnClickListener {
                val data = addWatchListBody("movie", movie.id, true)
                viewModel.addToWatchList("test", 21314, data)
                val snackbar = Snackbar.make(binding.root, "Pelicula añadida a tu watchlist", Snackbar.LENGTH_SHORT)
                snackbar.show()
            }

            binding.floatingbtMiListaDetallesPelicula.setOnClickListener {
                val data = addFavoriteBody("movie", movie.id, true)
                viewModel.addToFavorite("test", 21314, data)
                val snackbar = Snackbar.make(binding.root, "Pelicula añadida a tus favoritos", Snackbar.LENGTH_SHORT)
                snackbar.show()
            }
            val imageList = listaURLs.map { ImagenCarousel(UUID.randomUUID().toString(), it) }

            val imageAdapter = ImagenCarouselAdaptador()
            binding.recyclerViewDetallesPelicula.layoutManager = CarouselLayoutManager(HeroCarouselStrategy())
            binding.recyclerViewDetallesPelicula.adapter = imageAdapter
            imageAdapter.submitList(imageList)

        }
    }

    private fun rellenaDatos(peli: Movie) {
        val originalURL = "https://media.themoviedb.org/t/p/original"
        val posterURL = "https://media.themoviedb.org/t/p/w300_and_h450_bestv2" + peli.poster_path
        val backgroundURL = "https://media.themoviedb.org/t/p/w1920_and_h800_multi_faces" + peli.backdrop_path
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