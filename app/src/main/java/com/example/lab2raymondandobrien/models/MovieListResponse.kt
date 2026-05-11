package com.example.lab2raymondandobrien.models

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

fun ApiMovie.toMovie(detail: MovieDetailResponse, position: Int) = Movie(
    id = id,
    position = position,
    imdbId = detail.imdbId,
    title = title,
    releaseDate = release_date,
    genres = detail.genres.map { it.name },
    overview = overview,
    homepageUrl = detail.homepage,
    posterPath = poster_path,
    backdropPath = backdrop_path
)
