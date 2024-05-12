package com.example.proyectomovie_api.data.tvSerieProvider.seriesRegions

import com.example.proyectomovie_api.data.tvSerieProvider.Ad
import com.example.proyectomovie_api.data.tvSerieProvider.Buy
import com.example.proyectomovie_api.data.tvSerieProvider.Flatrate
import com.example.proyectomovie_api.data.tvSerieProvider.Rent

data class FR(
    val ads: List<Ad>,
    val buy: List<Buy>,
    val flatrate: List<Flatrate>,
    val link: String,
    val rent: List<Rent>
)