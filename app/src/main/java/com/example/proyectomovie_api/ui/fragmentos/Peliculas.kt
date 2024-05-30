package com.example.proyectomovie_api.ui.fragmentos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.proyectomovie_api.R
import com.example.proyectomovie_api.data.favorite.addFavoriteBody
import com.example.proyectomovie_api.data.movie.Movie
import com.example.proyectomovie_api.databinding.FragmentPeliculasBinding
import com.example.proyectomovie_api.ui.MainActivity
import com.example.proyectomovie_api.ui.adaptadores.AdaptadorCarouselPeliculas
import com.example.proyectomovie_api.ui.adaptadores.AdaptadorMiListaPeliculas
import com.example.proyectomovie_api.ui.carousel.ImagenCarousel
import com.example.proyectomovie_api.ui.carousel.ImagenCarouselAdaptador
import com.example.proyectomovie_api.ui.view.MyViewModel
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.HeroCarouselStrategy
import com.google.android.material.snackbar.Snackbar
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

        val allImageUrls = listOf(
            ImagenCarousel(118, "https://image.tmdb.org/t/p/original/7Vb317OAaxJqvOXf8B5BaCRJ4kH.jpg"),
            ImagenCarousel(578, "https://image.tmdb.org/t/p/original/sFmTQjoFGXGOny9R1KMJqGSZ28r.jpg"),
            ImagenCarousel(11, "https://image.tmdb.org/t/p/original/xCLEq302jcV4MAKqihiBF91WIYU.jpg"),
            ImagenCarousel(843527, "https://image.tmdb.org/t/p/original/AsJsO9Zac7u2WUEG0JCLyusfgUP.jpg"),
            ImagenCarousel(550, "https://image.tmdb.org/t/p/original/n0ceI4oS4wCad1GPvnf4FMBwBie.jpg"),
            ImagenCarousel(603, "https://image.tmdb.org/t/p/original/nZi1IAiLS4UyW3PVWwN7XZWVX3M.jpg"),
            ImagenCarousel(37165, "https://image.tmdb.org/t/p/original/aCHn2TXYJfzPXQKA6r9mKPbMlUB.jpg"),
            ImagenCarousel(640, "https://image.tmdb.org/t/p/original/eNGfh7YEu2fcyWMITS12qnuMxrw.jpg"),
            ImagenCarousel(10625, "https://image.tmdb.org/t/p/original/pgDSpf7I9373QRqF2p7FeBshmIo.jpg"),
            ImagenCarousel(423, "https://image.tmdb.org/t/p/original/dVr11o9or7AS8AMPfwjSpEU83iU.jpg"),
            ImagenCarousel(44214, "https://image.tmdb.org/t/p/original/nsjh6qceR9ev0yI9ZSJHdWd04G7.jpg"),
            ImagenCarousel(594, "https://image.tmdb.org/t/p/original/zywtNiaZ9r7azrcNdl2j0jOgrkw.jpg"),
            ImagenCarousel(0, "https://image.tmdb.org/t/p/original/topD2LQykPrT9vyQV704kUQXDlo.jpg"),  // Ajuste porque falta el id
            ImagenCarousel(508965, "https://image.tmdb.org/t/p/original/mlxKite1x1PgmIhJgAxNS9eHmH8.jpg"),
            ImagenCarousel(587, "https://image.tmdb.org/t/p/original/bLqUd0tBvKezDr9MEla7k34i3rp.jpg"),
            ImagenCarousel(12429, "https://image.tmdb.org/t/p/original/aHU97KMJ7fSq2pyWcVraBWg9l96.jpg"),
            ImagenCarousel(28178, "https://image.tmdb.org/t/p/original/a5pOEjOLvr04Hr8qktIDM75OZi0.jpg"),
            ImagenCarousel(4951, "https://image.tmdb.org/t/p/original/4ssWRanWTKN9CQ0tfq5S1whP7tr.jpg"),
            ImagenCarousel(38, "https://image.tmdb.org/t/p/original/j6rZkE9ksbMsRhBvk9Az5uBc8LT.jpg"),
            ImagenCarousel(11036, "https://image.tmdb.org/t/p/original/zdXnJqBaGFVtLoPNuMeKfEYUViZ.jpg"),
            ImagenCarousel(22, "https://image.tmdb.org/t/p/original/3rlclw8qHWEX2hYHNi7dAaW1e0.jpg"),
            ImagenCarousel(5528, "https://image.tmdb.org/t/p/original/aHufvTTgyf6h1MDgn2t6d7jEseX.jpg"),
            ImagenCarousel(155, "https://image.tmdb.org/t/p/original/kxCRNTZ96dbftDPt7SQWA3LQCiK.jpg"),
            ImagenCarousel(475557, "https://image.tmdb.org/t/p/original/r0kZNywAeN6Ar75rxDqLlTP5RiJ.jpg"),
            ImagenCarousel(856, "https://image.tmdb.org/t/p/original/mED6iGO5jCD3enjE2IuINrxxZJa.jpg"),
            ImagenCarousel(557, "https://image.tmdb.org/t/p/original/3E9BIEHkOmkMEBddp6JMPNxwfR6.jpg"),
            ImagenCarousel(551332, "https://image.tmdb.org/t/p/original/vNaCGEBjGlEMPPhmJCLVeOdUtXc.jpg"),
            ImagenCarousel(424694, "https://image.tmdb.org/t/p/original/ii8IKYeVLDxz3449sbTglCLtKjb.jpg")

        )


        val randomImageUrls = allImageUrls.shuffled().take(6)

        val imageAdapter = ImagenCarouselAdaptador(randomImageUrls, object : ImagenCarouselAdaptador.MyClick {
            override fun onHolderClick(imagenCarousel: ImagenCarousel) {
                viewModel.getMovieById(imagenCarousel.id, "es-ES").observe(viewLifecycleOwner) { peliCarouel ->
                    if (peliCarouel != null) {
                        viewModel.setPelicula(peliCarouel)
                    }
                    findNavController().navigate(R.id.action_fragmentPeliculas_to_informacion)
                }
            }
        })

        binding.RecyclerViewCarouselPeliculas.layoutManager = CarouselLayoutManager(HeroCarouselStrategy())
        binding.RecyclerViewCarouselPeliculas.adapter = imageAdapter
        imageAdapter.submitList(randomImageUrls)


        viewModel.getPopularMovies().observe(viewLifecycleOwner){pelicula->

            var baseUrl = "https://image.tmdb.org/t/p/original"

            val randomIndices = (0 until 19).shuffled().take(4)

            Glide.with(requireActivity()).load(baseUrl + pelicula[randomIndices[0]].poster_path).into(binding.imPopularPelicula1)
            Glide.with(requireActivity()).load(baseUrl + pelicula[randomIndices[1]].poster_path).into(binding.imPopularPelicula2)
            Glide.with(requireActivity()).load(baseUrl + pelicula[randomIndices[2]].poster_path).into(binding.imPopularPelicula3)
            Glide.with(requireActivity()).load(baseUrl + pelicula[randomIndices[3]].poster_path).into(binding.imPopularPelicula4)

            binding.imPopularPelicula1.setOnClickListener {
                val id = pelicula[randomIndices[0]].id
                viewModel.getMovieById(id, "es-ES").observe(viewLifecycleOwner){ it2 ->
                    if (it2 != null) {
                        viewModel.setPelicula(it2)
                        findNavController().navigate(R.id.action_fragmentPeliculas_to_informacion)
                    }
                }
            }

            binding.imPopularPelicula2.setOnClickListener {
                val id = pelicula[randomIndices[1]].id
                viewModel.getMovieById(id, "es-ES").observe(viewLifecycleOwner){ it2 ->
                    if (it2 != null) {
                        viewModel.setPelicula(it2)
                        findNavController().navigate(R.id.action_fragmentPeliculas_to_informacion)
                    }
                }
            }

            binding.imPopularPelicula3.setOnClickListener {
                val id = pelicula[randomIndices[2]].id
                viewModel.getMovieById(id, "es-ES").observe(viewLifecycleOwner){ it2 ->
                    if (it2 != null) {
                        viewModel.setPelicula(it2)
                        findNavController().navigate(R.id.action_fragmentPeliculas_to_informacion)
                    }
                }
            }

            binding.imPopularPelicula4.setOnClickListener {
                val id = pelicula[randomIndices[3]].id
                viewModel.getMovieById(id, "es-ES").observe(viewLifecycleOwner){ it2 ->
                    if (it2 != null) {
                        viewModel.setPelicula(it2)
                        findNavController().navigate(R.id.action_fragmentPeliculas_to_informacion)
                    }
                }
            }

        }
      viewModel.topRatedMovies(

      ).observe(viewLifecycleOwner){pelicula ->

            var baseUrl = "https://image.tmdb.org/t/p/original"

            val randomIndices = (0 until 19).shuffled().take(4)

            Glide.with(requireActivity()).load(baseUrl + pelicula[randomIndices[0]].poster_path ).into(binding.imRatedPelicula1)
            Glide.with(requireActivity()).load(baseUrl + pelicula[randomIndices[1]].poster_path).into(binding.imRatedPelicula2)
            Glide.with(requireActivity()).load(baseUrl + pelicula[randomIndices[2]].poster_path).into(binding.imRatedPelicula3)
            Glide.with(requireActivity()).load(baseUrl + pelicula[randomIndices[3]].poster_path).into(binding.imRatedPelicula4)

            binding.imRatedPelicula1.setOnClickListener {
                val id = pelicula[randomIndices[0]].id
                viewModel.getMovieById(id, "es-ES").observe(viewLifecycleOwner){ it2 ->
                    if (it2 != null) {
                        viewModel.setPelicula(it2)
                        findNavController().navigate(R.id.action_fragmentPeliculas_to_informacion)
                    }
                }
            }

            binding.imRatedPelicula2.setOnClickListener {
                val id = pelicula[randomIndices[1]].id
                viewModel.getMovieById(id, "es-ES").observe(viewLifecycleOwner){ it2 ->
                    if (it2 != null) {
                        viewModel.setPelicula(it2)
                        findNavController().navigate(R.id.action_fragmentPeliculas_to_informacion)
                    }
                }
            }

            binding.imRatedPelicula3.setOnClickListener {
                val id = pelicula[randomIndices[2]].id
                viewModel.getMovieById(id, "es-ES").observe(viewLifecycleOwner){ it2 ->
                    if (it2 != null) {
                        viewModel.setPelicula(it2)
                        findNavController().navigate(R.id.action_fragmentPeliculas_to_informacion)
                    }
                }
            }
            binding.imRatedPelicula4.setOnClickListener {
                val id = pelicula[randomIndices[3]].id
                viewModel.getMovieById(id, "es-ES").observe(viewLifecycleOwner){ it2 ->
                    if (it2 != null) {
                        viewModel.setPelicula(it2)
                        findNavController().navigate(R.id.action_fragmentPeliculas_to_informacion)
                    }
                }
            }
        }

        viewModel.getSessionID().observe(viewLifecycleOwner){sessionId ->
            viewModel.getAccountID(sessionId).observe(viewLifecycleOwner){accountId ->
                viewModel.getFavoriteMovies(accountId).observe(viewLifecycleOwner){listaFavoritos ->

                    val adaptadorFavoritos = AdaptadorMiListaPeliculas(listaFavoritos, object : AdaptadorMiListaPeliculas.MyClick{
                        override fun onHolderClick(pelicula: Movie) {
                            val id = pelicula.id
                            viewModel.getMovieById(id, "es-ES").observe(viewLifecycleOwner){
                                if (it != null) {
                                    viewModel.setPelicula(it)
                                    findNavController().navigate(R.id.action_fragmentPeliculas_to_informacion)
                                }
                            }
                        }

                        override fun onItemLongClick(pelicula: Movie) {
                            val id = pelicula.id
                            viewModel.getMovieById(id, "es-ES").observe(viewLifecycleOwner){
                                if (it != null) {
                                    val deleteFavorito = addFavoriteBody("movie", id, false)
                                    viewModel.getSessionID().observe(viewLifecycleOwner){ session ->
                                        viewModel.getAccountID(session).observe(viewLifecycleOwner){accountId ->
                                            context?.let { it1 -> viewModel.addToFavorite(it1, accountId, deleteFavorito).observe(viewLifecycleOwner){ respuesta ->
                                                if (respuesta.success){
                                                    val snackbar = Snackbar.make(binding.root, "Pelicula eliminada de favoritos", Snackbar.LENGTH_SHORT)
                                                    snackbar.show()
                                                }else{
                                                    val snackbar = Snackbar.make(binding.root, "Error", Snackbar.LENGTH_SHORT)
                                                    snackbar.show()
                                                }
                                            } }
                                        }
                                    }
                                }
                            }
                        }

                    })
                    binding.RecyclerViewMisFavoritosPeliculas.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                    if (listaFavoritos.size > 0){
                        binding.tvMensajeNingunFav.visibility = View.INVISIBLE
                    }
                    binding.RecyclerViewMisFavoritosPeliculas.adapter = adaptadorFavoritos
                }
            }
        }

    }
}