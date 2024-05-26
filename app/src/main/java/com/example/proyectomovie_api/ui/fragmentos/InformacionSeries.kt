package com.example.proyectomovie_api.ui.fragmentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.proyectomovie_api.data.tv.TVShow
import com.example.proyectomovie_api.databinding.FragmentInformacionSeriesBinding
import com.example.proyectomovie_api.ui.MainActivity
import com.example.proyectomovie_api.ui.view.MyViewModel
import com.example.proyectomovie_api.watchlist.addWatchListBody

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

            binding.floatingbtnWhatchListDetallesSeries.setOnClickListener {
                val data = addWatchListBody("tv", it.id, true)
                viewModel.addToWatchList("1234",123124, data)
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