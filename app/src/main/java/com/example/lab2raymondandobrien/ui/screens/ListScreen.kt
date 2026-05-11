package com.example.lab2raymondandobrien.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.lab2raymondandobrien.models.Movie
import com.example.lab2raymondandobrien.utils.Constants

private val VIEW_TYPES = listOf(
    "popular" to "Popular",
    "top_rated" to "Top Rated",
    "upcoming" to "Upcoming",
    "now_playing" to "Now Playing"
)

@Composable
fun ListScreen(
    movies: List<Movie>,
    viewType: String,
    onViewTypeChange: (String) -> Unit,
    onMovieClick: (Movie) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        ViewTypeSelector(viewType, onViewTypeChange)

        if (movies.isEmpty()) {
            NoConnectionPlaceholder()
        } else {
            LazyColumn(contentPadding = PaddingValues(top = 8.dp)) {
                items(movies) { movie ->
                    MovieItem(movie, onMovieClick)
                }
            }
        }
    }
}

@Composable
fun MovieItem(movie: Movie, onMovieClick: (Movie) -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp).clickable { onMovieClick(movie) }
    ) {
        Row {
            AsyncImage(
                model = Constants.POSTER_IMAGE_BASE_URL + Constants.POSTER_IMAGE_BASE_WIDTH + movie.posterPath,
                contentDescription = movie.title,
                modifier = Modifier.width(92.dp).height(138.dp),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = movie.overview,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

// Shared composables used by both ListScreen and GridScreen

@Composable
fun ViewTypeSelector(viewType: String, onViewTypeChange: (String) -> Unit) {
    Row(
        modifier = Modifier
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        VIEW_TYPES.forEachIndexed { index, (type, label) ->
            if (index > 0) Spacer(modifier = Modifier.width(8.dp))
            if (viewType == type) {
                Button(onClick = {}) { Text(label) }
            } else {
                OutlinedButton(onClick = { onViewTypeChange(type) }) { Text(label) }
            }
        }
    }
}

@Composable
fun NoConnectionPlaceholder() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector = Icons.Default.WifiOff,
                contentDescription = null,
                modifier = Modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text("No connection")
        }
    }
}

