package com.example.proyectomovie_api.data

import android.graphics.Region
import com.example.proyectomovie_api.data.inicioSesion.BodyLogin
import com.example.proyectomovie_api.data.inicioSesion.BodySessionID
import com.example.proyectomovie_api.data.retrofit.RetrofitHelper

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



    //Funcion para buscador
    suspend fun searchMulti(buscador: String) = RetrofitHelper.retrofitService.searchMulti(buscador)
}