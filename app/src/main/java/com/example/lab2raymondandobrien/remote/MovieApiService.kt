package com.example.lab2raymondandobrien.remote

import com.example.lab2raymondandobrien.models.ReviewResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService{
    @GET("movie/{movie_id}/reviews") //endpoint
    suspend fun getReviews(
        @Path("movie_id") movieId: Long, //will replace movieid
        @Query("api_key") apiKey: String //add  ?api_key=...
    ): ReviewResponse
}