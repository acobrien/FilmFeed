package com.example.lab2raymondandobrien.models

import com.example.lab2raymondandobrien.database.MovieEntity

data class MovieListResponse(
    val results: List<ApiMovie>
)

data class ApiMovie(
    val id: Long,
    val title: String,
    val overview: String,
    val release_date: String,
    val poster_path: String,
    val backdrop_path: String
)

fun ApiMovie.toEntity() = MovieEntity(
    id = id,
    imdbId = "",
    title = title,
    releaseDate = release_date,
    genres = emptyList(),
    overview = overview,
    homepageUrl = "",
    posterPath = poster_path,
    backdropPath = backdrop_path
)
