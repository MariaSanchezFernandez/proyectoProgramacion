package com.example.proyectomovie_api.data.serie_detalles


import com.google.gson.annotations.SerializedName

data class SerieDetallesResponse(
    @SerializedName("adult")
    var adult: Boolean,
    @SerializedName("backdrop_path")
    var backdropPath: String,
    @SerializedName("created_by")
    var createdBy: List<CreatedBy>,
    @SerializedName("episode_run_time")
    var episodeRunTime: List<Int>,
    @SerializedName("first_air_date")
    var firstAirDate: String,
    @SerializedName("genres")
    var genres: List<Genre>,
    @SerializedName("homepage")
    var homepage: String,
    @SerializedName("id")
    var id: Int,
    @SerializedName("in_production")
    var inProduction: Boolean,
    @SerializedName("languages")
    var languages: List<String>,
    @SerializedName("last_air_date")
    var lastAirDate: String,
    @SerializedName("last_episode_to_air")
    var lastEpisodeToAir: LastEpisodeToAir,
    @SerializedName("name")
    var name: String,
    @SerializedName("networks")
    var networks: List<Network>,
    @SerializedName("next_episode_to_air")
    var nextEpisodeToAir: Any,
    @SerializedName("number_of_episodes")
    var numberOfEpisodes: Int,
    @SerializedName("number_of_seasons")
    var numberOfSeasons: Int,
    @SerializedName("origin_country")
    var originCountry: List<String>,
    @SerializedName("original_language")
    var originalLanguage: String,
    @SerializedName("original_name")
    var originalName: String,
    @SerializedName("overview")
    var overview: String,
    @SerializedName("popularity")
    var popularity: Double,
    @SerializedName("poster_path")
    var posterPath: String,
    @SerializedName("production_companies")
    var productionCompanies: List<ProductionCompany>,
    @SerializedName("production_countries")
    var productionCountries: List<ProductionCountry>,
    @SerializedName("seasons")
    var seasons: List<Season>,
    @SerializedName("spoken_languages")
    var spokenLanguages: List<SpokenLanguage>,
    @SerializedName("status")
    var status: String,
    @SerializedName("tagline")
    var tagline: String,
    @SerializedName("type")
    var type: String,
    @SerializedName("vote_average")
    var voteAverage: Double,
    @SerializedName("vote_count")
    var voteCount: Int
)