package com.example.proyectomovie_api.data.people

data class PeopleResponse(
    val page: Int,
    val results: List<People>,
    val total_pages: Int,
    val total_results: Int
)