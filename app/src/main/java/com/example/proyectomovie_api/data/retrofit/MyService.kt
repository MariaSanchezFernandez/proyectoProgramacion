package com.example.proyectomovie_api.data.retrofit


import com.example.proyectomovie_api.data.favorite.addFavoriteBody
import com.example.proyectomovie_api.data.images.ImageResponse
import com.example.proyectomovie_api.data.watchlist.addWatchListBody
import com.example.proyectomovie_api.data.account.AccountDetailsResponse
import com.example.proyectomovie_api.data.inicioSesion.BodyLogin
import com.example.proyectomovie_api.data.inicioSesion.BodySessionID
import com.example.proyectomovie_api.data.inicioSesion.CreateGuestSessionResponse
import com.example.proyectomovie_api.data.inicioSesion.CreateSessionResponse
import com.example.proyectomovie_api.data.inicioSesion.DeleteSessionResponse
import com.example.proyectomovie_api.data.inicioSesion.RequestTokenResponse
import com.example.proyectomovie_api.data.movie.Movie
import com.example.proyectomovie_api.data.movie.MovieResponse
import com.example.proyectomovie_api.data.movieProvider.MovieProviderResponse
import com.example.proyectomovie_api.data.movie_detalles.MovieDetallesResponse
import com.example.proyectomovie_api.data.movie_detalles.SpokenLanguage
import com.example.proyectomovie_api.data.people.PeopleResponse
import com.example.proyectomovie_api.data.serie_detalles.SerieDetallesResponse
import com.example.proyectomovie_api.data.tv.TVResponse
import com.example.proyectomovie_api.data.tvSerieProvider.TVSerieResponse
import com.example.proyectomovie_api.data.watchlist.WatchListResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

private const val BEARER_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzZmM2ZDI3NGRkMmMxYzhmMTAyYjI1NDEyNzI4ZjMxOSIsInN1YiI6IjY2MWUyY2NlZWNhZWY1MDE3Y2Y5MGJkOSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.Xy4v07iPVq7sc3Z_rATBniTeAqu7GCAL9_m8w4tVIaM"

interface MyService {

    // El Token es este, si no funciona puede que lo haya re generado, preguntad a salva :
    // eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3MzkxMTg2YTU2OTA4YjYyNWYzMTYxZTllYjAxY2I1ZCIsInN1YiI6IjY2M2QzZDZkODQyZjg2NzZkMmEzYzY5MCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.u_BACdgwUbPA0HU_WGGAiJ_hCxTz9-l2VHYxexXVdbA
    /*
    *    ------------- PETICIONES PARA EL LOGIN -----------------------
     */







    // Plataformas en las que se puede ver una peli
    // CREA UN TOKEN PARA ENVIARLO A LA PAGINA Y VALIDARLO
    @Headers(
        "accept: application/json",
        "Authorization: Bearer $BEARER_TOKEN"
    )
    @GET("authentication/token/new")
    suspend fun getAuthToken(
    ):Response<RequestTokenResponse>


    // Create Session with Login (username + password + requestToken ya autenticada por el body)
    // CORRECCIÓN , NO CREA LA SESIÓN, SOLO VALIDA EL TOKEN POR PARTE DEL CREADOR DE LA CUENTA (YO) PARA MANEJAR LA API
    @Headers(
        "Content-Type: application/json",
        "accept: application/json",
        "Authorization: Bearer $BEARER_TOKEN"
    )
    @POST("authentication/token/validate_with_login")
    suspend fun createSessionLogin(
        @Body body:BodyLogin,
    ):Response<RequestTokenResponse>


    // Create Session ID.  ESTA VEZ DE VERDAD
    @Headers(
        "Content-Type: application/json",
        "accept: application/json",
        "Authorization: Bearer $BEARER_TOKEN"
    )
    @POST("authentication/session/new")
    suspend fun createSessionID(
        @Body bodySessionID: BodySessionID
    ):Response<CreateSessionResponse>


    // Create Guest Session
    @Headers(
        "accept: application/json",
        "Authorization: Bearer $BEARER_TOKEN"
    )
    @GET("authentication/guest_session/new")
    suspend fun createGuestSession(
    ):Response<CreateGuestSessionResponse>

    // DELETE SESSION
    @Headers(
        "Content-Type: application/json",
        "accept: application/json",
        "Authorization: Bearer $BEARER_TOKEN"
    )
    @DELETE("authentication/session")
    suspend fun deleteSession(
        @Query("session_id") id:String
    ):Response<DeleteSessionResponse>


    // -------------------------------------------------------------------------------------------------

    /*
    * ------------------ PETICIONES PARA LA PÁGINA DE CUENTA ----------------
     */


    // Account Details

