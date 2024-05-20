package com.example.proyectomovie_api.data

import com.example.proyectomovie_api.data.inicioSesion.BodyLogin
import com.example.proyectomovie_api.data.retrofit.RetrofitHelper

class Repository {

    suspend fun discoverMovies(apiKey:String) = RetrofitHelper.retrofitService.discoverMovies(apiKey)

    suspend fun discoverTVShows(apiKey:String) = RetrofitHelper.retrofitService.discoverTVShows(apiKey)

    suspend fun getMovieProviders(apiKey: String, movieID:Int) = RetrofitHelper.retrofitService.getMovieProvider(apiKey,movieID)

    suspend fun getTVShowProviders(apiKey: String, tvID:Int) = RetrofitHelper.retrofitService.getTVShowProvider(apiKey,tvID)

    suspend fun getFavoriteMovies(apiKey: String, accountID:Int ) = RetrofitHelper.retrofitService.getFavMovies(apiKey, accountID)

    suspend fun getFavoriteTVShows(apiKey: String, accountID: Int) = RetrofitHelper.retrofitService.getFavTVShows(apiKey,accountID)

    suspend fun getPopularMovies(apiKey: String, region:String = "ES")  = RetrofitHelper.retrofitService.popularMovies(apiKey, region)

    suspend fun getPopularTVShows(apiKey: String, region:String = "ES") = RetrofitHelper.retrofitService.popularTVShows(apiKey, region)

    suspend fun getTopRatedMovies(apiKey: String, region:String = "ES") = RetrofitHelper.retrofitService.topRatedMovies(apiKey, region)

    suspend fun getTopRatedTVShows(apiKey: String, region:String = "ES") = RetrofitHelper.retrofitService.topRatedTVShows(apiKey, region)

    suspend fun getTrendingMovies(apiKey: String, timeWindow:String = "day") = RetrofitHelper.retrofitService.trendingMovies(apiKey,timeWindow)

    suspend fun getTrendingTVShows(apiKey: String, timeWindow:String = "day") = RetrofitHelper.retrofitService.trendingTVShows(apiKey,timeWindow)

    suspend fun getTrendingPeople(apiKey: String, timeWindow: String = "day") = RetrofitHelper.retrofitService.trendingPeople(apiKey, timeWindow)




    // FUNCIONES PARA EL LOGIN
    suspend fun getAuthToken(apiKey: String) = RetrofitHelper.retrofitService.getAuthToken(apiKey)
    suspend fun createSession(apiKey: String, body:BodyLogin)= RetrofitHelper.retrofitService.createSessionLogin(body,apiKey)
    suspend fun createGuestSession(apiKey: String) =RetrofitHelper.retrofitService.createGuestSession(apiKey)











}