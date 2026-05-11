package com.example.lab2raymondandobrien.models

data class MovieDetailResponse(
    val id: Long,
    val imdb_id: String,
    val homepage: String,
    val genres: List<Genre>
)

data class Genre(val name: String)

fun MovieDetailResponse.applyTo(movie: Movie) = movie.copy(
    imdbId = imdb_id,
    homepageUrl = homepage,
    genres = genres.map { it.name }
)
