package com.example.proyectomovie_api.data

import com.example.proyectomovie_api.data.retrofit.RetrofitHelper

class Repository {

    suspend fun getPopularMovies(apiKey:String) = RetrofitHelper.retrofitService.getPopularMovies(apiKey)

    suspend fun getPopularTVShows(apiKey:String) = RetrofitHelper.retrofitService.getPopularTVSeries(apiKey)

    suspend fun getMovieProviders(apiKey: String, movieID:Int) = RetrofitHelper.retrofitService.getMovieProvider(apiKey,movieID)

    suspend fun getTVShowProviders(apiKey: String, tvID:Int) = RetrofitHelper.retrofitService.getTVShowProvider(apiKey,tvID)




}