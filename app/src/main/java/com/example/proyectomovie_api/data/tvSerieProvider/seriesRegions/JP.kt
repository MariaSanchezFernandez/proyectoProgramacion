package com.example.proyectomovie_api.data.tvSerieProvider.seriesRegions

import com.example.proyectomovie_api.data.tvSerieProvider.Buy
import com.example.proyectomovie_api.data.tvSerieProvider.Rent

data class JP(
    val buy: List<Buy>,
    val link: String,
    val rent: List<Rent>
)