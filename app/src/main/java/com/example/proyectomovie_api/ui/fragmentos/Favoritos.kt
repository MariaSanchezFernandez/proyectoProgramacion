package com.example.proyectomovie_api.ui.fragmentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.proyectomovie_api.R
import com.example.proyectomovie_api.data.movie.Movie
import com.example.proyectomovie_api.data.tv.TVShow
import com.example.proyectomovie_api.databinding.FragmentFavoritosBinding
import com.example.proyectomovie_api.ui.adaptadores.AdaptadorMiListaPeliculas
import com.example.proyectomovie_api.ui.adaptadores.AdaptadorMiListaSerie
import com.example.proyectomovie_api.ui.adapters.AdapterFav
import com.example.proyectomovie_api.ui.adapters.AdapterFavTvShows
import com.example.proyectomovie_api.ui.view.MyViewModel

class Favoritos : Fragment() {

    private var _binding: FragmentFavoritosBinding? = null
    private val binding get() = _binding!!
    private val viewModel by activityViewModels<MyViewModel>()
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoritosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configRecyclerMovies()
        configRecyclerTvShows()
        configRecyclerMoviesWl()
        configRecyclerTvShowsWl()

        binding.btVerMas1.setOnClickListener{
            findNavController().navigate(R.id.action_favoritos_to_fragment_btVerMas3_Fav)
        }

        binding.btVerMas2.setOnClickListener{
            findNavController().navigate(R.id.action_favoritos_to_fragment_btVerMas3_Fav)
        }

        binding.btVerMas3.setOnClickListener{
            findNavController().navigate(R.id.action_favoritos_to_fragment_btVerMas3_Fav)
        }

        binding.btVerMas4.setOnClickListener{
            findNavController().navigate(R.id.action_favoritos_to_fragment_btVerMas3_Fav)
        }

    }

    private fun configRecyclerMovies() {
        viewModel.getSessionID().observe(viewLifecycleOwner){sessionId ->
            viewModel.getAccountDetails(sessionId).observe(viewLifecycleOwner){accountId ->
                viewModel.getFavoriteMovies(accountId.id).observe(viewLifecycleOwner){listaFavoritos ->
                    val adaptadorFavoritos = AdaptadorMiListaPeliculas(listaFavoritos, object : AdaptadorMiListaPeliculas.MyClick{
                        override fun onHolderClick(pelicula: Movie) {
                            val id = pelicula.id
                            viewModel.getMovieById(id, "es-ES").observe(viewLifecycleOwner){
                                if (it != null) {
                                    viewModel.setPelicula(it)
                                    findNavController().navigate(R.id.action_favoritos_to_informacion)
                                }
                            }
                        }

                    })
                    binding.rvPelis.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                    binding.rvPelis.adapter = adaptadorFavoritos
                }
            }
        }
    }

    private fun configRecyclerTvShows() {
        viewModel.getSessionID().observe(viewLifecycleOwner){sessionId ->
            viewModel.getAccountDetails(sessionId).observe(viewLifecycleOwner){accountId ->
                viewModel.getFavoriteTVShows(accountId.id).observe(viewLifecycleOwner){listaTVShow ->
                    val adaptadorFavoritos = AdaptadorMiListaSerie(listaTVShow, object : AdaptadorMiListaSerie.MyClick{
                        override fun onHolderClick(serie: TVShow) {
                            val id = serie.id
                            viewModel.getMovieById(id, "es-ES").observe(viewLifecycleOwner){
                                if (it != null) {
                                    viewModel.setPelicula(it)
                                    findNavController().navigate(R.id.action_favoritos_to_informacion)
                                }
                            }
                        }
                    })
                    binding.rvSeries.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                    binding.rvSeries.adapter = adaptadorFavoritos
                }
            }
        }
    }

    private fun configRecyclerMoviesWl() {
        viewModel.getSessionID().observe(viewLifecycleOwner){sessionId ->
            viewModel.getAccountID(sessionId).observe(viewLifecycleOwner){accountId ->
                viewModel.getFavoriteWatchListMovies(accountId).observe(viewLifecycleOwner){listaFavoritos ->
                    val adaptadorFavoritos = AdaptadorMiListaPeliculas(listaFavoritos, object : AdaptadorMiListaPeliculas.MyClick{
                        override fun onHolderClick(pelicula: Movie) {
                            val id = pelicula.id
                            viewModel.getMovieById(id, "es-ES").observe(viewLifecycleOwner){
                                if (it != null) {
                                    viewModel.setPelicula(it)
                                    findNavController().navigate(R.id.action_favoritos_to_informacion)
                                }
                            }
                        }

                    })
                    binding.rvWLpelis.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                    binding.rvWLpelis.adapter = adaptadorFavoritos
                }
            }
        }
    }

    private fun configRecyclerTvShowsWl() {
        viewModel.getSessionID().observe(viewLifecycleOwner){sessionId ->
            viewModel.getAccountID(sessionId).observe(viewLifecycleOwner){accountId ->
                viewModel.getFavouriteWatchListTVShows(accountId).observe(viewLifecycleOwner){listaTVShow ->
                    val adaptadorFavoritos = AdaptadorMiListaSerie(listaTVShow, object : AdaptadorMiListaSerie.MyClick{
                        override fun onHolderClick(serie: TVShow) {
                            val id = serie.id
                            viewModel.getMovieById(id, "es-ES").observe(viewLifecycleOwner){
                                if (it != null) {
                                    viewModel.setPelicula(it)
                                    findNavController().navigate(R.id.action_favoritos_to_informacion)
                                }
                            }
                        }

                    })
                    binding.rvWLseries.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                    binding.rvWLseries.adapter = adaptadorFavoritos
                }
            }
        }
    }

}