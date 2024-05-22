package com.example.proyectomovie_api.data

import android.graphics.Region
import com.example.proyectomovie_api.data.retrofit.RetrofitHelper

class Repository {

    suspend fun getPopularMovies(apiKey:String) = RetrofitHelper.retrofitService.popularMovies(apiKey, "ES")

    suspend fun getPopularTVShows(apiKey:String) = RetrofitHelper.retrofitService.popularTVShows(apiKey, "ES")

    suspend fun getMovieProviders(apiKey: String, movieID:Int) = RetrofitHelper.retrofitService.getMovieProvider(apiKey,movieID)

    suspend fun getTVShowProviders(apiKey: String, tvID:Int) = RetrofitHelper.retrofitService.getTVShowProvider(apiKey,tvID)




}