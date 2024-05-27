package com.example.proyectomovie_api.data.buscador


import com.google.gson.annotations.SerializedName

data class BuscadorMultiResponse(
    @SerializedName("page")
    var page: Int?,
    @SerializedName("results")
    var results: List<Result?>?,
    @SerializedName("total_pages")
    var totalPages: Int?,
    @SerializedName("total_results")
    var totalResults: Int?
)