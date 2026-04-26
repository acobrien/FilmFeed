package com.example.lab2raymondandobrien.database

import com.example.lab2raymondandobrien.models.Movie
import com.example.lab2raymondandobrien.models.Review

data class MovieUIState(
    val movies: List<Movie> = emptyList(),
    val selectedMovie: Movie? = null,
    val reviews: List<Review> = emptyList() // is to save the data received from the API
)
