package com.example.proyectomovie_api.data.serie_detalles


import com.google.gson.annotations.SerializedName

data class LastEpisodeToAir(
    @SerializedName("air_date")
    var airDate: String,
    @SerializedName("episode_number")
    var episodeNumber: Int,
    @SerializedName("episode_type")
    var episodeType: String,
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("overview")
    var overview: String,
    @SerializedName("production_code")
    var productionCode: String,
    @SerializedName("runtime")
    var runtime: Int,
    @SerializedName("season_number")
    var seasonNumber: Int,
    @SerializedName("show_id")
    var showId: Int,
    @SerializedName("still_path")
    var stillPath: String,
    @SerializedName("vote_average")
    var voteAverage: Double,
    @SerializedName("vote_count")
    var voteCount: Int
)