    // de esta request se puede sacar la ID de la cuenta, que se usa a su vez para hacer ciertas llamadas a la api
    @Headers(
        "accept: application/json",
        "Authorization: Bearer $BEARER_TOKEN"
    )
    @GET("account")
    suspend fun getAccountDetails(
        @Query("session_id") sessionID:String
    ):Response<AccountDetailsResponse>


    // ----------------------------



    // Lista de descubrimientos de películas
    @Headers(
        "accept: application/json",
        "Authorization: Bearer $BEARER_TOKEN"
    )
    @GET("discover/movie?sort_by=popularity.desc")
    suspend fun discoverMovies(
    ): Response<MovieResponse>

    // Lista de descubrimientos de TVShows
    @Headers(
        "accept: application/json",
        "Authorization: Bearer $BEARER_TOKEN"
    )
    @GET("discover/tv?sort_by=popularity.desc")
    suspend fun discoverTVShows(
    ): Response<TVResponse>

    // Plataformas en las que se puede ver una peli
    @Headers(
        "accept: application/json",
        "Authorization: Bearer $BEARER_TOKEN"
        )
    @GET("movie/{movie_id}/watch/providers")
    suspend fun getMovieProvider(
        @Path("movie_id") movieID:Int
    ): Response<MovieProviderResponse>

    // Plataformas en las que se puede ver un TVShow

    @Headers(
        "accept: application/json",
        "Authorization: Bearer $BEARER_TOKEN"
    )
    @GET("tv/{series_id}/watch/providers")
    suspend fun getTVShowProvider(
        @Path("series_id") tvID:Int
    ): Response<TVSerieResponse>



    // Películas favoritas del usuario
    @Headers(
        "accept: application/json",
        "Authorization: Bearer $BEARER_TOKEN"
    )
    @GET("account/{account_id}/favorite/movies")
    suspend fun getFavMovies(
        @Path("account_id") accountID: Int
    ): Response<MovieResponse>

    //TVShows favoritos del usuario
    @Headers(
        "accept: application/json",
        "Authorization: Bearer $BEARER_TOKEN"
    )
    @GET("account/{account_id}/favorite/tv")
    suspend fun getFavTVShows(
        @Path("account_id") account_id: Int
    ): Response<TVResponse>

    // Peliculas favoritas del usuario WatchList
    @Headers(
        "accept: application/json",
        "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkMGQ2MjE1NDBjYzg3ZmE5OWM0OTQ1MDJhMTEwZjc3ZiIsInN1YiI6IjY2M2QzZDZkODQyZjg2NzZkMmEzYzY5MCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.6-zizPvB8-3S-2bgxAKOdRthfs-RRvPvmR9gMX-_kGc"
    )
    @GET("account/{account_id}/watchlist/movies")
    suspend fun getFavWatchListMovies(
        @Path("acount_id") userID: Int
    ): Response<MovieResponse>

    // TVShows favoritos del usuario WatchList
    @Headers(
        "accept: application/json",
        "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkMGQ2MjE1NDBjYzg3ZmE5OWM0OTQ1MDJhMTEwZjc3ZiIsInN1YiI6IjY2M2QzZDZkODQyZjg2NzZkMmEzYzY5MCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.6-zizPvB8-3S-2bgxAKOdRthfs-RRvPvmR9gMX-_kGc"
    )
    @GET("account/{account_id}/watchlist/tv")
    suspend fun getFavWatchListTVShows(
        @Path("acount_id") userID: Int
    ): Response<TVResponse>


    // Películas más populares
    @Headers(
        "accept: application/json",
        "Authorization: Bearer $BEARER_TOKEN"
    )
    @GET("movie/popular")
    suspend fun popularMovies(
        @Query("region") region:String
    ):Response<MovieResponse>

    // Películas mejor valoradas
    @Headers(
        "accept: application/json",
        "Authorization: Bearer $BEARER_TOKEN"
    )
    @GET("movie/top_rated")
    suspend fun topRatedMovies(
        @Query("region") region:String
    ):Response<MovieResponse>

    // TVShows más populares
    @Headers(
        "accept: application/json",
        "Authorization: Bearer $BEARER_TOKEN"
    )
    @GET("tv/popular")
    suspend fun popularTVShows(
        @Query("region") region:String
    ):Response<TVResponse>

    // TVShows mejor valorados
    @Headers(
        "accept: application/json",
        "Authorization: Bearer $BEARER_TOKEN"
    )
    @GET("tv/top_rated")
    suspend fun topRatedTVShows(
        @Query("region") region:String
    ):Response<TVResponse>

    // Personas Trending del día o semana
    @Headers(
        "accept: application/json",
        "Authorization: Bearer $BEARER_TOKEN"
    )
    @GET("trending/person/{time_window}")
    suspend fun trendingPeople(
        @Path("time_window") timeWindow:String
    ):Response<PeopleResponse>

