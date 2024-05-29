package com.example.proyectomovie_api.ui.view

import android.content.Context
import android.graphics.Region
import android.media.Image
import android.widget.Toast

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectomovie_api.data.Repository
import com.example.proyectomovie_api.data.favorite.addFavoriteBody
import com.example.proyectomovie_api.data.images.ImageResponse

import com.example.proyectomovie_api.data.account.AccountDetailsResponse
import com.example.proyectomovie_api.data.inicioSesion.BodyLogin
import com.example.proyectomovie_api.data.inicioSesion.BodySessionID
import com.example.proyectomovie_api.data.inicioSesion.CreateGuestSessionResponse
import com.example.proyectomovie_api.data.inicioSesion.CreateSessionResponse
import com.example.proyectomovie_api.data.inicioSesion.RequestTokenResponse
import com.example.proyectomovie_api.data.movie.Movie
import com.example.proyectomovie_api.data.movie.MovieResponse
import com.example.proyectomovie_api.data.movieProvider.MovieProviderResponse
import com.example.proyectomovie_api.data.movie_detalles.MovieDetallesResponse
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
    private val peliculaLivedata = MutableLiveData<MovieDetallesResponse>()
    private val serieLiveData = MutableLiveData<TVShow>()
    private val requestToken = MutableLiveData<String>()
    private val sessionID = MutableLiveData<String>()
    private val listaFavMovies = MutableLiveData<List<Movie>>()
    private val listaFavSeries = MutableLiveData<List<TVShow>>()

    //Id del acountId de Salva -> 548
    // Esto al juntarse con el resto del código tiene que cambiarse para que esté bien
    private val acountId = MutableLiveData<Int>(548)
    private val accountID = MutableLiveData<Int>()


    //Obtener las peliculas más populares

    fun getPopularMovies(apiKey: String): MutableLiveData<List<Movie>> {
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

    fun getAuthToken(): MutableLiveData<String> {

        viewModelScope.launch {
            val response = repositorio.getAuthToken()

            if (response.code() == 200) {
                response.body()?.request_token?.let {
                    requestToken.postValue(it)
                }
            }
        }
        return requestToken
    }

    fun createSessionLogin(body: BodyLogin): MutableLiveData<RequestTokenResponse> {
        val liveData = MutableLiveData<RequestTokenResponse>()

        viewModelScope.launch {
            val response = repositorio.createSessionLogin(body)

            if (response.body()?.success == true) {
                response.body()?.let {
                    liveData.postValue(it)
                }
            }
        }
        return liveData
    }

    fun getSessionID() = sessionID

    fun setSessionID(id: String) {
        sessionID.value = id
    }

    fun createGuestSession(): MutableLiveData<CreateGuestSessionResponse> {
        val liveData = MutableLiveData<CreateGuestSessionResponse>()

        viewModelScope.launch {
            val response = repositorio.createGuestSession()

            if (response.code() == 200) {
                response.body()?.let {
                    liveData.postValue(it)
                }
            }
        }
        return liveData
    }

    fun createSession(bodySessionID: BodySessionID): MutableLiveData<CreateSessionResponse> {
        val liveData = MutableLiveData<CreateSessionResponse>()

        viewModelScope.launch {
            val response = repositorio.createSession(bodySessionID)

            if (response.code() == 200) {
                response.body()?.let {
                    liveData.postValue(it)
                }
            }
        }
        return liveData
    }

    //Da un objeto Response con los detalles de la cuenta
    fun getAccountDetails(sessionID: String): MutableLiveData<AccountDetailsResponse> {
        val liveData = MutableLiveData<AccountDetailsResponse>()

        viewModelScope.launch {
            val response = repositorio.getAccountDetails(sessionID)

            if (response.code() == 200) {
                response.body()?.let {
                    liveData.postValue(it)
                }
            }
        }
        return liveData
    }

    fun getPopularTVShow(apiKey: String): MutableLiveData<List<TVShow>> {
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

    fun topRatedMovies(apiKey: String): MutableLiveData<List<Movie>> {
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

    fun topRatedTVShow(apiKey: String): MutableLiveData<List<TVShow>> {
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
    fun getMovieWatchProvider(movieId: Int): MutableLiveData<MovieProviderResponse> {
        val movieWatchProviderLiveData = MutableLiveData<MovieProviderResponse>()
        viewModelScope.launch {
            val respuesta = repositorio.getMovieWatchProvider(movieId)
            if (respuesta.code() == 200) {
                var movieWatchProvider = respuesta.body()
                movieWatchProvider.let {
                    movieWatchProviderLiveData.postValue(it)
                }
            }
        }
        return movieWatchProviderLiveData
    }

    // Devuelve un objeto con los proveedores de la Serie
    fun getSerieWatchProvider(serieId: Int): MutableLiveData<TVSerieResponse> {
        val serieWatchProviderLiveData = MutableLiveData<TVSerieResponse>()
        viewModelScope.launch {
            val respuesta = repositorio.getSerieWatchProvider(serieId)
            if (respuesta.code() == 200) {
                var serieWatchProvider = respuesta.body()
                serieWatchProvider.let {
                    serieWatchProviderLiveData.postValue(it)
                }
            }
        }
        return serieWatchProviderLiveData
    }

    // Devuelve una respuesta si el POST de añadir a WatchList es exitoso
    fun addToWatchList(accountId: Int, data: addWatchListBody): MutableLiveData<WatchListResponse> {
        val addWatchListLiveData = MutableLiveData<WatchListResponse>()
        viewModelScope.launch {
            val respuesta = repositorio.addToWatchList(accountId, data)
            if (respuesta.code() == 200) {
                var addWatchList = respuesta.body()
                addWatchList.let {
                    addWatchListLiveData.postValue(it)
                }
            }
        }
        return addWatchListLiveData
    }

    fun addToFavorite(accountId: Int, data: addFavoriteBody): MutableLiveData<WatchListResponse> {
        val addFavoriteLiveData = MutableLiveData<WatchListResponse>()
        viewModelScope.launch {
            val respuesta = repositorio.addToFavorite(accountId, data)
            if (respuesta.code() == 200) {
                var addFavorite = respuesta.body()
                addFavorite.let {
                    addFavoriteLiveData.postValue(it)
                }
            }
        }
        return addFavoriteLiveData
    }

    fun getMovieImages(movieId: Int): MutableLiveData<ImageResponse> {
        val getMovieImagesLiveData = MutableLiveData<ImageResponse>()
        viewModelScope.launch {
            val respuesta = repositorio.getMovieImages(movieId)
            if (respuesta.code() == 200) {
                var addFavorite = respuesta.body()
                addFavorite.let {
                    getMovieImagesLiveData.postValue(it)
                }
            }
        }
        return getMovieImagesLiveData
    }

    fun getSerieImages(serieId: Int): MutableLiveData<ImageResponse> {
        val getSerieImagesLiveData = MutableLiveData<ImageResponse>()
        viewModelScope.launch {
            val respuesta = repositorio.getSerieImages(serieId)
            if (respuesta.code() == 200) {
                var addFavorite = respuesta.body()
                addFavorite.let {
                    getSerieImagesLiveData.postValue(it)
                }
            }
        }
        return getSerieImagesLiveData
    }

    fun getMovieById(movieId: Int, language: String): MutableLiveData<MovieDetallesResponse?> {
        val getMovieByIdLiveData = MutableLiveData<MovieDetallesResponse?>()
        viewModelScope.launch {
            val respuesta = repositorio.getMovieById(movieId, language)
            if (respuesta.code() == 200) {
                val addFavorite = respuesta.body()
                getMovieByIdLiveData.postValue(addFavorite)
            }
        }
        return getMovieByIdLiveData
    }

    //Guardar película al cambio de pantalla
    fun setPelicula(pelicula: MovieDetallesResponse) {
        peliculaLivedata.value = pelicula
    }

    fun getPelicula() = peliculaLivedata


    //Guardar TVShow al cambio de pantalla
    fun setSerie(serie: TVShow) {
        serieLiveData.value = serie
    }

    fun getSerie() = serieLiveData


    fun getFavoriteMovies(accountID: Int): MutableLiveData<List<Movie>> {
        viewModelScope.launch {
            val respuesta = repositorio.getFavoriteMovies(accountID)
            if (respuesta.code() == 200) {
                var listaMoviesFav = respuesta.body()
                listaMoviesFav?.let {
                    listaFavMovies.postValue(it.results)
                }
            }
        }
        return listaFavMovies
    }

    fun getFavMovies() = listaFavMovies

    fun getFavoriteTVShows(acountId: Int): MutableLiveData<List<TVShow>> {
        viewModelScope.launch {
            val respuesta = repositorio.getFavoriteTVShows(acountId)
            if (respuesta?.code() == 200) {
                val listaTVshowFav = respuesta?.body()
                listaTVshowFav?.let {
                    listaFavSeries.postValue(it.results)
                }
            }
        }
        return listaFavSeries
    }

    fun getFavTVShows() = listaFavSeries

    fun getFavoriteWatchListMovies(accountId: Int): MutableLiveData<List<Movie>> {
        val listaWlMoviesFavLiveData = MutableLiveData<List<Movie>>()
        viewModelScope.launch {
            val respuesta = repositorio.getFavouriteWatchListMovies(accountId)
            if (respuesta.code() == 200) {
                var listaWLMoviesFav = respuesta.body()
                listaWLMoviesFav?.let {
                    listaWlMoviesFavLiveData.postValue(it.results)
                }
            }
        }
        return listaWlMoviesFavLiveData
    }

    fun getFavouriteWatchListTVShows(accountId: Int): MutableLiveData<List<TVShow>> {
        val listaWlTVshowsFavLiveData = MutableLiveData<List<TVShow>>()
        viewModelScope.launch {
            val respuesta = repositorio.getFavouriteWatchListTVShows(accountId)
            if(respuesta?.code() == 200) {
                val listaWLTVShowsFav = respuesta.body()
                listaWLTVShowsFav?.let {
                    listaWlTVshowsFavLiveData.postValue(it.results)
                }
            }
        }
        return listaWlTVshowsFavLiveData
    }

    fun setAccountId(id: Int) {
        accountID.value = id
    }

    // Devuelve la ID de la cuenta
    fun getAccountID(sessionID: String): MutableLiveData<Int> {
        viewModelScope.launch {
            val response = repositorio.getAccountDetails(sessionID)
            if (response.code() == 200) {
                response.body()?.id.let {
                    accountID.postValue(it)
                }
            }
        }
        return accountID
    }

}