package com.example.proyectomovie_api.data.retrofit

import com.example.proyectomovie_api.data.movie.Movie
import com.example.proyectomovie_api.data.movie.MovieResponse
import com.example.proyectomovie_api.data.tv.TVResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MyService {



    // Devuelve las películas más populares ordenadas
    @GET("discover/movie?sort_by=popularity.desc")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey:String
    ): Response<MovieResponse>

    // Devuelve los TV Shows más populares ordenados
    @GET("discover/tv?sort_by=popularity.desc")
    suspend fun getPopularTVSeries(
        @Query("api_key") apiKey:String
    ): Response<TVResponse>

    //


}