package com.example.lab2raymondandobrien.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.lab2raymondandobrien.database.MovieDatabase
import com.example.lab2raymondandobrien.models.Movie
import com.example.lab2raymondandobrien.repository.MovieRepository
import com.example.lab2raymondandobrien.worker.MovieSyncWorker
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MovieViewModel(application: Application) : AndroidViewModel(application) {
    private val _uiState = MutableStateFlow(MovieUIState())
    val uiState: StateFlow<MovieUIState> = _uiState.asStateFlow()

    private val repository = MovieRepository(MovieDatabase.getInstance(application))
    private val workManager = WorkManager.getInstance(application)
    private var cachedViewType = "popular"
    private var loadJob: Job? = null

    init {
        viewModelScope.launch {
            workManager.getWorkInfosForUniqueWorkFlow("movie_sync")
                .collect { infos ->
                    if (infos.firstOrNull()?.state == WorkInfo.State.SUCCEEDED) {
                        _uiState.update { it.copy(movies = repository.getCachedMovies()) }
                    }
                }
        }
        loadMovies("popular")
        enqueueSync("popular")
    }

    private fun enqueueSync(viewType: String) {
        val request = OneTimeWorkRequestBuilder<MovieSyncWorker>()
            .setConstraints(Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build())
            .setInputData(workDataOf("viewType" to viewType))
            .build()
        workManager.enqueueUniqueWork("movie_sync", ExistingWorkPolicy.REPLACE, request)
    }

    private fun loadMovies(viewType: String) {
        loadJob?.cancel()
        loadJob = viewModelScope.launch {
            val result = repository.getMovies(viewType)
            when {
                result != null -> {
                    cachedViewType = viewType
                    _uiState.update { it.copy(movies = result) }
                }
                viewType == cachedViewType -> {
                    _uiState.update { it.copy(movies = repository.getCachedMovies()) }
                }
                else -> {
                    _uiState.update { it.copy(movies = emptyList()) }
                }
            }
        }
    }

    fun switchViewType(viewType: String) {
        _uiState.update { it.copy(viewType = viewType) }
        loadMovies(viewType)
        enqueueSync(viewType)
    }

    fun setSelectedMovie(movie: Movie) {
        _uiState.update { state -> state.copy(selectedMovie = movie, reviews = movie.reviews) }
    }
}
