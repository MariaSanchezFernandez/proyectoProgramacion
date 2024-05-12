package com.example.proyectomovie_api.data.tvSerieProvider.seriesRegions

import com.example.proyectomovie_api.data.tvSerieProvider.Ad
import com.example.proyectomovie_api.data.tvSerieProvider.Flatrate

data class ES(
    val ads: List<Ad>,
    val flatrate: List<Flatrate>,
    val link: String
)