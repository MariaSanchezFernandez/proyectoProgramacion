package com.example.proyectomovie_api.data

import com.example.proyectomovie_api.data.retrofit.RetrofitHelper

class Repository {

    suspend fun getPopularMovies(apiKey:String) = RetrofitHelper.retrofitService.getPopularMovies(apiKey)

    suspend fun getPopularTVShows(apiKey:String) = RetrofitHelper.retrofitService.getPopularTVSeries(apiKey)




}