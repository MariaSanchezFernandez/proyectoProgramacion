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
import com.example.proyectomovie_api.ui.adapters.AdapterWLMoviesFav
import com.example.proyectomovie_api.ui.adapters.AdapterWLTVShowsFav
import com.example.proyectomovie_api.ui.view.MyViewModel

class Favoritos : Fragment() {

    private var _binding: FragmentFavoritosBinding? = null
    private val binding get() = _binding!!
    private val myViewModel by activityViewModels<MyViewModel>()
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoritosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sesionId = myViewModel.getSessionID()

        val acountId = myViewModel. getAccountID(sesionId.toString())

        acountId.value?.let { myViewModel.getFavoriteMovies(it) }?.observe(viewLifecycleOwner){
            configRecyclerMovies()
        }

        acountId.value?.let { myViewModel.getFavoriteTVShows(it) }?.observe(viewLifecycleOwner){
            configRecyclerTvShows()
        }

        acountId.value?.let { myViewModel.getFavoriteWatchListMovies(it) }?.observe(viewLifecycleOwner) {
            configRecyclerMoviesWl()
        }

        acountId.value?.let { myViewModel.getFavouriteWatchListTVShows(it) }?.observe(viewLifecycleOwner) {
            configRecyclerTvShowsWl()
        }

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
        myViewModel.getSessionID().observe(viewLifecycleOwner){sessionId ->
            myViewModel.getAccountDetails(sessionId).observe(viewLifecycleOwner){accountId ->
                myViewModel.getFavoriteMovies(accountId.id).observe(viewLifecycleOwner){listaFavoritos ->
                    val adaptadorFavoritos = AdaptadorMiListaPeliculas(listaFavoritos, object : AdaptadorMiListaPeliculas.MyClick{
                        override fun onHolderClick(pelicula: Movie) {
                            val id = pelicula.id
                            myViewModel.getMovieById(id, "es-ES").observe(viewLifecycleOwner){
                                if (it != null) {
                                    myViewModel.setPelicula(it)
                                    findNavController().navigate(R.id.action_favoritos_to_informacion)
                                }
                            }
                        }
                        override fun onItemLongClick(pelicula: Movie) {

                        }
                    })
                    binding.rvPelis.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                    binding.rvPelis.adapter = adaptadorFavoritos
                }
            }
        }
    }

    private fun configRecyclerTvShows() {
        myViewModel.getSessionID().observe(viewLifecycleOwner){sessionId ->
            myViewModel.getAccountDetails(sessionId).observe(viewLifecycleOwner){accountId ->
                myViewModel.getFavoriteTVShows(accountId.id).observe(viewLifecycleOwner){listaTVShow ->
                    val adaptadorFavoritos = AdaptadorMiListaSerie(listaTVShow, object : AdaptadorMiListaSerie.MyClick{
                        override fun onHolderClick(serie: TVShow) {
                            val id = serie.id
                            myViewModel.getMovieById(id, "es-ES").observe(viewLifecycleOwner){
                                if (it != null) {
                                    myViewModel.setPelicula(it)
                                    findNavController().navigate(R.id.action_favoritos_to_informacion)
                                }
                            }
                        }
                        override fun onItemLongClick(serie: TVShow) {

                        }
                    })
                    binding.rvSeries.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                    binding.rvSeries.adapter = adaptadorFavoritos
                }
            }
        }
    }

    private fun configRecyclerMoviesWl() {
        myViewModel.getSessionID().observe(viewLifecycleOwner){sessionId ->
            myViewModel.getAccountID(sessionId).observe(viewLifecycleOwner){accountId ->
                myViewModel.getFavoriteWatchListMovies(accountId).observe(viewLifecycleOwner){listaFavoritos ->
                    val adaptadorFavoritos = AdapterWLMoviesFav(listaFavoritos, object : AdapterWLMoviesFav.FavClick{
                        override fun onHolderClick(movie: Movie) {
                            val id = movie.id
                            myViewModel.getMovieById(id, "es-ES").observe(viewLifecycleOwner){
                                if (it != null) {
                                    myViewModel.setPelicula(it)
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
        myViewModel.getSessionID().observe(viewLifecycleOwner){sessionId ->
            myViewModel.getAccountID(sessionId).observe(viewLifecycleOwner){accountId ->
                myViewModel.getFavouriteWatchListTVShows(accountId).observe(viewLifecycleOwner){listaTVShow ->
                    val adaptadorFavoritos = AdapterWLTVShowsFav(listaTVShow, object : AdapterWLTVShowsFav.FavClick{
                        override fun onHolderClick(serie: TVShow) {
                            val id = serie.id
                            myViewModel.getMovieById(id, "es-ES").observe(viewLifecycleOwner){
                                if (it != null) {
                                    myViewModel.setPelicula(it)
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