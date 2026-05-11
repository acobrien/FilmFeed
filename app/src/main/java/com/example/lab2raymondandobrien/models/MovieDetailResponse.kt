package com.example.lab2raymondandobrien.models

data class MovieDetailResponse(
    val id: Long,
    val imdbId: String,
    val homepage: String,
    val genres: List<Genre>
)

data class Genre(val name: String)
