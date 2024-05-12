package com.example.proyectomovie_api.data.tv

data class TVResponse(
    val page: Int,
    val results: List<TVShow>,
    val total_pages: Int,
    val total_results: Int
)