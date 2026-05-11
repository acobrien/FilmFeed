package com.example.lab2raymondandobrien.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.lab2raymondandobrien.models.Movie
import com.example.lab2raymondandobrien.utils.Constants

@Composable
fun GridScreen(
    movies: List<Movie>,
    viewType: String,
    onViewTypeChange: (String) -> Unit,
    onMovieClick: (Movie) -> Unit,
    modifier: Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        ViewTypeSelector(viewType, onViewTypeChange)

        if (movies.isEmpty()) {
            NoConnectionPlaceholder()
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier.fillMaxSize()
            ) {
                items(movies) { movie ->
                    MovieGridItem(movie, onMovieClick)
                }
            }
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
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(8.dp))
            AsyncImage(
                model = Constants.POSTER_IMAGE_BASE_URL + Constants.POSTER_IMAGE_BASE_WIDTH + movie.posterPath,
                contentDescription = movie.title,
                modifier = Modifier.height(150.dp)
            )
            // Fixed height reserves space for two lines so all cards are the same size
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = 4.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = movie.title,
                    textAlign = TextAlign.Center,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

