package com.example.proyectomovie_api.ui.fragmentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.proyectomovie_api.data.favorite.addFavoriteBody
import com.example.proyectomovie_api.data.tv.TVShow
import com.example.proyectomovie_api.databinding.FragmentInformacionSeriesBinding
import com.example.proyectomovie_api.ui.MainActivity
import com.example.proyectomovie_api.ui.view.MyViewModel
import com.example.proyectomovie_api.data.watchlist.addWatchListBody
import com.example.proyectomovie_api.ui.carousel.ImagenCarouselAdaptador
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.CarouselSnapHelper
import com.google.android.material.carousel.HeroCarouselStrategy
import com.google.android.material.snackbar.Snackbar

class InformacionSeries : Fragment() {

    private lateinit var binding: FragmentInformacionSeriesBinding
    private val viewModel by activityViewModels<MyViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInformacionSeriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getSerie().observe(viewLifecycleOwner){ serie ->
            rellenaDatos(serie)

            val respuestaImagenes = viewModel.getSerieImages("test", serie.id)
            val sizeRespuesta = respuestaImagenes.value?.backdrops?.size
            val listaURLs = ArrayList<String>()
            var i = 0
            while(i < sizeRespuesta!!){
                respuestaImagenes.value?.backdrops?.get(i)?.let { listaURLs.add("https://image.tmdb.org/t/p/original" + it.file_path) }
                ++i
            }

            binding.floatingbtnWhatchListDetallesSeries.setOnClickListener {
                val data = addWatchListBody("tv", serie.id, true)
                viewModel.addToWatchList("1234",123124, data)
                val snackbar = Snackbar.make(binding.root, "Serie añadida a tu watchlist", Snackbar.LENGTH_SHORT)
                snackbar.show()
            }

            binding.floatingbtMiListaDetallesSerie.setOnClickListener {
                val data = addFavoriteBody("movie", serie.id, true)
                viewModel.addToFavorite("test", 21314, data)
                val snackbar = Snackbar.make(binding.root, "Serie añadida a tus favoritos", Snackbar.LENGTH_SHORT)
                snackbar.show()
            }
        }
    }

    private fun rellenaDatos(serie: TVShow) {
        val originalURL = "https://media.themoviedb.org/t/p/original/"
        val posterURL = "https://media.themoviedb.org/t/p/w300_and_h450_bestv2/" + serie.poster_path
        val backgroundURL = "https://media.themoviedb.org/t/p/w1920_and_h800_multi_faces/" + serie.backdrop_path
        with(binding) {

            tvTituloDetallesSerie.text = serie.name
            tvReleaseDateDetallesSerie.text = serie.first_air_date

            Glide.with(requireContext())
                .load(posterURL)
                .into(binding.ivCartelDetallesSerie)

            Glide.with(requireContext())
                .load(backgroundURL)
                .into(binding.ivFondoDetallesSerie)

            (requireActivity() as MainActivity).supportActionBar?.setTitle(serie.name)

        }
    }
}