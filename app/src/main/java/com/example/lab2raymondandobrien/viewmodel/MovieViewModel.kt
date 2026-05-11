package com.example.lab2raymondandobrien.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.lab2raymondandobrien.database.MovieDatabase
import com.example.lab2raymondandobrien.models.Movie
import com.example.lab2raymondandobrien.remote.RetroFitInstance
import com.example.lab2raymondandobrien.repository.MovieRepository
import com.example.lab2raymondandobrien.utils.Constants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MovieViewModel(application: Application) : AndroidViewModel(application) {
    private val _uiState = MutableStateFlow(MovieUIState())
    val uiState: StateFlow<MovieUIState> = _uiState.asStateFlow()

    private val repository = MovieRepository(MovieDatabase.getInstance(application))

    // Tracks which view type is currently stored in the DB so offline fallback
    // only serves cache when it matches what was requested.
    private var cachedViewType = "popular"

    private val connectivityManager =
        application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    // Automatically reloads the current view type when connectivity is restored.
    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            loadMovies(uiState.value.viewType)
        }
    }

    init {
        connectivityManager.registerDefaultNetworkCallback(networkCallback)
        loadMovies("popular")
    }

    private fun loadMovies(viewType: String) {
        viewModelScope.launch {
            val result = repository.getMovies(viewType)
            when {
                // Network success: update DB record and show results.
                result != null -> {
                    cachedViewType = viewType
                    _uiState.update { it.copy(movies = result) }
                }
                // Offline, same type as cache: serve the cached list.
                viewType == cachedViewType -> {
                    _uiState.update { it.copy(movies = repository.getCachedMovies()) }
                }
                // Offline, different type: show empty so the UI displays "no connection"
                // rather than showing the wrong cached list mislabeled as a different type.
                else -> {
                    _uiState.update { it.copy(movies = emptyList()) }
                }
            }
        }
    }

    fun switchViewType(viewType: String) {
        _uiState.update { it.copy(viewType = viewType) }
        loadMovies(viewType)
    }

    fun setSelectedMovie(movie: Movie) {
        _uiState.update { state -> state.copy(selectedMovie = movie) }
    }

    fun loadReviews(movieId: Long) {
        viewModelScope.launch {
            try {
                val response = RetroFitInstance.api.getReviews(movieId, Constants.API_KEY)
                _uiState.update { state -> state.copy(reviews = response.results) }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }
}
