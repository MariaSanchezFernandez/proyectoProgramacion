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
import com.example.proyectomovie_api.databinding.FragmentSeriesBinding
import com.example.proyectomovie_api.ui.MainActivity
import com.example.proyectomovie_api.ui.adaptadores.AdaptadorMiListaSerie
import com.example.proyectomovie_api.ui.carousel.ImagenCarousel
import com.example.proyectomovie_api.ui.carousel.ImagenCarouselAdaptador
import com.example.proyectomovie_api.ui.view.MyViewModel
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.HeroCarouselStrategy
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
            ImagenCarousel("118","https://image.tmdb.org/t/p/original/aXa6J5vGZQQOLZZv8fK0w0cd2fm.jpg"),
            ImagenCarousel("578","https://image.tmdb.org/t/p/original/laXrFmKtkirG3cwuWTQc4XAG3t9.jpg"),
            ImagenCarousel("11","https://image.tmdb.org/t/p/original/mcgZDwhMlrFsfpbvCeg5RY5mhiu.jpg"),
            ImagenCarousel("843527","https://image.tmdb.org/t/p/original/dv4nL8dT45xtD8FVm1NfqnBssAF.jpg"),
            ImagenCarousel("550","https://image.tmdb.org/t/p/original/8RZSvebsuWY6XobuWqOdp9q85Sp.jpg"),
            ImagenCarousel("603","https://image.tmdb.org/t/p/original/mGVrXeIjyecj6TKmwPVpHlscEmw.jpg"),
            ImagenCarousel("37165","https://image.tmdb.org/t/p/original/aCHn2TXYJfzPXQKA6r9mKPbMlUB.jpg"),
            ImagenCarousel("640","https://image.tmdb.org/t/p/original/eNGfh7YEu2fcyWMITS12qnuMxrw.jpg"),
            ImagenCarousel("10625","https://image.tmdb.org/t/p/original/pgDSpf7I9373QRqF2p7FeBshmIo.jpg"),
            ImagenCarousel("423","https://image.tmdb.org/t/p/original/dVr11o9or7AS8AMPfwjSpEU83iU.jpg"),
            ImagenCarousel("44214","https://image.tmdb.org/t/p/original/nsjh6qceR9ev0yI9ZSJHdWd04G7.jpg"),
            ImagenCarousel("594","https://image.tmdb.org/t/p/original/zywtNiaZ9r7azrcNdl2j0jOgrkw.jpg"),
            ImagenCarousel("","https://image.tmdb.org/t/p/original/topD2LQykPrT9vyQV704kUQXDlo.jpg"),
            ImagenCarousel("508965","https://image.tmdb.org/t/p/original/mlxKite1x1PgmIhJgAxNS9eHmH8.jpg"),
            ImagenCarousel("587","https://image.tmdb.org/t/p/original/bLqUd0tBvKezDr9MEla7k34i3rp.jpg"),
            ImagenCarousel("12429","https://image.tmdb.org/t/p/original/aHU97KMJ7fSq2pyWcVraBWg9l96.jpg"),
            ImagenCarousel("28178","https://image.tmdb.org/t/p/original/a5pOEjOLvr04Hr8qktIDM75OZi0.jpg"),
            ImagenCarousel("4951","https://image.tmdb.org/t/p/original/4ssWRanWTKN9CQ0tfq5S1whP7tr.jpg"),
            ImagenCarousel("38","https://image.tmdb.org/t/p/original/j6rZkE9ksbMsRhBvk9Az5uBc8LT.jpg"),
            ImagenCarousel("11036","https://image.tmdb.org/t/p/original/zdXnJqBaGFVtLoPNuMeKfEYUViZ.jpg"),
            ImagenCarousel("22","https://image.tmdb.org/t/p/original/3rlclw8qHWEX2hYHNi7dAaW1e0.jpg"),
            ImagenCarousel("5528","https://image.tmdb.org/t/p/original/aHufvTTgyf6h1MDgn2t6d7jEseX.jpg"),
            ImagenCarousel("155","https://image.tmdb.org/t/p/original/kxCRNTZ96dbftDPt7SQWA3LQCiK.jpg"),
            ImagenCarousel("475557","https://image.tmdb.org/t/p/original/r0kZNywAeN6Ar75rxDqLlTP5RiJ.jpg"),
            ImagenCarousel("856","https://image.tmdb.org/t/p/original/mED6iGO5jCD3enjE2IuINrxxZJa.jpg"),
            ImagenCarousel("557","https://image.tmdb.org/t/p/original/3E9BIEHkOmkMEBddp6JMPNxwfR6.jpg"),
            ImagenCarousel("551332","https://image.tmdb.org/t/p/original/vNaCGEBjGlEMPPhmJCLVeOdUtXc.jpg"),
            ImagenCarousel("424694","https://image.tmdb.org/t/p/original/ii8IKYeVLDxz3449sbTglCLtKjb.jpg")
        )

        // Seleccionar un subconjunto aleatorio de 6 URLs de imágenes
        val randomImageUrls = allImageUrls.shuffled().take(6)
        val imageList = randomImageUrls.map { ImagenCarousel(UUID.randomUUID().toString(), it.url) }

        val imageAdapter = ImagenCarouselAdaptador()
        binding.RecycledViewCarouselSeries.layoutManager = CarouselLayoutManager(HeroCarouselStrategy())
        binding.RecycledViewCarouselSeries.adapter = imageAdapter
        imageAdapter.submitList(imageList)

        viewModel.getPopularTVShow("3fc6d274dd2c1c8f102b25412728f319").observe(viewLifecycleOwner){series ->

            var baseUrl = "https://image.tmdb.org/t/p/original"

            val randomIndices = (0 until 19).shuffled().take(4)

            Glide.with(requireActivity()).load(baseUrl + series[randomIndices[0]].poster_path).into(binding.imPopularSeries1)
            Glide.with(requireActivity()).load(baseUrl + series[randomIndices[1]].poster_path).into(binding.imPopularSeries2)
            Glide.with(requireActivity()).load(baseUrl + series[randomIndices[2]].poster_path).into(binding.imPopularSeries3)
            Glide.with(requireActivity()).load(baseUrl + series[randomIndices[3]].poster_path).into(binding.imPopularSeries4)

            binding.imPopularSeries1.setOnClickListener {
                viewModel.setSerie(series[randomIndices[0]])
                findNavController().navigate(R.id.action_fragmentSeries_to_informacion)
            }

            binding.imPopularSeries2.setOnClickListener {
                viewModel.setSerie(series[randomIndices[1]])
                findNavController().navigate(R.id.action_fragmentSeries_to_informacion)
            }

            binding.imPopularSeries3.setOnClickListener {
                viewModel.setSerie(series[randomIndices[2]])
                findNavController().navigate(R.id.action_fragmentSeries_to_informacion)
            }
            binding.imPopularSeries4.setOnClickListener {
                viewModel.setSerie(series[randomIndices[3]])
                findNavController().navigate(R.id.action_fragmentSeries_to_informacion)
            }
        }

        viewModel.topRatedTVShow("3fc6d274dd2c1c8f102b25412728f319").observe(viewLifecycleOwner){series ->

            var baseUrl = "https://image.tmdb.org/t/p/original"

            val randomIndices = (0 until 19).shuffled().take(4)

            Glide.with(requireActivity()).load(baseUrl + series[randomIndices[0]].poster_path).into(binding.imRatedSeries1)
            Glide.with(requireActivity()).load(baseUrl + series[randomIndices[1]].poster_path).into(binding.imRatedSeries2)
            Glide.with(requireActivity()).load(baseUrl + series[randomIndices[2]].poster_path).into(binding.imRatedSeries3)
            Glide.with(requireActivity()).load(baseUrl + series[randomIndices[3]].poster_path).into(binding.imRatedSeries4)

            binding.imRatedSeries1.setOnClickListener {
                viewModel.setSerie(series[randomIndices[0]])
                findNavController().navigate(R.id.action_fragmentSeries_to_informacion)
            }

            binding.imRatedSeries2.setOnClickListener {
                viewModel.setSerie(series[randomIndices[1]])
                findNavController().navigate(R.id.action_fragmentSeries_to_informacion)
            }

            binding.imRatedSeries3.setOnClickListener {
                viewModel.setSerie(series[randomIndices[2]])
                findNavController().navigate(R.id.action_fragmentSeries_to_informacion)
            }
            binding.imRatedSeries4.setOnClickListener {
                viewModel.setSerie(series[randomIndices[3]])
                findNavController().navigate(R.id.action_fragmentSeries_to_informacion)
            }
        }

        viewModel.getSessionID().observe(viewLifecycleOwner){sessionId ->
            viewModel.getAccountDetails(sessionId).observe(viewLifecycleOwner){accountId ->
                viewModel.getFavoriteTVShows(accountId.id).observe(viewLifecycleOwner){listaFavoritos ->

                    val adaptadorFavoritos = AdaptadorMiListaSerie(listaFavoritos)
                    binding.RecyclerViewMisFavoritosSeries.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                    binding.RecyclerViewMisFavoritosSeries.adapter = adaptadorFavoritos
                }
            }
        }
    }
}