package com.example.lab2raymondandobrien.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.lab2raymondandobrien.database.MovieDatabase
import com.example.lab2raymondandobrien.repository.MovieRepository

class MovieSyncWorker(appContext: Context, params: WorkerParameters) :
    CoroutineWorker(appContext, params) {

    override suspend fun doWork(): Result {
        val viewType = inputData.getString("viewType") ?: "popular"
        val repo = MovieRepository(MovieDatabase.getInstance(applicationContext))
        return if (repo.getMovies(viewType) != null) Result.success() else Result.retry()
    }
}
