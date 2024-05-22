package com.example.proyectomovie_api.ui.view

import android.graphics.Region
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectomovie_api.data.Repository
import com.example.proyectomovie_api.data.movie.Movie
import com.example.proyectomovie_api.data.movie.MovieResponse
import com.example.proyectomovie_api.data.tv.TVShow
import kotlinx.coroutines.launch

class MyViewModel: ViewModel() {


    private val repositorio = Repository()
    private val listaPeliculasPopularesLiveData = MutableLiveData<MovieResponse>()
    private val listaMovies = MutableLiveData<ArrayList<Movie>>()
    private val listaTVShows = MutableLiveData<ArrayList<TVShow>>()


    //Obtener las peliculas más populares

//    fun getPopularMovies(apiKey: String) {
//        viewModelScope.launch {
//            val respuesta = repositorio.getPopularMovies(apiKey)
//            if (respuesta.code() == 200) {
//                var listaPeliculasPopulares = respuesta.body()
//                listaPeliculasPopulares?.let {
//                    listaPeliculasPopularesLiveData.postValue(it)
//                }
//            }
//        }
//    }
}