    // Peliculas Trending del día o semana
    @Headers(
        "accept: application/json",
        "Authorization: Bearer $BEARER_TOKEN"
    )
    @GET("trending/movie/{time_window}")
    suspend fun trendingMovies(
        @Path("time_window") timeWindow:String
    ):Response<MovieResponse>


    // TVShows Trending del día o semana
    @Headers(
        "accept: application/json",
        "Authorization: Bearer $BEARER_TOKEN"
    )
    @GET("trending/tv/{time_window}")
    suspend fun trendingTVShows(
        @Path("time_window") timeWindow:String
    ):Response<TVResponse>



    // Obtiene la lista de proveedores por país donde la serie está disponible
    @Headers(
        "accept: application/json",
        "Authorization: Bearer $BEARER_TOKEN"
    )
    @GET("tv/{serie_id}/watch/providers")
    suspend fun getSerieWatchProvider(
        @Path("serie_id") movieId: Int
    ):Response<TVSerieResponse>

    // Obtiene la lista de proveedores por país donde la película está disponible
    @Headers(
        "accept: application/json",
        "Authorization: Bearer $BEARER_TOKEN"
    )
    @GET("movie/{movie_id}/watch/providers")
    suspend fun getMovieWatchProvider(
        @Path("movie_id") movieId: Int
    ):Response<MovieProviderResponse>


    // Añadir contenido a tu watchlist pasándole un objeto de tipo addWatchListBody como body y la id del usuario por URL (Path)
    @Headers(
        "accept: application/json",
        "Authorization: Bearer $BEARER_TOKEN"
    )
    @POST("account/{account_id}/watchlist")
    suspend fun addToWatchList(
        @Path("account_id") accountId : Int,
        @Body data : addWatchListBody
    ) : Response<WatchListResponse>

    // Añadir contenido a tus favoritos pasándole un objeto de tipo addFavoriteBody como body y la id del usuario por URL (Path)
    @Headers(
        "accept: application/json",
        "Authorization: Bearer $BEARER_TOKEN"
    )
    @POST("account/{account_id}/favorite")
    suspend fun addToFavorite(
        @Path("account_id") accountId : Int,
        @Body data : addFavoriteBody
    ) : Response<WatchListResponse>


    //Ambas funciones obtiene fotos del contenido deseando, se debe enviar una ID
    @Headers(
        "accept: application/json",
        "Authorization: Bearer $BEARER_TOKEN"
    )
    @GET("movie/{movie_id}/images")
    suspend fun getMovieImages(
        @Path("movie_id") movieId : Int
    ) : Response<ImageResponse>

    @Headers(
        "accept: application/json",
        "Authorization: Bearer $BEARER_TOKEN"
    )
    @GET("tv/{serie_id}/images")
    suspend fun getSerieImages(
        @Path("serie_id") serieId : Int
    ) : Response<ImageResponse>


    @Headers(
        "accept: application/json",
        "Authorization: Bearer $BEARER_TOKEN"
    )
    @GET("search/person")
    suspend fun getPersonBuscador(
        @Query("query") buscador: String
    ): Response<PeopleResponse>

    // Obtenemos todos los datos detallados de una Pelicula pasándole una Id de serie
    @Headers(
        "accept: application/json",
        "Authorization: Bearer $BEARER_TOKEN"
    )
    @GET("movie/{movie_id}")
    suspend fun getMovieById(
        @Path("movie_id") movieId : Int,
        @Query("language") language: String
    ): Response<MovieDetallesResponse>


    // Obtenemos todos los datos detallados de una Serie pasándole una Id de serie
    @Headers(
        "accept: application/json",
        "Authorization: Bearer $BEARER_TOKEN"
    )
    @GET("tv/{serie_id}")
    suspend fun getSerieById(
        @Path("serie_id") serieId: Int,
        @Query("language") language: String
    ): Response<SerieDetallesResponse>

    @Headers(
        "accept: application/json",
        "Authorization: Bearer $BEARER_TOKEN"
    )
    @GET("search/movie")
    suspend fun getMovieBuscar(
        @Query("query") textIntroducido : String
    ):Response<MovieResponse>


    @Headers(
        "accept: application/json",
        "Authorization: Bearer $BEARER_TOKEN"
    )
    @GET("search/person")
    suspend fun getPersonBuscar(
        @Query("query") textIntroducido : String
    ):Response<PeopleResponse>


    @Headers(
        "accept: application/json",
        "Authorization: Bearer $BEARER_TOKEN"
    )
    @GET("search/tv")
    suspend fun getTVBuscar(
        @Query("query") textIntroducido : String
    ):Response<TVResponse>

}