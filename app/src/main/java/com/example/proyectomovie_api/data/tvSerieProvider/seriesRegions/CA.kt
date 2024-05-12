package com.example.proyectomovie_api.data.tvSerieProvider.seriesRegions

import com.example.proyectomovie_api.data.tvSerieProvider.Ad
import com.example.proyectomovie_api.data.tvSerieProvider.Buy
import com.example.proyectomovie_api.data.tvSerieProvider.Flatrate

data class CA(
    val ads: List<Ad>,
    val buy: List<Buy>,
    val flatrate: List<Flatrate>,
    val link: String
)