package com.example.lab2raymondandobrien.ui.theme.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.lab2raymondandobrien.database.MovieUIState
import com.example.lab2raymondandobrien.models.Review
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

@Composable
fun ExtraScreen(
    uiState: MovieUIState
) {
    Column {

        Text("Reviews")

        ReviewList(uiState.reviews)

        Spacer(modifier = Modifier.height(16.dp))

        Text("Videos")

        VideoList()
    }
}

@Composable
fun ReviewList(reviews: List<Review>) {

    LazyRow {
        items(reviews) { review ->

            Card(modifier = Modifier.padding(8.dp)) {

                Column(modifier = Modifier.padding(8.dp)) {

                    Text(text = review.author)

                    Text(
                        text = review.content,
                        maxLines = 3
                    )
                }
            }
        }
    }
}

@Composable
fun VideoList() {
    LazyRow {
        items(3) {
            VideoPlayer()
        }
    }
}

@Composable
fun VideoPlayer() {

    val context = LocalContext.current

    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(
                MediaItem.fromUri("https://storage.googleapis.com/exoplayer-test-media-0/BigBuckBunny_320x180.mp4")
            )
            prepare()
        }
    }

    AndroidView(
        factory = {
            PlayerView(it).apply {
                player = exoPlayer
            }
        },
        modifier = Modifier
            .height(200.dp)
            .width(300.dp)
    )
}