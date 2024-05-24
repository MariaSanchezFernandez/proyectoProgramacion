package com.example.proyectomovie_api.ui.fragmentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.proyectomovie_api.R
import com.example.proyectomovie_api.databinding.FragmentSeriesBinding
import com.example.proyectomovie_api.ui.MainActivity
import com.example.proyectomovie_api.ui.adaptadores.AdaptadorMiListaPeliculas
import com.example.proyectomovie_api.ui.carousel.ImagenCarousel
import com.example.proyectomovie_api.ui.carousel.ImagenCarouselAdaptador
import com.example.proyectomovie_api.ui.view.MyViewModel
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.CarouselSnapHelper
import com.google.android.material.carousel.HeroCarouselStrategy
import java.util.Random
import java.util.UUID

class Series : Fragment() {

    private lateinit var binding: FragmentSeriesBinding
    private val viewModel by activityViewModels<MyViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSeriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).supportActionBar?.title = "Series"

        val imageList = arrayListOf(
            ImagenCarousel(
                UUID.randomUUID().toString(),
                "https://image.tmdb.org/t/p/original/aXa6J5vGZQQOLZZv8fK0w0cd2fm.jpg"
            ),
            ImagenCarousel(
                UUID.randomUUID().toString(),
                "https://image.tmdb.org/t/p/original/laXrFmKtkirG3cwuWTQc4XAG3t9.jpg"
            ),
            ImagenCarousel(
                UUID.randomUUID().toString(),
                "https://image.tmdb.org/t/p/original/mcgZDwhMlrFsfpbvCeg5RY5mhiu.jpg"
            ),
            ImagenCarousel(
                UUID.randomUUID().toString(),
                "https://image.tmdb.org/t/p/original/dv4nL8dT45xtD8FVm1NfqnBssAF.jpg"
            ),
            ImagenCarousel(
                UUID.randomUUID().toString(),
                "https://image.tmdb.org/t/p/original/8RZSvebsuWY6XobuWqOdp9q85Sp.jpg"
            ),
            ImagenCarousel(
                UUID.randomUUID().toString(),
                "https://image.tmdb.org/t/p/original/mGVrXeIjyecj6TKmwPVpHlscEmw.jpg"
            )
        )

        val imageAdapter = ImagenCarouselAdaptador()
        val snapHelper = CarouselSnapHelper()
        binding.RecycledViewCarouselSeries.layoutManager = CarouselLayoutManager(
            HeroCarouselStrategy()
        )
        binding.RecycledViewCarouselSeries.adapter = imageAdapter
        imageAdapter.submitList(imageList)

        viewModel.getPopularTVShow("3fc6d274dd2c1c8f102b25412728f319").observe(viewLifecycleOwner){
            val arrayListFavoritos = arrayListOf(
                (it[0].poster_path),
                (it[1].poster_path),
                (it[2].poster_path),
                (it[3].poster_path),
            )

            var baseUrl = "https://image.tmdb.org/t/p/original"
            val randomIndices = (0 until 19).shuffled().take(4)

            Glide.with(requireActivity()).load(baseUrl + it[randomIndices[0]].poster_path).into(binding.imPopularSeries1)
            Glide.with(requireActivity()).load(baseUrl + it[randomIndices[1]].poster_path).into(binding.imPopularSeries2)
            Glide.with(requireActivity()).load(baseUrl + it[randomIndices[2]].poster_path).into(binding.imPopularSeries3)
            Glide.with(requireActivity()).load(baseUrl + it[randomIndices[3]].poster_path).into(binding.imPopularSeries4)

            val adaptadorFavoritos = AdaptadorMiListaPeliculas(arrayListFavoritos)

            binding.RecyclerViewMisFavoritosSeries.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            binding.RecyclerViewMisFavoritosSeries.adapter = adaptadorFavoritos
        }

        viewModel.topRatedTVShow("3fc6d274dd2c1c8f102b25412728f319").observe(viewLifecycleOwner){

            val arrayListFavoritos = arrayListOf(
                (it[0].poster_path),
                (it[1].poster_path),
                (it[2].poster_path),
                (it[3].poster_path),
            )

            var baseUrl = "https://image.tmdb.org/t/p/original"

            val randomIndices = (0 until 19).shuffled().take(4)

            Glide.with(requireActivity()).load(baseUrl + it[randomIndices[0]].poster_path).into(binding.imRatedSeries1)
            Glide.with(requireActivity()).load(baseUrl + it[randomIndices[1]].poster_path).into(binding.imRatedSeries2)
            Glide.with(requireActivity()).load(baseUrl + it[randomIndices[2]].poster_path).into(binding.imRatedSeries3)
            Glide.with(requireActivity()).load(baseUrl + it[randomIndices[3]].poster_path).into(binding.imRatedSeries4)


            val adaptadorFavoritos = AdaptadorMiListaPeliculas(arrayListFavoritos)

            binding.RecyclerViewMisFavoritosSeries.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            binding.RecyclerViewMisFavoritosSeries.adapter = adaptadorFavoritos
        }
    }
}