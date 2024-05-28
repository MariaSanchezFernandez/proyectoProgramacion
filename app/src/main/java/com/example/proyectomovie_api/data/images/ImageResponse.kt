package com.example.proyectomovie_api.data.images

data class ImageResponse(
    val backdrops: List<Backdrop>,
    val id: Int,
    val logos: List<Logo>,
    val posters: List<Poster>
)