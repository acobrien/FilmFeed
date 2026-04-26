package com.example.lab2raymondandobrien.ui.theme.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import com.example.lab2raymondandobrien.models.Movie
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.lab2raymondandobrien.utils.Constants

@Composable
fun GridScreen(
    movies: List<Movie>,
    onMovieClick: (Movie) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2) // 👉 n = 2
    ) {
        items(movies) { movie ->
            MovieGridItem(movie, onMovieClick)
        }
    }
}

@Composable
fun MovieGridItem(
    movie: Movie,
    onMovieClick: (Movie) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable { onMovieClick(movie) }
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            AsyncImage(
                model = Constants.POSTER_IMAGE_BASE_URL +
                        Constants.POSTER_IMAGE_BASE_WIDTH +
                        movie.posterPath,
                contentDescription = movie.title,
                modifier = Modifier.height(150.dp)
            )

            Text(movie.title)
        }
    }
}