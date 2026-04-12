package com.example.filmfeed

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.filmfeed.database.Movies
import com.example.filmfeed.ui.theme.FilmFeedTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FilmFeedTheme {
                FilmFeedApp()
            }
        }
    }
}

@Composable
fun FilmFeedApp() {
    Scaffold(
        topBar = { TopBar() }
    ) { innerPadding ->
        MovieList(
            movieList = Movies().getMovies(),
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(/* TODO */) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                stringResource(R.string.app_name),
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            ) },
        navigationIcon = {
            IconButton(onClick = { /* TODO */ }) {
                Icon(
                    // imageVector = Icons.Default.Home,
                    // contentDescription = "Go to homepage",
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Open menu",
                    tint = Color.White
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF48A860),
            titleContentColor = Color.White
        )
    )
}

@Preview(showBackground = true)
@Composable
fun FilmFeedAppPreview() {
    FilmFeedTheme {
        FilmFeedApp()
    }
}