package com.example.proyectomovie_api.ui.fragmentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.proyectomovie_api.R
import com.example.proyectomovie_api.data.movie.Movie
import com.example.proyectomovie_api.data.tv.TVShow
import com.example.proyectomovie_api.data.watchlist.addWatchListBody
import com.example.proyectomovie_api.databinding.FragmentBtVerMas3FavBinding
import com.example.proyectomovie_api.ui.adaptadores.AdaptadorMiListaPeliculas
import com.example.proyectomovie_api.ui.adaptadores.AdaptadorMiListaSerie
import com.example.proyectomovie_api.ui.adapters.AdapterWLMoviesFav
import com.example.proyectomovie_api.ui.adapters.AdapterWLTVShowsFav
import com.example.proyectomovie_api.ui.view.MyViewModel

class fragment_btVerMas3_Fav : Fragment() {

    private var _binding: FragmentBtVerMas3FavBinding? = null
    private val binding get() = _binding!!
    private val ViewModel by  activityViewModels<MyViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBtVerMas3FavBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configRecyclerMoviesInter()
        configRecyclerTvShowsInter()
        configRecyclerMoviesWlInter()
        configRecyclerTvShowsWlInter()

    }

    private fun configRecyclerMoviesInter() {
        ViewModel.getSessionID().observe(viewLifecycleOwner){sessionId ->
            ViewModel.getAccountDetails(sessionId).observe(viewLifecycleOwner){accountId ->
                ViewModel.getFavoriteMovies(accountId.id).observe(viewLifecycleOwner){ listaFavoritos ->
                    val adaptadorFavoritos = AdaptadorMiListaPeliculas(listaFavoritos, object : AdaptadorMiListaPeliculas.MyClick{
                        override fun onHolderClick(pelicula: Movie) {
                            val id = pelicula.id
                            ViewModel.getMovieById(id, "es-ES").observe(viewLifecycleOwner){
                                if (it != null) {
                                    ViewModel.setPelicula(it)
                                    findNavController().navigate(R.id.action_fragment_btVerMas3_Fav_to_informacion)
                                }
                            }
                        }
                        override fun onItemLongClick(pelicula: Movie) {

                        }
                    })
                    binding.rvFavInter.layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, true)
                    //binding.rvFavInter.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                    binding.rvFavInter.adapter = adaptadorFavoritos
                }
            }
        }
    }

    private fun configRecyclerTvShowsInter() {
        ViewModel.getSessionID().observe(viewLifecycleOwner){sessionId ->
            ViewModel.getAccountDetails(sessionId).observe(viewLifecycleOwner){accountId ->
                ViewModel.getFavoriteTVShows(accountId.id).observe(viewLifecycleOwner){listaTVShow ->
                    val adaptadorFavoritos = AdaptadorMiListaSerie(listaTVShow, object : AdaptadorMiListaSerie.MyClick{
                        override fun onHolderClick(serie: TVShow) {
                            val id = serie.id
                            ViewModel.getMovieById(id, "es-ES").observe(viewLifecycleOwner){
                                if (it != null) {
                                    ViewModel.setPelicula(it)
                                    findNavController().navigate(R.id.action_fragment_btVerMas3_Fav_to_informacion)
                                }
                            }
                        }
                        override fun onItemLongClick(serie: TVShow) {

                        }
                    })
                    binding.rvFavInter.layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, true)
                    binding.rvFavInter.adapter = adaptadorFavoritos
                }
            }
        }
    }

    private fun configRecyclerMoviesWlInter() {
        ViewModel.getSessionID().observe(viewLifecycleOwner){sessionId ->
            ViewModel.getAccountID(sessionId).observe(viewLifecycleOwner){accountId ->
                ViewModel.getFavoriteWatchListMovies(accountId).observe(viewLifecycleOwner){ listaWatchListMovies ->
                    val adaptadorFavoritos = AdapterWLMoviesFav(listaWatchListMovies, object : AdapterWLMoviesFav.FavClick{
                        override fun onHolderClick(addWatchListBody: addWatchListBody) {
                            val id = addWatchListBody.media_id
                            ViewModel.getMovieById(id, "es-ES").observe(viewLifecycleOwner){
                                if (it != null) {
                                    ViewModel.setPelicula(it)
                                    findNavController().navigate(R.id.action_fragment_btVerMas3_Fav_to_informacion)
                                }
                            }
                        }
                    })
                    binding.rvFavInter.layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, true)
                    binding.rvFavInter.adapter = adaptadorFavoritos
                }
            }
        }
    }

    private fun configRecyclerTvShowsWlInter() {
        ViewModel.getSessionID().observe(viewLifecycleOwner){sessionId ->
            ViewModel.getAccountID(sessionId).observe(viewLifecycleOwner){accountId ->
                ViewModel.getFavouriteWatchListTVShows(accountId).observe(viewLifecycleOwner){ listaWatchListTVShows ->
                    val adaptadorFavoritos = AdapterWLTVShowsFav(listaWatchListTVShows, object : AdapterWLTVShowsFav.FavClick{
                        override fun onHolderClick(addWatchListBody: addWatchListBody) {
                            val id = addWatchListBody.media_id
                            ViewModel.getMovieById(id, "es-ES").observe(viewLifecycleOwner){
                                if (it != null) {
                                    ViewModel.setPelicula(it)
                                    findNavController().navigate(R.id.action_fragment_btVerMas3_Fav_to_informacion)
                                }
                            }
                        }
                    })
                    binding.rvFavInter.layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, true)
                    binding.rvFavInter.adapter = adaptadorFavoritos
                }
            }
        }
    }

}
