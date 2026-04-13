package com.example.filmfeed

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.filmfeed.ui.theme.FilmFeedTheme

@Composable
fun InfoPage(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "https://github.com/acobrien"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun InfoPagePreview() {
    FilmFeedTheme { InfoPage() }
}