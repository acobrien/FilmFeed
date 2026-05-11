package com.example.lab2raymondandobrien.viewmodel

import com.example.lab2raymondandobrien.models.Movie
import com.example.lab2raymondandobrien.models.Review

data class MovieUIState(
    val movies: List<Movie> = emptyList(),
    val selectedMovie: Movie? = null,
    val reviews: List<Review> = emptyList(),
    val viewType: String = "popular"
)
