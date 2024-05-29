package com.example.proyectomovie_api.data.movie_detalles


import com.google.gson.annotations.SerializedName

data class Genre(
    @SerializedName("id")
    var id: Int?,
    @SerializedName("name")
    var name: String?
)