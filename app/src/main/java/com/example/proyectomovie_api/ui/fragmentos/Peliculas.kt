package com.example.proyectomovie_api.ui.fragmentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyectomovie_api.R
import com.example.proyectomovie_api.databinding.FragmentPeliculasBinding
import com.example.proyectomovie_api.ui.MainActivity
import com.example.proyectomovie_api.ui.adaptadores.AdaptadorMiListaPeliculas
import com.example.proyectomovie_api.ui.carousel.ImagenCarousel
import com.example.proyectomovie_api.ui.carousel.ImagenCarouselAdaptador
import com.example.proyectomovie_api.ui.view.MyViewModel
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.CarouselSnapHelper
import com.google.android.material.carousel.HeroCarouselStrategy
import java.util.UUID


class Peliculas : Fragment() {
    private lateinit var binding: FragmentPeliculasBinding
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
        binding = FragmentPeliculasBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).supportActionBar?.title = "Peliculas"

        viewModel.getPopularMovies("3fc6d274dd2c1c8f102b25412728f319")

        val imageList = arrayListOf(
            ImagenCarousel(UUID.randomUUID().toString(), "https://image.tmdb.org/t/p/original/7Vb317OAaxJqvOXf8B5BaCRJ4kH.jpg"),
            ImagenCarousel(UUID.randomUUID().toString(), "https://image.tmdb.org/t/p/original/sFmTQjoFGXGOny9R1KMJqGSZ28r.jpg"),
            ImagenCarousel(UUID.randomUUID().toString(), "https://image.tmdb.org/t/p/original/xCLEq302jcV4MAKqihiBF91WIYU.jpg"),
            ImagenCarousel(UUID.randomUUID().toString(), "https://image.tmdb.org/t/p/original/AsJsO9Zac7u2WUEG0JCLyusfgUP.jpg"),
            ImagenCarousel(UUID.randomUUID().toString(), "https://image.tmdb.org/t/p/original/n0ceI4oS4wCad1GPvnf4FMBwBie.jpg"),
            ImagenCarousel(UUID.randomUUID().toString(), "https://image.tmdb.org/t/p/original/nZi1IAiLS4UyW3PVWwN7XZWVX3M.jpg")
        )


        val imageAdapter = ImagenCarouselAdaptador()
        val snapHelper = CarouselSnapHelper()
        binding.RecyclerViewCarouselPeliculas.layoutManager =  CarouselLayoutManager(HeroCarouselStrategy())
        binding.RecyclerViewCarouselPeliculas.adapter = imageAdapter
        imageAdapter.submitList(imageList)



        val arrayListFavoritos = arrayListOf(
            (R.drawable.prueba),
            (R.drawable.prueba),
            (R.drawable.prueba),
            (R.drawable.prueba),
            (R.drawable.prueba),
            (R.drawable.prueba),
            (R.drawable.prueba)
        )

        val adaptadorFavoritos = AdaptadorMiListaPeliculas(arrayListFavoritos)

        binding.RecyclerViewMisFavoritosPeliculas.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.RecyclerViewMisFavoritosPeliculas.adapter = adaptadorFavoritos
    }
}