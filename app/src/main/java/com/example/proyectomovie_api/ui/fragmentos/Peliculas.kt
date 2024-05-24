package com.example.proyectomovie_api.ui.fragmentos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
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

        viewModel.getPopularMovies("3fc6d274dd2c1c8f102b25412728f319").observe(viewLifecycleOwner){
            val arrayListFavoritos = arrayListOf(
                (it[0].poster_path),
                (it[1].poster_path),
                (it[2].poster_path),
                (it[3].poster_path),
            )

            var baseUrl = "https://image.tmdb.org/t/p/original"

            val randomIndices = (0 until 19).shuffled().take(4)

            Glide.with(requireActivity()).load(baseUrl + it[randomIndices[0]].poster_path).into(binding.imPopularPelicula1)
            Glide.with(requireActivity()).load(baseUrl + it[randomIndices[1]].poster_path).into(binding.imPopularPelicula2)
            Glide.with(requireActivity()).load(baseUrl + it[randomIndices[2]].poster_path).into(binding.imPopularPelicula3)
            Glide.with(requireActivity()).load(baseUrl + it[randomIndices[3]].poster_path).into(binding.imPopularPelicula4)

            val adaptadorFavoritos = AdaptadorMiListaPeliculas(arrayListFavoritos)

            binding.RecyclerViewMisFavoritosPeliculas.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            binding.RecyclerViewMisFavoritosPeliculas.adapter = adaptadorFavoritos
        }

        viewModel.topRatedMovies("3fc6d274dd2c1c8f102b25412728f319").observe(viewLifecycleOwner){

            val arrayListFavoritos = arrayListOf(
                (it[0].poster_path),
                (it[1].poster_path),
                (it[2].poster_path),
                (it[3].poster_path),
            )

            var baseUrl = "https://image.tmdb.org/t/p/original"

            val randomIndices = (0 until 19).shuffled().take(4)

            Glide.with(requireActivity()).load(baseUrl + it[randomIndices[0]].poster_path).into(binding.imRatedPelicula1)
            Glide.with(requireActivity()).load(baseUrl + it[randomIndices[1]].poster_path).into(binding.imRatedPelicula2)
            Glide.with(requireActivity()).load(baseUrl + it[randomIndices[2]].poster_path).into(binding.imRatedPelicula3)
            Glide.with(requireActivity()).load(baseUrl + it[randomIndices[3]].poster_path).into(binding.imRatedPelicula4)

            val adaptadorFavoritos = AdaptadorMiListaPeliculas(arrayListFavoritos)

            binding.RecyclerViewMisFavoritosPeliculas.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            binding.RecyclerViewMisFavoritosPeliculas.adapter = adaptadorFavoritos
        }
    }
}