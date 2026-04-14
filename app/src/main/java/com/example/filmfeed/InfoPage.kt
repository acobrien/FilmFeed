package com.example.filmfeed

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.filmfeed.ui.theme.FilmFeedTheme

@Composable
fun InfoPage(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painterResource(R.drawable.github_lockup_black_clearspace),
            contentDescription = "GitHub logo"
        )
        Spacer(modifier = Modifier.height(16.dp))
        val uriHandler = LocalUriHandler.current
        Text(
            text = "https://github.com/acobrien/FilmFeed",
            color = Color.Blue,
            textDecoration = TextDecoration.Underline,
            modifier = Modifier.clickable {
                uriHandler.openUri("https://github.com/acobrien/FilmFeed")
            }
        )
        Spacer(modifier = Modifier.height(120.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun InfoPagePreview() {
    FilmFeedTheme { InfoPage() }
}