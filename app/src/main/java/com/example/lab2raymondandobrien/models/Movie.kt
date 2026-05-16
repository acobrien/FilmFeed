package com.example.lab2raymondandobrien.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class Movie(
    @PrimaryKey var id: Long = 0L,
    var position: Int = 0,  // Display order
    var imdbId: String = "",
    var title: String = "",
    var releaseDate: String = "",
    var genres: List<String> = emptyList(),
    var overview: String = "",
    var homepageUrl: String = "",
    var posterPath: String = "",
    var backdropPath: String = "",
    var reviews: List<Review> = emptyList()
) {
    fun getImdbUrl(): String = "https://www.imdb.com/title/$imdbId/"
}
