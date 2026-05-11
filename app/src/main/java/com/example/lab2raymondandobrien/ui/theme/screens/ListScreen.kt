package com.example.lab2raymondandobrien.ui.theme.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.lab2raymondandobrien.models.Movie
import com.example.lab2raymondandobrien.utils.Constants

@Composable
fun ListScreen(
    movies: List<Movie>,
    onMovieClick: (Movie) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn (modifier = modifier.fillMaxSize(), contentPadding = PaddingValues(top = 8.dp)) {
        items(movies) { movie ->
            MovieItem(movie, onMovieClick)
        }
    }
}

@Composable
fun MovieItem(movie: Movie, onMovieClick: (Movie) -> Unit){
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp).clickable{ onMovieClick(movie) }
    ){
        Row{
            Box{
                AsyncImage(
                    model = Constants.POSTER_IMAGE_BASE_URL
                            + Constants.POSTER_IMAGE_BASE_WIDTH
                            + movie.posterPath,
                    contentDescription = movie.title,
                    modifier = Modifier.width(92.dp).height(138.dp),
                    contentScale = ContentScale.Crop
                )
            }
            Column(modifier = Modifier.padding(16.dp)){
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.titleLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = movie.overview,
                    maxLines = 2
                )
            }
        }

    }
}