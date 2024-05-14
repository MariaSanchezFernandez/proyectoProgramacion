package com.example.proyectomovie_api.data.retrofit

import com.example.proyectomovie_api.data.movie.MovieResponse
import com.example.proyectomovie_api.data.movieProvider.MovieProviderResponse
import com.example.proyectomovie_api.data.people.PeopleResponse
import com.example.proyectomovie_api.data.tv.TVResponse
import com.example.proyectomovie_api.data.tvSerieProvider.TVSerieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MyService {



    // Lista de descubrimientos de películas
    @GET("discover/movie?sort_by=popularity.desc")
    suspend fun discoverMovies(
        @Query("api_key") apiKey:String
    ): Response<MovieResponse>

    // Lista de descubrimientos de TVShows
    @GET("discover/tv?sort_by=popularity.desc")
    suspend fun discoverTVShows(
        @Query("api_key") apiKey:String
    ): Response<TVResponse>

    // Plataformas en las que se puede ver una peli
    @GET("movie/{movie_id}/watch/providers")
    suspend fun getMovieProvider(
        @Query("api_key") apiKey:String,
        @Path("movie_id") movieID:Int
    ): Response<MovieProviderResponse>

    // Plataformas en las que se puede ver un TVShow
    @GET("tv/{series_id}/watch/providers")
    suspend fun getTVShowProvider(
        @Query("api_key") apiKey:String,
        @Path("series_id") tvID:Int
    ): Response<TVSerieResponse>


    // Películas favoritas del usuario
    @GET("account/{account_id}/favorite/movies")
    suspend fun getFavMovies(
        @Query("api_key") apiKey:String,
        @Path("account_id") userID: Int
    ): Response<MovieResponse>


    //TVShows favoritos del usuario
    @GET("account/{account_id}/favorite/tv")
    suspend fun getFavTVShows(
        @Query("api_key") apiKey:String,
        @Path("account_id") userID: Int
    ): Response<TVResponse>

    // Películas más populares
    @GET("movie/popular")
    suspend fun popularMovies(
        @Query("api_key") apiKey:String,
        @Query("region") region:String
    ):Response<MovieResponse>

    // Películas mejor valoradas
    @GET("movie/top_rated")
    suspend fun topRatedMovies(
        @Query("api_key") apiKey:String,
        @Query("region") region:String
    ):Response<MovieResponse>

    // TVShows más populares
    @GET("tv/popular")
    suspend fun popularTVShows(
        @Query("api_key") apiKey:String,
        @Query("region") region:String
    ):Response<TVResponse>

    // TVShows mejor valorados
    @GET("tv/top_rated")
    suspend fun topRatedTVShows(
        @Query("api_key") apiKey:String,
        @Query("region") region:String
    ):Response<TVResponse>

    // Personas Trending del día o semana
    @GET("trending/person/{time_window}")
    suspend fun trendingPeople(
        @Query("api_key") apiKey:String,
        @Path("time_window") timeWindow:String
    ):Response<PeopleResponse>

    // Peliculas Trending del día o semana
    @GET("trending/movie/{time_window}")
    suspend fun trendingMovies(
        @Query("api_key") apiKey:String,
        @Path("time_window") timeWindow:String
    ):Response<MovieResponse>


    // TVShows Trending del día o semana
    @GET("trending/tv/{time_window}")
    suspend fun trendingTVShows(
        @Query("api_key") apiKey:String,
        @Path("time_window") timeWindow:String
    ):Response<TVResponse>















































}