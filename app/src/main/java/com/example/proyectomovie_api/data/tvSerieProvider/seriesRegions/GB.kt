package com.example.proyectomovie_api.data.tvSerieProvider.seriesRegions

import com.example.proyectomovie_api.data.tvSerieProvider.Buy
import com.example.proyectomovie_api.data.tvSerieProvider.Flatrate
import com.example.proyectomovie_api.data.tvSerieProvider.Free

data class GB(
    val buy: List<Buy>,
    val flatrate: List<Flatrate>,
    val free: List<Free>,
    val link: String
)