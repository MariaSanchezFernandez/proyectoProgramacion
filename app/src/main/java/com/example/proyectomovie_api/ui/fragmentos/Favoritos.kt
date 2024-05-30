package com.example.proyectomovie_api.ui.fragmentos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.proyectomovie_api.R
import com.example.proyectomovie_api.data.movie.Movie
import com.example.proyectomovie_api.data.tv.TVShow
import com.example.proyectomovie_api.databinding.FragmentFavoritosBinding
import com.example.proyectomovie_api.ui.adapters.AdapterFav
import com.example.proyectomovie_api.ui.adapters.AdapterFavTvShows
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

        val acountId = myViewModel.getAccountID(sesionId.toString())

        acountId.value?.let { myViewModel.getFavoriteMovies(it) }?.observe(viewLifecycleOwner){
            configRecyclerMovies(it)
        }

//        acountId.value?.let { myViewModel.getFa(it) }?.observe(viewLifecycleOwner){
//            configRecyclerTvShows(it)
//        }

    }

    private fun configRecyclerMovies(listaPeliculas: List<Movie>) {
        val recyclerView = binding.rvPelis
        val adapter = AdapterFav(listaPeliculas, object : AdapterFav.FavClick{
            override fun onFavClick(movie: Movie) {
                findNavController().navigate(R.id.action_fragmentPeliculas_to_informacion)
            }
        })
        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }

    private fun configRecyclerTvShows(listaTVShow: List<TVShow>) {
        val recyclerView = binding.rvSeries
        val adapter = AdapterFavTvShows(listaTVShow, object : AdapterFavTvShows.FavClick{
            override fun onFavClick(movie: Movie) {
                findNavController().navigate(R.id.action_fragmentSeries_to_informacionSeries)
            }
        })
        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }

}