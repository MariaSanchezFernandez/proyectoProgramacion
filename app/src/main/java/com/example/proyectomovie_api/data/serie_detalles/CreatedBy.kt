package com.example.proyectomovie_api.data.serie_detalles


import com.google.gson.annotations.SerializedName

data class CreatedBy(
    @SerializedName("credit_id")
    var creditId: String,
    @SerializedName("gender")
    var gender: Int,
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("original_name")
    var originalName: String,
    @SerializedName("profile_path")
    var profilePath: String
)