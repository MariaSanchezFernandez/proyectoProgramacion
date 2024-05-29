package com.example.proyectomovie_api.ui.fragmentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
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
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.CarouselSnapHelper
import com.google.android.material.carousel.HeroCarouselStrategy
import com.google.android.material.snackbar.Snackbar

class InformacionSeries : Fragment() {

    private lateinit var binding: FragmentInformacionSeriesBinding
    private val viewModel by activityViewModels<MyViewModel>()


    private lateinit var idioma:String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInformacionSeriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.getSessionID().observe(viewLifecycleOwner){
            viewModel.getAccountDetails(it).observe(viewLifecycleOwner){

                idioma = it.iso_639_1 + "-" + it.iso_3166_1
            }
        }

        super.onViewCreated(view, savedInstanceState)
        viewModel.getSerie().observe(viewLifecycleOwner){ serie ->
            rellenaDatos(serie)

            viewModel.getSerieImages(serie.id).observe(viewLifecycleOwner){ it2 ->
                val sizeRespuesta = it2.backdrops?.size
                val listaURLs = ArrayList<ImagenCarousel>()
                var i = 0
                while(i < sizeRespuesta!!){
                    it2.backdrops?.get(i)?.let {
                        val imagen = ImagenCarousel(i, "https://image.tmdb.org/t/p/original" + it.file_path)
                        listaURLs.add(imagen) }
                    ++i
                }

                val adaptadorSeriesDetalles = ImagenCarouselAdaptador(listaURLs.toList(), object : ImagenCarouselAdaptador.MyClick{
                    override fun onHolderClick(imagenCarousel: ImagenCarousel) {
                    }

                })
                binding.recyclerViewDetallesSerie.layoutManager = CarouselLayoutManager()
                binding.recyclerViewDetallesSerie.adapter = adaptadorSeriesDetalles
                adaptadorSeriesDetalles.submitList(listaURLs)
            }


            binding.floatingbtnWhatchListDetallesSeries.setOnClickListener {
                val data = addWatchListBody("tv", serie.id, true)
                viewModel.addToWatchList(21216522, data).observe(viewLifecycleOwner){
                    if (it.success){
                        val snackbar = Snackbar.make(binding.root, "Serie añadida a tu watchlist", Snackbar.LENGTH_SHORT)
                        snackbar.show()
                    }else{
                        val snackbar = Snackbar.make(binding.root, "Error", Snackbar.LENGTH_SHORT)
                        snackbar.show()
                    }
                }

            }

            binding.floatingbtMiListaDetallesSerie.setOnClickListener {
                val data = addFavoriteBody("tv", serie.id, true)
                viewModel.addToFavorite(requireContext(), 21216522, data).observe(viewLifecycleOwner){
                    if (it.success){
                        val snackbar = Snackbar.make(binding.root, "Serie añadida a tu watchlist", Snackbar.LENGTH_SHORT)
                        snackbar.show()
                    }else{
                        val snackbar = Snackbar.make(binding.root, "Error", Snackbar.LENGTH_SHORT)
                        snackbar.show()
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

            (requireActivity() as MainActivity).supportActionBar?.setTitle(serie.name)

        }
    }
}