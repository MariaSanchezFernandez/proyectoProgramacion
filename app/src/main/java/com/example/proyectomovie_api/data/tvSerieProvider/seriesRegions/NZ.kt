package com.example.proyectomovie_api.data.tvSerieProvider.seriesRegions

import com.example.proyectomovie_api.data.tvSerieProvider.Buy
import com.example.proyectomovie_api.data.tvSerieProvider.Flatrate

data class NZ(
    val buy: List<Buy>,
    val flatrate: List<Flatrate>,
    val link: String
)