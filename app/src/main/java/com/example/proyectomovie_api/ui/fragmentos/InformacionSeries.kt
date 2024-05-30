package com.example.proyectomovie_api.ui.fragmentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.proyectomovie_api.data.favorite.addFavoriteBody
import com.example.proyectomovie_api.data.serie_detalles.SerieDetallesResponse
import com.example.proyectomovie_api.data.tv.TVShow
import com.example.proyectomovie_api.databinding.FragmentInformacionSeriesBinding
import com.example.proyectomovie_api.ui.MainActivity
import com.example.proyectomovie_api.ui.view.MyViewModel
import com.example.proyectomovie_api.data.watchlist.addWatchListBody
import com.example.proyectomovie_api.ui.adaptadores.AdaptadorCarouselSeries
import com.example.proyectomovie_api.ui.adaptadores.AdaptadorMiListaSerie
import com.example.proyectomovie_api.ui.carousel.ImagenCarousel
import com.example.proyectomovie_api.ui.carousel.ImagenCarouselAdaptador
import com.example.proyectomovie_api.ui.carousel.ImagenCarouselAdaptadorInformacion
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.CarouselSnapHelper
import com.google.android.material.carousel.HeroCarouselStrategy
import com.google.android.material.snackbar.Snackbar

class InformacionSeries : Fragment() {

    private lateinit var binding: FragmentInformacionSeriesBinding
    private val viewModel by activityViewModels<MyViewModel>()


    private lateinit var idioma: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInformacionSeriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.getUserType().observe(viewLifecycleOwner){
            if (it == "Invitado"){
                binding.floatingbtnMiListaDetallesSerie.visibility = View.GONE
                binding.floatingbtnWhatchListDetallesSeries.visibility = View.GONE
            }
        }

        viewModel.getSessionID().observe(viewLifecycleOwner) {
            viewModel.getAccountDetails(it).observe(viewLifecycleOwner) {

                idioma = it.iso_639_1 + "-" + it.iso_3166_1
            }
        }

        super.onViewCreated(view, savedInstanceState)

            binding.floatingbtnWhatchListDetallesSeries.setOnClickListener {
                viewModel.getSessionID().observe(viewLifecycleOwner) { sessionId ->
                    viewModel.getAccountID(sessionId).observe(viewLifecycleOwner) { accountId ->
                        val data = addWatchListBody("tv", serie.id, true)
                        viewModel.addToWatchList(accountId, data).observe(viewLifecycleOwner) {
                            val snackbarPositivo = Snackbar.make(
                                binding.root,
                                "Serie añadida a tu watchlist",
                                Snackbar.LENGTH_SHORT
                            )
                            val snackbarNegativo =
                                Snackbar.make(binding.root, "Error", Snackbar.LENGTH_SHORT)

                            if (it.success) {
                                snackbarPositivo.show()
                            } else {
                                snackbarNegativo.show()
                            }
                        }

        viewModel.getSessionID().observe(viewLifecycleOwner) {
            viewModel.getAccountDetails(it).observe(viewLifecycleOwner) {
                idioma = it.iso_639_1 + "-" + it.iso_3166_1
            }
        }

        viewModel.getSerie().observe(viewLifecycleOwner) { serie ->
            rellenaDatos(serie)

            viewModel.getSerieImages(serie.id).observe(viewLifecycleOwner) { it2 ->
                val listaURLs = it2.backdrops?.mapIndexed { index, backdrop ->
                    ImagenCarousel(index, "https://image.tmdb.org/t/p/original${backdrop.file_path}")
                } ?: emptyList()
                println(listaURLs)
                binding.recyclerViewDetallesSerie.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                binding.recyclerViewDetallesSerie.adapter = ImagenCarouselAdaptadorInformacion(listaURLs)
            }

            binding.floatingbtnMiListaDetallesSerie.setOnClickListener {
                viewModel.getSessionID().observe(viewLifecycleOwner) { sessionId ->
                    viewModel.getAccountID(sessionId).observe(viewLifecycleOwner) { accountId ->
                        val data = addFavoriteBody("tv", serie.id, true)
                        viewModel.addToFavorite(requireContext(), accountId, data).observe(viewLifecycleOwner) {
                              val snackbarPositivo = Snackbar.make(binding.root, "Serie añadida a tus favoritos", Snackbar.LENGTH_SHORT)
                              val snackbarNegativo = Snackbar.make(binding.root, "Error", Snackbar.LENGTH_SHORT)
                                if (it.success) {
                                    snackbarPositivo.show()
                                } else {

                                    snackbarNegativo.show()
                                }
                            }
                    }
                }
            }
            //recyclerViewDetallesSerie
        }
    }

    private fun rellenaDatos(serie: SerieDetallesResponse) {
        val originalURL = "https://media.themoviedb.org/t/p/original/" + serie.backdropPath
        val posterURL = "https://media.themoviedb.org/t/p/w300_and_h450_bestv2/" + serie.posterPath
        val backgroundURL = "https://media.themoviedb.org/t/p/w1920_and_h800_multi_faces/"
        with(binding) {

            tvTituloDetallesSerie.text = serie.name
            tvReleaseDateDetallesSerie.text = serie.firstAirDate

            Glide.with(requireContext())
                .load(posterURL)
                .into(binding.ivCartelDetallesSerie)

            Glide.with(requireContext())
                .load(originalURL)
                .into(binding.ivFondoDetallesSerie)

            tvGenresDetallesSerie.text = serie.genres?.get(0)?.name.toString()
            tvOriginCountryDetallesSerie.text = serie.originCountry?.get(0).toString() + " · "
            tvOverviewDetallesSerie.text = serie.overview
            tvDuracionDetallesSerie.text = serie.numberOfSeasons.toString() + " temporada(s)"
            if (serie.status.equals("Ended")) {
                tvStatusDetallesSerie.text = "Finalizada"
                tvUltimoCapituloDetallesSerie.text =
                    "Última emisión: " + serie.lastEpisodeToAir.airDate
            } else {
                tvUltimoCapituloDetallesSerie.visibility = View.GONE
            }

            (requireActivity() as MainActivity).supportActionBar?.setTitle(serie.name)
        }
    }
}