package com.example.lab2raymondandobrien.database

import com.example.lab2raymondandobrien.models.Movie

data class MovieUIState(
    val movies: List<Movie> = emptyList(),
    val selectedMovie: Movie? = null
)
