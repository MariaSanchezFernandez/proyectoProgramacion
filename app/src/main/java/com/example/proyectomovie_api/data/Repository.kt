package com.example.proyectomovie_api.data

import android.graphics.Region
import com.example.proyectomovie_api.data.favorite.addFavoriteBody
import com.example.proyectomovie_api.data.inicioSesion.BodyLogin
import com.example.proyectomovie_api.data.inicioSesion.BodySessionID
import com.example.proyectomovie_api.data.retrofit.RetrofitHelper
import com.example.proyectomovie_api.data.watchlist.addWatchListBody
import org.intellij.lang.annotations.Language

class Repository {

    suspend fun getPopularMovies(apiKey:String) = RetrofitHelper.retrofitService.popularMovies(apiKey, "ES")

    suspend fun getPopularTVShows(apiKey:String) = RetrofitHelper.retrofitService.popularTVShows(apiKey, "ES")

    suspend fun discoverMovies() = RetrofitHelper.retrofitService.discoverMovies()

    suspend fun discoverTVShows() = RetrofitHelper.retrofitService.discoverTVShows()

    suspend fun getMovieProviders(movieID:Int) = RetrofitHelper.retrofitService.getMovieProvider(movieID)

    suspend fun getTVShowProviders(tvID:Int) = RetrofitHelper.retrofitService.getTVShowProvider(tvID)

    suspend fun getFavoriteMovies(accountID:Int ) = RetrofitHelper.retrofitService.getFavMovies(accountID)

    suspend fun getFavoriteTVShows(accountID: Int) = RetrofitHelper.retrofitService.getFavTVShows(accountID)

   // suspend fun getPopularMovies(region:String = "ES")  = RetrofitHelper.retrofitService.popularMovies(region)

   // suspend fun getPopularTVShows(region:String = "ES") = RetrofitHelper.retrofitService.popularTVShows(region)

    suspend fun getTopRatedMovies(region:String = "ES") = RetrofitHelper.retrofitService.topRatedMovies( region)

    suspend fun getTopRatedTVShows(region:String = "ES") = RetrofitHelper.retrofitService.topRatedTVShows( region)

    suspend fun getTrendingMovies(timeWindow:String = "day") = RetrofitHelper.retrofitService.trendingMovies(timeWindow)

    suspend fun getMovieWatchProvider(movieId:Int) = RetrofitHelper.retrofitService.getMovieWatchProvider(movieId)
    suspend fun getSerieWatchProvider(serieId:Int) = RetrofitHelper.retrofitService.getSerieWatchProvider(serieId)
    suspend fun addToWatchList( accountId: Int, data : addWatchListBody) = RetrofitHelper.retrofitService.addToWatchList(accountId,data)
    suspend fun addToFavorite(accountId: Int, data : addFavoriteBody) = RetrofitHelper.retrofitService.addToFavorite(accountId,data)
    suspend fun getSerieImages(serieId: Int) = RetrofitHelper.retrofitService.getSerieImages(serieId)
    suspend fun getMovieImages(movieId: Int) = RetrofitHelper.retrofitService.getMovieImages(movieId)
    suspend fun getMovieById(movieId: Int, language: String) = RetrofitHelper.retrofitService.getMovieById(movieId, language)
    suspend fun getTrendingTVShows(timeWindow:String = "day") = RetrofitHelper.retrofitService.trendingTVShows(timeWindow)

    suspend fun getTrendingPeople(timeWindow: String = "day") = RetrofitHelper.retrofitService.trendingPeople( timeWindow)


    // FUNCIONES PARA EL LOGIN
    suspend fun getAuthToken() = RetrofitHelper.retrofitService.getAuthToken()
    suspend fun createSessionLogin(body:BodyLogin) = RetrofitHelper.retrofitService.createSessionLogin(body)
    suspend fun createSession(bodySessionID: BodySessionID) = RetrofitHelper.retrofitService.createSessionID(bodySessionID)
    suspend fun createGuestSession() = RetrofitHelper.retrofitService.createGuestSession()


    // FUNCION PARA LOS DETALLES DE LA CUENTA

    suspend fun getAccountDetails(sessionID:String) = RetrofitHelper.retrofitService.getAccountDetails(sessionID)


    suspend fun topRatedTVShows(apiKey: String) = RetrofitHelper.retrofitService.topRatedTVShows(apiKey, "ES")

    suspend fun topRatedMovies(apiKey: String) = RetrofitHelper.retrofitService.topRatedMovies(apiKey, "ES")
}