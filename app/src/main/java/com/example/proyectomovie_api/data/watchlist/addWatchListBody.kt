package com.example.proyectomovie_api.data.watchlist

data class addWatchListBody(
    val media_type: String,
    val media_id: Int,
    val watchlist: Boolean
)