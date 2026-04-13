package com.example.filmfeed

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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
    val navController = rememberNavController()

    Scaffold(
        topBar = { TopBar(navController) } // TopBar needs to see current route
    ) { innerPadding ->

        // Navigation manager
        NavHost(
            navController = navController,
            startDestination = Routes.SCROLL // Start on ScrollPage
        ) {
            // ScrollPage
            composable(Routes.SCROLL) {
                ScrollPage(
                    movieList = Movies.getMovies(),
                    navController = navController,
                    modifier = Modifier.padding(innerPadding)
                )
            }

            // DetailsPage
            composable(
                route = Routes.DETAILS,
                arguments = listOf(navArgument("movieId") { type = NavType.LongType })
            ) { backStackEntry -> // backStackEntry holds navigation info
                // Pull movieId out of arguments
                val movieId = backStackEntry.arguments?.getLong("movieId")
                val movie = Movies.getMovies().first { it.id == movieId }
                DetailsPage(
                    movie = movie,
                    modifier = Modifier.padding(innerPadding)
                )
            }

            // InfoPage
            composable(Routes.INFO) {
                InfoPage(modifier = Modifier.padding(innerPadding))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navController: NavHostController) {
    // currentBackStackEntryAsState() gives current state, drill down to route
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

    CenterAlignedTopAppBar(
        title = {
            Text(
                stringResource(R.string.app_name),
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            ) },
        navigationIcon = {
            if (currentRoute == Routes.SCROLL) {
                // If on ScrollPage, show Info icon to navigate to InfoPage
                IconButton(onClick = { navController.navigate(Routes.INFO) }) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = "Developer info",
                        tint = Color.White
                    )
                }
            } else { // Show home icon to navigate back to ScrollPage
                IconButton(onClick = {
                    navController.navigate(Routes.SCROLL) {
                        popUpTo(0) // Clear back stack to prevent history pileup
                    }
                }) {
                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = "Homepage",
                        tint = Color.White
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF48A860),
            titleContentColor = Color.White
        )
    )
}