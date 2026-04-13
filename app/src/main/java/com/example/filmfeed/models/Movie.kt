package com.example.filmfeed.models 

data class Movie(
    var id: Long = 0L,
    var title: String,
    var releaseDate: String,
    var genres: List<String>,
    var overview: String,
    var posterPath: String,
    var backdropPath: String,
) {
    fun getURL(): String {
        val formattedTitle = title
            .lowercase()
            // Claude-Generated: Replace any run of non-alphanumeric chars with a single hyphen
            .replace(Regex("[^a-z0-9]+"), "-")

        return "https://www.themoviedb.org/movie/${id}-${formattedTitle}"
    }
}