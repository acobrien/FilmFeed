package com.example.lab2raymondandobrien.viewmodel

import androidx.lifecycle.ViewModel
import com.example.lab2raymondandobrien.database.MovieUIState
import com.example.lab2raymondandobrien.database.Movies
import com.example.lab2raymondandobrien.models.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MovieViewModel : ViewModel(){
    private val _uiState = MutableStateFlow(MovieUIState())
    val uiState: StateFlow<MovieUIState> = _uiState.asStateFlow()

    init {
        loadMovies()
    }
    private fun loadMovies(){
        _uiState.update { state -> state.copy(movies = Movies.getMovies()) }
    }

    fun setSelectedMovie(movie: Movie){
        _uiState.update { state -> state.copy(selectedMovie = movie) }
    }
}