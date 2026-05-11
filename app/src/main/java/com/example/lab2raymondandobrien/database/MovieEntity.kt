package com.example.lab2raymondandobrien.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.lab2raymondandobrien.models.Movie

@Entity(tableName = "movies")
data class MovieEntity(
    @PrimaryKey val id: Long,
    val imdbId: String,
    val title: String,
    val releaseDate: String,
    val genres: List<String>,
    val overview: String,
    val homepageUrl: String,
    val posterPath: String,
    val backdropPath: String
)

fun MovieEntity.toMovie() = Movie(
    id = id,
    imdbId = imdbId,
    title = title,
    releaseDate = releaseDate,
    genres = genres,
    overview = overview,
    homepageUrl = homepageUrl,
    posterPath = posterPath,
    backdropPath = backdropPath
)

fun Movie.toEntity() = MovieEntity(
    id = id,
    imdbId = imdbId,
    title = title,
    releaseDate = releaseDate,
    genres = genres,
    overview = overview,
    homepageUrl = homepageUrl,
    posterPath = posterPath,
    backdropPath = backdropPath
)