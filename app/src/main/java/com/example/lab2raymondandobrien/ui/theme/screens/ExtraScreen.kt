package com.example.lab2raymondandobrien.ui.theme.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ExtraScreen() {

    Column(modifier = Modifier.fillMaxSize()) {

        Text("Reviews")

        ReviewList()

        Spacer(modifier = Modifier.height(16.dp))

        Text("Videos")

        VideoList()
    }
}

@Composable
fun ReviewList() {

    val reviews = getReviews()

    LazyRow {
        items(reviews) { review ->
            Card(modifier = Modifier.padding(8.dp)) {
                Column(modifier = Modifier.padding(8.dp)) {
                    Text(review.author)
                    Text(review.content)
                }
            }
        }
    }
}