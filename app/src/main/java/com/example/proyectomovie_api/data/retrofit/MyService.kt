package com.example.proyectomovie_api.data.retrofit

import com.example.proyectomovie_api.data.movie.MovieResponse
import com.example.proyectomovie_api.data.movieProvider.MovieProviderResponse
import com.example.proyectomovie_api.data.tv.TVResponse
import com.example.proyectomovie_api.data.tvSerieProvider.TVSerieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
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

    // Devuelve Las plataformas en las que se puede ver una peli
    @GET("movie/{movie_id}/watch/providers")
    suspend fun getMovieProvider(
        @Query("api_key") apiKey:String,
        @Path("movie_id") movieID:Int
    ): Response<MovieProviderResponse>

    // Devuelve Las plataformas en las que se puede ver una serie de TV
    @GET("tv/{series_id}/watch/providers")
    suspend fun getTVShowProvider(
        @Query("api_key") apiKey:String,
        @Path("series_id") tvID:Int
    ): Response<TVSerieResponse>


}