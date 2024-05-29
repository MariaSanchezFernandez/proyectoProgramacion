package com.example.proyectomovie_api.data.serie_detalles


import com.google.gson.annotations.SerializedName

data class Network(
    @SerializedName("id")
    var id: Int,
    @SerializedName("logo_path")
    var logoPath: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("origin_country")
    var originCountry: String
)