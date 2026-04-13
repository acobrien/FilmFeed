package com.example.filmfeed

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.filmfeed.models.Movie
import com.example.filmfeed.ui.theme.FilmFeedTheme
import com.example.filmfeed.utils.Constants

// To show genre details and links to homepage & IMDB
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
        val uriHandler = LocalUriHandler.current

        Text(
            text = movie.homepageUrl,
            color = Color.Blue,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier.clickable { uriHandler.openUri(movie.homepageUrl) }
        )
        Text(
            text = movie.getImdbUrl(),
            color = Color.Blue,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier.clickable { uriHandler.openUri(movie.getImdbUrl()) }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DetailsPagePreview() {
    FilmFeedTheme {
        DetailsPage(
            Movie(
                id = 1290821,
                imdbId = "tt32357218",
                title = "Shelter",
                releaseDate = "2026-01-28",
                genres = listOf("Action", "Crime", "Thriller"),
                overview = "A man living in self-imposed exile on a remote island rescues a young girl from a violent storm, setting off a chain of events that forces him out of seclusion to protect her from enemies tied to his past.",
                homepageUrl = "https://blackbearpictures.com/film-and-tv/shelter",
                posterPath = "/buPFnHZ3xQy6vZEHxbHgL1Pc6CR.jpg",
                backdropPath = "/nHxWyy18SvAZ8jJeemtS8k1UNjM.jpg"
            )
        )
    }
}