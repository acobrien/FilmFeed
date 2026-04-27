package com.example.lab2raymondandobrien.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab2raymondandobrien.database.MovieUIState
import com.example.lab2raymondandobrien.database.Movies
import com.example.lab2raymondandobrien.models.Movie
import com.example.lab2raymondandobrien.remote.RetroFitInstance
import com.example.lab2raymondandobrien.utils.Constants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

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

    //this function/method will call internet, bring the reviews and save them in state.
    //it will be used whenever I select a movie
    fun loadReviews(movieId: Long){
        viewModelScope.launch {
            try {
                val response = RetroFitInstance.api.getReviews(
                    movieId,
                    Constants.API_KEY
                )

                _uiState.update { state ->
                    state.copy(reviews = response.results)
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

//in the viewModel now we are doing the logic part to separate it from the UI.