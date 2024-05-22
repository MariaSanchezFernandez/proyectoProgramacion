package com.example.proyectomovie_api.data.retrofit

import com.example.proyectomovie_api.data.inicioSesion.BodyLogin
import com.example.proyectomovie_api.data.inicioSesion.CreateSessionResponse
import com.example.proyectomovie_api.data.inicioSesion.RequestTokenResponse
import com.example.proyectomovie_api.data.movie.MovieResponse
import com.example.proyectomovie_api.data.movieProvider.MovieProviderResponse
import com.example.proyectomovie_api.data.people.PeopleResponse
import com.example.proyectomovie_api.data.tv.TVResponse
import com.example.proyectomovie_api.data.tvSerieProvider.TVSerieResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface MyService {

    // El Token es este, si no funciona puede que lo haya re generado, preguntad a salva :
    // eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3MzkxMTg2YTU2OTA4YjYyNWYzMTYxZTllYjAxY2I1ZCIsInN1YiI6IjY2M2QzZDZkODQyZjg2NzZkMmEzYzY5MCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.u_BACdgwUbPA0HU_WGGAiJ_hCxTz9-l2VHYxexXVdbA
    /*
    *   PETICIONES PARA EL LOGIN
     */


    // Create Request Token
    @Headers(
        "accept: application/json",
        "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3MzkxMTg2YTU2OTA4YjYyNWYzMTYxZTllYjAxY2I1ZCIsInN1YiI6IjY2M2QzZDZkODQyZjg2NzZkMmEzYzY5MCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.u_BACdgwUbPA0HU_WGGAiJ_hCxTz9-l2VHYxexXVdbA"
    )
    @GET("authentication/token/new")
    suspend fun getAuthToken(
    ):Response<RequestTokenResponse>

    // Entre estas habría que autenticar el token enviando al usuario a la página


    //Create Session with Login (username + password + requestToken ya autenticada por el body)
    @Headers(
        "accept: application/json",
        "content-type : application/json",
        "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3MzkxMTg2YTU2OTA4YjYyNWYzMTYxZTllYjAxY2I1ZCIsInN1YiI6IjY2M2QzZDZkODQyZjg2NzZkMmEzYzY5MCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.u_BACdgwUbPA0HU_WGGAiJ_hCxTz9-l2VHYxexXVdbA"
    )
    @POST("authentication/token/validate_with_login")
    suspend fun createSessionLogin(
        @Body body: BodyLogin,
    ):Response<CreateSessionResponse>

    // Create Guest Session
    @Headers(
        "accept: application/json",
        "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3MzkxMTg2YTU2OTA4YjYyNWYzMTYxZTllYjAxY2I1ZCIsInN1YiI6IjY2M2QzZDZkODQyZjg2NzZkMmEzYzY5MCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.u_BACdgwUbPA0HU_WGGAiJ_hCxTz9-l2VHYxexXVdbA"
    )
    @GET("authentication/guest_session/new")
    suspend fun createGuestSession(
    ):Response<CreateSessionResponse>





    // Lista de descubrimientos de películas
    @Headers(
        "accept: application/json",
        "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkMGQ2MjE1NDBjYzg3ZmE5OWM0OTQ1MDJhMTEwZjc3ZiIsInN1YiI6IjY2M2QzZDZkODQyZjg2NzZkMmEzYzY5MCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.6-zizPvB8-3S-2bgxAKOdRthfs-RRvPvmR9gMX-_kGc"
    )
    @GET("discover/movie?sort_by=popularity.desc")
    suspend fun discoverMovies(
    ): Response<MovieResponse>

    // Lista de descubrimientos de TVShows
    @Headers(
        "accept: application/json",
        "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkMGQ2MjE1NDBjYzg3ZmE5OWM0OTQ1MDJhMTEwZjc3ZiIsInN1YiI6IjY2M2QzZDZkODQyZjg2NzZkMmEzYzY5MCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.6-zizPvB8-3S-2bgxAKOdRthfs-RRvPvmR9gMX-_kGc"
    )
    @GET("discover/tv?sort_by=popularity.desc")
    suspend fun discoverTVShows(
    ): Response<TVResponse>

    // Plataformas en las que se puede ver una peli
    @Headers(
        "accept: application/json",
        "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkMGQ2MjE1NDBjYzg3ZmE5OWM0OTQ1MDJhMTEwZjc3ZiIsInN1YiI6IjY2M2QzZDZkODQyZjg2NzZkMmEzYzY5MCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.6-zizPvB8-3S-2bgxAKOdRthfs-RRvPvmR9gMX-_kGc"
    )
    @GET("movie/{movie_id}/watch/providers")
    suspend fun getMovieProvider(
        @Path("movie_id") movieID:Int
    ): Response<MovieProviderResponse>

    // Plataformas en las que se puede ver un TVShow
    @Headers(
        "accept: application/json",
        "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkMGQ2MjE1NDBjYzg3ZmE5OWM0OTQ1MDJhMTEwZjc3ZiIsInN1YiI6IjY2M2QzZDZkODQyZjg2NzZkMmEzYzY5MCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.6-zizPvB8-3S-2bgxAKOdRthfs-RRvPvmR9gMX-_kGc"
    )
    @GET("tv/{series_id}/watch/providers")
    suspend fun getTVShowProvider(
        @Path("series_id") tvID:Int
    ): Response<TVSerieResponse>

    // Películas favoritas del usuario
    @Headers(
        "accept: application/json",
        "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkMGQ2MjE1NDBjYzg3ZmE5OWM0OTQ1MDJhMTEwZjc3ZiIsInN1YiI6IjY2M2QzZDZkODQyZjg2NzZkMmEzYzY5MCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.6-zizPvB8-3S-2bgxAKOdRthfs-RRvPvmR9gMX-_kGc"
    )
    @GET("account/{account_id}/favorite/movies")
    suspend fun getFavMovies(
        @Path("account_id") userID: Int
    ): Response<MovieResponse>

    //TVShows favoritos del usuario
    @Headers(
        "accept: application/json",
        "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkMGQ2MjE1NDBjYzg3ZmE5OWM0OTQ1MDJhMTEwZjc3ZiIsInN1YiI6IjY2M2QzZDZkODQyZjg2NzZkMmEzYzY5MCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.6-zizPvB8-3S-2bgxAKOdRthfs-RRvPvmR9gMX-_kGc"
    )
    @GET("account/{account_id}/favorite/tv")
    suspend fun getFavTVShows(
        @Path("account_id") userID: Int
    ): Response<TVResponse>

    // Películas más populares
    @Headers(
        "accept: application/json",
        "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkMGQ2MjE1NDBjYzg3ZmE5OWM0OTQ1MDJhMTEwZjc3ZiIsInN1YiI6IjY2M2QzZDZkODQyZjg2NzZkMmEzYzY5MCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.6-zizPvB8-3S-2bgxAKOdRthfs-RRvPvmR9gMX-_kGc"
    )
    @GET("movie/popular")
    suspend fun popularMovies(
        @Query("region") region:String
    ):Response<MovieResponse>

    // Películas mejor valoradas
    @Headers(
        "accept: application/json",
        "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkMGQ2MjE1NDBjYzg3ZmE5OWM0OTQ1MDJhMTEwZjc3ZiIsInN1YiI6IjY2M2QzZDZkODQyZjg2NzZkMmEzYzY5MCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.6-zizPvB8-3S-2bgxAKOdRthfs-RRvPvmR9gMX-_kGc"
    )
    @GET("movie/top_rated")
    suspend fun topRatedMovies(
        @Query("region") region:String
    ):Response<MovieResponse>

    // TVShows más populares
    @Headers(
        "accept: application/json",
        "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkMGQ2MjE1NDBjYzg3ZmE5OWM0OTQ1MDJhMTEwZjc3ZiIsInN1YiI6IjY2M2QzZDZkODQyZjg2NzZkMmEzYzY5MCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.6-zizPvB8-3S-2bgxAKOdRthfs-RRvPvmR9gMX-_kGc"
    )
    @GET("tv/popular")
    suspend fun popularTVShows(
        @Query("region") region:String
    ):Response<TVResponse>

    // TVShows mejor valorados
    @Headers(
        "accept: application/json",
        "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkMGQ2MjE1NDBjYzg3ZmE5OWM0OTQ1MDJhMTEwZjc3ZiIsInN1YiI6IjY2M2QzZDZkODQyZjg2NzZkMmEzYzY5MCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.6-zizPvB8-3S-2bgxAKOdRthfs-RRvPvmR9gMX-_kGc"
    )
    @GET("tv/top_rated")
    suspend fun topRatedTVShows(
        @Query("region") region:String
    ):Response<TVResponse>

    // Personas Trending del día o semana
    @Headers(
        "accept: application/json",
        "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkMGQ2MjE1NDBjYzg3ZmE5OWM0OTQ1MDJhMTEwZjc3ZiIsInN1YiI6IjY2M2QzZDZkODQyZjg2NzZkMmEzYzY5MCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.6-zizPvB8-3S-2bgxAKOdRthfs-RRvPvmR9gMX-_kGc"
    )
    @GET("trending/person/{time_window}")
    suspend fun trendingPeople(
        @Path("time_window") timeWindow:String
    ):Response<PeopleResponse>

    // Peliculas Trending del día o semana
    @Headers(
        "accept: application/json",
        "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkMGQ2MjE1NDBjYzg3ZmE5OWM0OTQ1MDJhMTEwZjc3ZiIsInN1YiI6IjY2M2QzZDZkODQyZjg2NzZkMmEzYzY5MCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.6-zizPvB8-3S-2bgxAKOdRthfs-RRvPvmR9gMX-_kGc"
    )
    @GET("trending/movie/{time_window}")
    suspend fun trendingMovies(
        @Path("time_window") timeWindow:String
    ):Response<MovieResponse>


    // TVShows Trending del día o semana
    @Headers(
        "accept: application/json",
        "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkMGQ2MjE1NDBjYzg3ZmE5OWM0OTQ1MDJhMTEwZjc3ZiIsInN1YiI6IjY2M2QzZDZkODQyZjg2NzZkMmEzYzY5MCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.6-zizPvB8-3S-2bgxAKOdRthfs-RRvPvmR9gMX-_kGc"
    )
    @GET("trending/tv/{time_window}")
    suspend fun trendingTVShows(
        @Path("time_window") timeWindow:String
    ):Response<TVResponse>





}