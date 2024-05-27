package com.example.proyectomovie_api.data

import android.graphics.Region
import com.example.proyectomovie_api.data.favorite.addFavoriteBody
import com.example.proyectomovie_api.data.retrofit.RetrofitHelper
import com.example.proyectomovie_api.data.watchlist.addWatchListBody

class Repository {

    suspend fun getPopularMovies(apiKey:String) = RetrofitHelper.retrofitService.popularMovies(apiKey, "ES")

    suspend fun getPopularTVShows(apiKey:String) = RetrofitHelper.retrofitService.popularTVShows(apiKey, "ES")

    suspend fun getMovieProviders(apiKey: String, movieID:Int) = RetrofitHelper.retrofitService.getMovieProvider(apiKey,movieID)

    suspend fun getTVShowProviders(apiKey: String, tvID:Int) = RetrofitHelper.retrofitService.getTVShowProvider(apiKey,tvID)

    suspend fun topRatedTVShows(apiKey: String) = RetrofitHelper.retrofitService.topRatedTVShows(apiKey, "ES")

    suspend fun topRatedMovies(apiKey: String) = RetrofitHelper.retrofitService.topRatedMovies(apiKey, "ES")

    suspend fun getMovieWatchProvider(apiKey: String, movieId:Int) = RetrofitHelper.retrofitService.getMovieWatchProvider(apiKey, movieId)
    suspend fun getSerieWatchProvider(apiKey: String, serieId:Int) = RetrofitHelper.retrofitService.getSerieWatchProvider(apiKey, serieId)
    suspend fun addToWatchList(apiKey: String, accountId: Int, data : addWatchListBody) = RetrofitHelper.retrofitService.addToWatchList(apiKey,accountId,data)
    suspend fun addToFavorite(apiKey: String, accountId: Int, data : addFavoriteBody) = RetrofitHelper.retrofitService.addToFavorite(apiKey,accountId,data)
    suspend fun getSerieImages(apiKey: String, serieId: Int) = RetrofitHelper.retrofitService.getSerieImages(apiKey, serieId)
    suspend fun getMovieimages(apiKey: String, movieId: Int) = RetrofitHelper.retrofitService.getMovieImages(apiKey, movieId)


}