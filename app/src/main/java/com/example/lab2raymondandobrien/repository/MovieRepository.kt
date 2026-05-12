package com.example.lab2raymondandobrien.repository

import com.example.lab2raymondandobrien.database.MovieDatabase
import com.example.lab2raymondandobrien.models.Movie
import com.example.lab2raymondandobrien.models.toMovie
import com.example.lab2raymondandobrien.remote.RetroFitInstance
import com.example.lab2raymondandobrien.utils.Constants

class MovieRepository(db: MovieDatabase) {
    private val dao = db.movieDao()
    private val api = RetroFitInstance.api

    // Returns null on network failure so the ViewModel can decide whether to fall back
    // to cache or show "no connection" depending on what type is currently stored.
    suspend fun getMovies(viewType: String): List<Movie>? {
        return try {
            val response = when (viewType) {
                "popular"    -> api.getPopularMovies(Constants.API_KEY)
                "top_rated"  -> api.getTopRatedMovies(Constants.API_KEY)
                "upcoming"   -> api.getUpcomingMovies(Constants.API_KEY)
                else         -> api.getNowPlayingMovies(Constants.API_KEY)
            }

            // One detail call per movie to populate genres, IMDB ID, and homepage.
            // The detail screen needs these fields.
            val movies = response.results.mapIndexed { index, apiMovie ->
                val detail = api.getMovieDetails(apiMovie.id, Constants.API_KEY)
                apiMovie.toMovie(detail, index)
            }

            dao.deleteAll()
            dao.insertAll(movies)
            movies
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }

    }

    suspend fun getCachedMovies(): List<Movie> = dao.getAll()
}
