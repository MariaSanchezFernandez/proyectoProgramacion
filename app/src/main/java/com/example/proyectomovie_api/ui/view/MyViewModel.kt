package com.example.proyectomovie_api.ui.view

import android.graphics.Region
import android.media.Image
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectomovie_api.data.Repository
import com.example.proyectomovie_api.data.favorite.addFavoriteBody
import com.example.proyectomovie_api.data.images.ImageResponse
import com.example.proyectomovie_api.data.movie.Movie
import com.example.proyectomovie_api.data.movie.MovieResponse
import com.example.proyectomovie_api.data.movieProvider.MovieProviderResponse
import com.example.proyectomovie_api.data.tv.TVResponse
import com.example.proyectomovie_api.data.tv.TVShow
import com.example.proyectomovie_api.data.tvSerieProvider.TVSerieResponse
import com.example.proyectomovie_api.data.watchlist.WatchListResponse
import com.example.proyectomovie_api.data.watchlist.addWatchListBody
import kotlinx.coroutines.launch

class MyViewModel: ViewModel() {


    private val repositorio = Repository()
    private val listaMoviesPopuales = MutableLiveData<List<Movie>>()
    private val listaTVShowsPopulares = MutableLiveData<List<TVShow>>()
    private val listaMovieRated = MutableLiveData<List<Movie>>()
    private val listaTVShowRated = MutableLiveData<List<TVShow>>()
    private val listaPeliculasPopularesLiveData1 = MutableLiveData<MovieResponse>()
    private val peliculaLivedata = MutableLiveData<Movie>()
    private val serieLiveData = MutableLiveData<TVShow>()

    //Obtener las peliculas más populares

    fun getPopularMovies(apiKey: String) : MutableLiveData<List<Movie>> {
        viewModelScope.launch {
            val respuesta = repositorio.getPopularMovies(apiKey)
            if (respuesta.code() == 200) {
                var listaPeliculasPopulares = respuesta.body()
                listaPeliculasPopulares?.let {
                    listaMoviesPopuales.postValue(it.results)
                }
            }
        }
        return listaMoviesPopuales
    }

    fun getPopularTVShow(apiKey: String) : MutableLiveData<List<TVShow>> {
        viewModelScope.launch {
            val respuesta = repositorio.getPopularTVShows(apiKey)
            if (respuesta.code() == 200) {
                var listaSeriesPopulares = respuesta.body()
                listaSeriesPopulares?.let {
                    listaTVShowsPopulares.postValue(it.results)
                }
            }
        }
        return listaTVShowsPopulares
    }

    fun topRatedMovies(apiKey: String) : MutableLiveData<List<Movie>> {
        viewModelScope.launch {
            val respuesta = repositorio.topRatedMovies(apiKey)
            if (respuesta.code() == 200) {
                var listaPeliculasRated = respuesta.body()
                listaPeliculasRated?.let {
                    listaMovieRated.postValue(it.results)
                }
            }
        }
        return listaMovieRated
    }

    fun topRatedTVShow(apiKey: String) : MutableLiveData<List<TVShow>> {
        viewModelScope.launch {
            val respuesta = repositorio.topRatedTVShows(apiKey)
            if (respuesta.code() == 200) {
                var listaSeriesRated = respuesta.body()
                listaSeriesRated?.let {
                    listaTVShowRated.postValue(it.results)
                }
            }
        }
        return listaTVShowRated
    }

    // Devuelve un objeto con los proveedores de la Película
    fun getMovieWatchProvider(apiKey: String, movieId : Int) : MutableLiveData<MovieProviderResponse> {
        val movieWatchProviderLiveData = MutableLiveData<MovieProviderResponse>()
        viewModelScope.launch {
            val respuesta = repositorio.getMovieWatchProvider(apiKey, movieId)
            if(respuesta.code() == 200){
                var movieWatchProvider = respuesta.body()
                movieWatchProvider.let {
                    movieWatchProviderLiveData.postValue(it)
                }
            }
        }
        return movieWatchProviderLiveData
    }

    // Devuelve un objeto con los proveedores de la Serie
    fun getSerieWatchProvider(apiKey: String, serieId : Int) : MutableLiveData<TVSerieResponse> {
        val serieWatchProviderLiveData = MutableLiveData<TVSerieResponse>()
        viewModelScope.launch {
            val respuesta = repositorio.getSerieWatchProvider(apiKey, serieId)
            if(respuesta.code() == 200){
                var serieWatchProvider = respuesta.body()
                serieWatchProvider.let {
                    serieWatchProviderLiveData.postValue(it)
                }
            }
        }
        return serieWatchProviderLiveData
    }

    // Devuelve una respuesta si el POST de añadir a WatchList es exitoso
    fun addToWatchList(apiKey: String, accountId: Int, data: addWatchListBody) : MutableLiveData<WatchListResponse> {
        val addWatchListLiveData = MutableLiveData<WatchListResponse>()
        viewModelScope.launch {
            val respuesta = repositorio.addToWatchList(apiKey, accountId, data)
            if(respuesta.code() == 200){
                var addWatchList = respuesta.body()
                addWatchList.let {
                    addWatchListLiveData.postValue(it)
                }
            }
        }
        return addWatchListLiveData
    }

    fun addToFavorite(apiKey: String, accountId: Int, data: addFavoriteBody) : MutableLiveData<WatchListResponse> {
        val addFavoriteLiveData = MutableLiveData<WatchListResponse>()
        viewModelScope.launch {
            val respuesta = repositorio.addToFavorite(apiKey, accountId, data)
            if(respuesta.code() == 200){
                var addFavorite = respuesta.body()
                addFavorite.let {
                    addFavoriteLiveData.postValue(it)
                }
            }
        }
        return addFavoriteLiveData
    }

    fun getMovieImages(apiKey: String, movieId: Int) : MutableLiveData<ImageResponse> {
        val getMovieImagesLiveData = MutableLiveData<ImageResponse>()
        viewModelScope.launch {
            val respuesta = repositorio.getMovieimages(apiKey, movieId)
            if(respuesta.code() == 200){
                var addFavorite = respuesta.body()
                addFavorite.let {
                    getMovieImagesLiveData.postValue(it)
                }
            }
        }
        return getMovieImagesLiveData
    }

    fun getSerieImages(apiKey: String, serieId: Int) : MutableLiveData<ImageResponse> {
        val getSerieImagesLiveData = MutableLiveData<ImageResponse>()
        viewModelScope.launch {
            val respuesta = repositorio.getSerieImages(apiKey, serieId)
            if(respuesta.code() == 200){
                var addFavorite = respuesta.body()
                addFavorite.let {
                    getSerieImagesLiveData.postValue(it)
                }
            }
        }
        return getSerieImagesLiveData
    }

    //Guardar película al cambio de pantalla
    fun setPelicula(pelicula: Movie){
        peliculaLivedata.value = pelicula
    }

    fun getPelicula() = peliculaLivedata


    //Guardar TVShow al cambio de pantalla
    fun setSerie(serie: TVShow){
        serieLiveData.value = serie
    }

    fun getSerie() = serieLiveData
}
