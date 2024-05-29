package com.example.proyectomovie_api.data.favorite

data class addFavoriteBody(
    val media_type: String,
    val media_id: Int,
    val favorite: Boolean
)