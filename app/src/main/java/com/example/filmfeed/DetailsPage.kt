package com.example.filmfeed

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.filmfeed.database.Genres
import com.example.filmfeed.models.Movie
import com.example.filmfeed.ui.theme.FilmFeedTheme
import com.example.filmfeed.utils.Constants

// To show genre details and link for lab 1
@Composable
fun DetailsPage(movie: Movie, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = Constants.POSTER_IMAGE_BASE_URL
                    + Constants.POSTER_IMAGE_BASE_WIDTH
                    + movie.posterPath,
            contentDescription = movie.title,
            modifier = modifier
                .width(92.dp)
                .height(138.dp),
            contentScale = ContentScale.Crop
        )
        Text(
            text = "${movie.genres}"
        )
        Text( // TODO: Make link functional
            text = movie.getURL()
        )
        // TODO: Link to IMDB movie page
    }
}

@Preview(showBackground = true)
@Composable
fun DetailsPagePreview() {
    FilmFeedTheme {
        DetailsPage(
            Movie(
            id = 1290821,
            title = "Shelter",
            genres = Genres().getGenreNames(arrayOf(28, 80, 53)),
            releaseDate = "2026-01-28",
            overview = "A man living in self-imposed exile on a remote island rescues a young girl from a violent storm, setting off a chain of events that forces him out of seclusion to protect her from enemies tied to his past.",
            posterPath = "/buPFnHZ3xQy6vZEHxbHgL1Pc6CR.jpg",
            backdropPath = "/nHxWyy18SvAZ8jJeemtS8k1UNjM.jpg"
            )
        )
    }
}