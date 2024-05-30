package com.example.proyectomovie_api.ui.fragmentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.proyectomovie_api.R
import com.example.proyectomovie_api.data.favorite.addFavoriteBody
import com.example.proyectomovie_api.data.tv.TVShow
import com.example.proyectomovie_api.databinding.FragmentSeriesBinding
import com.example.proyectomovie_api.ui.MainActivity
import com.example.proyectomovie_api.ui.adaptadores.AdaptadorCarouselPeliculas
import com.example.proyectomovie_api.ui.adaptadores.AdaptadorMiListaSerie
import com.example.proyectomovie_api.ui.carousel.ImagenCarousel
import com.example.proyectomovie_api.ui.carousel.ImagenCarouselAdaptador
import com.example.proyectomovie_api.ui.view.MyViewModel
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.HeroCarouselStrategy
import com.google.android.material.snackbar.Snackbar
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

        val allImageUrls = listOf(
            ImagenCarousel(91239, "https://image.tmdb.org/t/p/original/aXa6J5vGZQQOLZZv8fK0w0cd2fm.jpg"),
            ImagenCarousel(105, "https://image.tmdb.org/t/p/original/laXrFmKtkirG3cwuWTQc4XAG3t9.jpg"),
            ImagenCarousel(1409, "https://image.tmdb.org/t/p/original/mcgZDwhMlrFsfpbvCeg5RY5mhiu.jpg"),
            ImagenCarousel(1421, "https://image.tmdb.org/t/p/original/dv4nL8dT45xtD8FVm1NfqnBssAF.jpg"),
            ImagenCarousel(18165, "https://image.tmdb.org/t/p/original/8RZSvebsuWY6XobuWqOdp9q85Sp.jpg"),
            ImagenCarousel(76479, "https://image.tmdb.org/t/p/original/mGVrXeIjyecj6TKmwPVpHlscEmw.jpg"),
            ImagenCarousel(99581, "https://image.tmdb.org/t/p/original/bjeus54Eb5myLU0SRUQdQ9jhkWt.jpg"),
            ImagenCarousel(60625, "https://image.tmdb.org/t/p/original/3jTgoDLnaXfSjr5jCeOgSevFoqH.jpg"),
            ImagenCarousel(71712, "https://image.tmdb.org/t/p/original/edLV34FXx1iFJA3hbZE7SYRSS4m.jpg"),
            ImagenCarousel(1399, "https://image.tmdb.org/t/p/original/jJojoFmsuLPQz8AOdkeV0b686RN.jpg"),
            ImagenCarousel(1396, "https://image.tmdb.org/t/p/original/gc8PfyTqzqltKPW3X0cIVUGmagz.jpg"),
            ImagenCarousel(60574, "https://image.tmdb.org/t/p/original/l8v3gJDlASN0lNn51gR8zQJsu5O.jpg"),
            ImagenCarousel(106379, "https://image.tmdb.org/t/p/original/coaPCIqQBPUZsOnJcWZxhaORcDT.jpg"),
            ImagenCarousel(1402, "https://image.tmdb.org/t/p/original/qK6FZ2tTAMkIUbxeuRGPnxcbMh1.jpg"),
            ImagenCarousel(2316, "https://image.tmdb.org/t/p/original/TOtg28mcyVunnHdUbSjGquIl2W.jpg"),
            ImagenCarousel(87739, "https://image.tmdb.org/t/p/original/5N5dSOrysuquExvn8Gpp5jMEf6u.jpg"),
            ImagenCarousel(63351, "https://image.tmdb.org/t/p/original/go5LNIFH9IdoSwv8j9cNyTk2uPU.jpg"),
            ImagenCarousel(61222, "https://image.tmdb.org/t/p/original/8oPHgn8PLJwSYrNjDjUcJeK0s2m.jpg"),
            ImagenCarousel(87108, "https://image.tmdb.org/t/p/original/900tHlUYUkp7Ol04XFSoAaEIXcT.jpg"),
            ImagenCarousel(71225, "https://image.tmdb.org/t/p/original/1WumoDFSYWogNXnUnQbcqfkQWXs.jpg"),
            ImagenCarousel(456, "https://image.tmdb.org/t/p/original/4InwNJ8yl1L4kf7rZvPHWJFQpSV.jpg"),
            ImagenCarousel(1418, "https://image.tmdb.org/t/p/original/rwYvhVv0vwbulMwxOfEsuAr1JrT.jpg"),
            ImagenCarousel(66732, "https://image.tmdb.org/t/p/original/cZXYaqurgJ2dAownQRVFkg4Ljft.jpg"),
            ImagenCarousel(82856, "https://image.tmdb.org/t/p/original/aZ4fmjMUAhS6PWhVLDDfT0RAdNe.jpg"),
            ImagenCarousel(66806, "https://image.tmdb.org/t/p/original/o9sRNLXzCpWbhxpxK8s7RlBYcrf.jpg"),
            ImagenCarousel(94605, "https://image.tmdb.org/t/p/original/q8eejQcg1bAqImEV8jh8RtBD4uH.jpg"),
            ImagenCarousel(65494, "https://image.tmdb.org/t/p/original/loZ6Vx4THSAQw4BzBCKNoNyqZaN.jpg"),
            ImagenCarousel(75006, "https://image.tmdb.org/t/p/original/nNN4MpzKSJWcntto6vFnXmITbtP.jpg")

        )

        val randomImageUrls = allImageUrls.shuffled().take(6)


        val imageAdapter = ImagenCarouselAdaptador(randomImageUrls, object : ImagenCarouselAdaptador.MyClick {
            override fun onHolderClick(imagenCarousel: ImagenCarousel) {
                viewModel.getSerieById(imagenCarousel.id, "es-ES").observe(viewLifecycleOwner) { serieCarousel ->
                    if (serieCarousel != null) {
                        viewModel.setSerie(serieCarousel)
                        findNavController().navigate(R.id.action_fragmentSeries_to_informacionSeries)
                    }
                }
            }
        })

        binding.RecycledViewCarouselSeries.layoutManager = CarouselLayoutManager(HeroCarouselStrategy())
        binding.RecycledViewCarouselSeries.adapter = imageAdapter
        imageAdapter.submitList(randomImageUrls)

        viewModel.getPopularTVShow().observe(viewLifecycleOwner){series ->

            var baseUrl = "https://image.tmdb.org/t/p/original"

            val randomIndices = (0 until 19).shuffled().take(4)

            Glide.with(requireActivity()).load(baseUrl + series[randomIndices[0]].poster_path).into(binding.imPopularSeries1)
            Glide.with(requireActivity()).load(baseUrl + series[randomIndices[1]].poster_path).into(binding.imPopularSeries2)
            Glide.with(requireActivity()).load(baseUrl + series[randomIndices[2]].poster_path).into(binding.imPopularSeries3)
            Glide.with(requireActivity()).load(baseUrl + series[randomIndices[3]].poster_path).into(binding.imPopularSeries4)

            binding.imPopularSeries1.setOnClickListener {
                val id = series[randomIndices[0]].id
                viewModel.getSerieById(id, "es-ES").observe(viewLifecycleOwner){ it2 ->
                    if (it2 != null) {
                        viewModel.setSerie(it2)
                        findNavController().navigate(R.id.action_fragmentSeries_to_informacionSeries)
                    }
                }
            }

            binding.imPopularSeries2.setOnClickListener {
                val id = series[randomIndices[1]].id
                viewModel.getSerieById(id, "es-ES").observe(viewLifecycleOwner){ it2 ->
                    if (it2 != null) {
                        viewModel.setSerie(it2)
                        findNavController().navigate(R.id.action_fragmentSeries_to_informacionSeries)
                    }
                }
            }

            binding.imPopularSeries3.setOnClickListener {
                val id = series[randomIndices[2]].id
                viewModel.getSerieById(id, "es-ES").observe(viewLifecycleOwner){ it2 ->
                    if (it2 != null) {
                        viewModel.setSerie(it2)
                        findNavController().navigate(R.id.action_fragmentSeries_to_informacionSeries)
                    }
                }
            }
            binding.imPopularSeries4.setOnClickListener {
                val id = series[randomIndices[3]].id
                viewModel.getSerieById(id, "es-ES").observe(viewLifecycleOwner){ it2 ->
                    if (it2 != null) {
                        viewModel.setSerie(it2)
                        findNavController().navigate(R.id.action_fragmentSeries_to_informacionSeries)
                    }
                }
            }
        }

        viewModel.topRatedTVShow().observe(viewLifecycleOwner){series ->

            var baseUrl = "https://image.tmdb.org/t/p/original"

            val randomIndices = (0 until 19).shuffled().take(4)

            Glide.with(requireActivity()).load(baseUrl + series[randomIndices[0]].poster_path).into(binding.imRatedSeries1)
            Glide.with(requireActivity()).load(baseUrl + series[randomIndices[1]].poster_path).into(binding.imRatedSeries2)
            Glide.with(requireActivity()).load(baseUrl + series[randomIndices[2]].poster_path).into(binding.imRatedSeries3)
            Glide.with(requireActivity()).load(baseUrl + series[randomIndices[3]].poster_path).into(binding.imRatedSeries4)

            binding.imRatedSeries1.setOnClickListener {
                val id = series[randomIndices[0]].id
                viewModel.getSerieById(id, "es-ES").observe(viewLifecycleOwner){ it2 ->
                    if (it2 != null) {
                        viewModel.setSerie(it2)
                        findNavController().navigate(R.id.action_fragmentSeries_to_informacionSeries)
                    }
                }
            }

            binding.imRatedSeries2.setOnClickListener {
                val id = series[randomIndices[1]].id
                viewModel.getSerieById(id, "es-ES").observe(viewLifecycleOwner){ it2 ->
                    if (it2 != null) {
                        viewModel.setSerie(it2)
                        findNavController().navigate(R.id.action_fragmentSeries_to_informacionSeries)
                    }
                }
            }

            binding.imRatedSeries3.setOnClickListener {
                val id = series[randomIndices[2]].id
                viewModel.getSerieById(id, "es-ES").observe(viewLifecycleOwner){ it2 ->
                    if (it2 != null) {
                        viewModel.setSerie(it2)
                        findNavController().navigate(R.id.action_fragmentSeries_to_informacionSeries)
                    }
                }
            }
            binding.imRatedSeries4.setOnClickListener {
                val id = series[randomIndices[3]].id
                viewModel.getSerieById(id, "es-ES").observe(viewLifecycleOwner){ it2 ->
                    if (it2 != null) {
                        viewModel.setSerie(it2)
                        findNavController().navigate(R.id.action_fragmentSeries_to_informacionSeries)
                    }
                }
            }
        }

        viewModel.getSessionID().observe(viewLifecycleOwner){sessionId ->
            viewModel.getAccountDetails(sessionId).observe(viewLifecycleOwner){accountId ->
                viewModel.getFavoriteTVShows(21209376).observe(viewLifecycleOwner){listaFavoritos ->

                    val adaptadorFavoritos = AdaptadorMiListaSerie(listaFavoritos, object : AdaptadorMiListaSerie.MyClick {
                        override fun onHolderClick(serie: TVShow) {
                            val id = serie.id
                            viewModel.getSerieById(id, "es-ES").observe(viewLifecycleOwner) {
                                if (it != null) {
                                    viewModel.setSerie(it)
                                    findNavController().navigate(R.id.action_fragmentSeries_to_informacionSeries)
                                }
                            }
                        }

                        override fun onItemLongClick(serie: TVShow) {
                            val id = serie.id
                            viewModel.getSerieById(id, "es-ES").observe(viewLifecycleOwner){
                                if (it != null) {
                                    val deleteFavorito = addFavoriteBody("tv", id, false)
                                    viewModel.getSessionID().observe(viewLifecycleOwner){ session ->
                                        viewModel.getAccountID(session).observe(viewLifecycleOwner){accountId ->
                                            context?.let { it1 -> viewModel.addToFavorite(it1, accountId, deleteFavorito).observe(viewLifecycleOwner){ respuesta ->
                                                if (respuesta.success){
                                                    val snackbar = Snackbar.make(binding.root, "Serie eliminada de favoritos", Snackbar.LENGTH_SHORT)
                                                    snackbar.show()
                                                }else{
                                                    val snackbar = Snackbar.make(binding.root, "Error", Snackbar.LENGTH_SHORT)
                                                    snackbar.show()
                                                }
                                            }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    })
                    binding.RecyclerViewMisFavoritosSeries.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                    binding.RecyclerViewMisFavoritosSeries.adapter = adaptadorFavoritos
                    if (listaFavoritos.size > 0){
                        binding.tvMensajeNoFavSerie.visibility = View.INVISIBLE
                    }
                }
            }
        }
    }
}