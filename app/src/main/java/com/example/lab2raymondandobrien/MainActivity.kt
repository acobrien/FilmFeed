package com.example.lab2raymondandobrien

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.lab2raymondandobrien.Routes.Routes
import com.example.lab2raymondandobrien.database.Movies
import com.example.lab2raymondandobrien.ui.theme.Lab2RaymondAndOBrienTheme
import com.example.lab2raymondandobrien.ui.theme.screens.DetailScreen
import com.example.lab2raymondandobrien.ui.theme.screens.ExtraScreen
import com.example.lab2raymondandobrien.ui.theme.screens.GridScreen
import com.example.lab2raymondandobrien.ui.theme.screens.InfoScreen
import com.example.lab2raymondandobrien.ui.theme.screens.ListScreen
import com.example.lab2raymondandobrien.viewmodel.MovieViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab2RaymondAndOBrienTheme {
                runApp()
                }
            }
        }
    }


@Composable
fun runApp() {

    val viewModel: MovieViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()

    val navController = rememberNavController()

    Scaffold(
        topBar = { MyTopBar (navController) }
    ) {innerpadding ->
        NavHost(
            navController = navController,
            startDestination = Routes.LIST
        ){
            composable(Routes.LIST) {
                ListScreen(
                    movies = uiState.movies,
                    onMovieClick = { movie ->
                        viewModel.setSelectedMovie(movie)
                        viewModel.loadReviews(movie.id)
                        navController.navigate(Routes.DETAIL)
                    },
                    modifier = Modifier.padding(innerpadding)
                )
            }

            composable(route = Routes.DETAIL){
                uiState.selectedMovie?.let { movie ->
                    DetailScreen(movie = movie, navController = navController, modifier = Modifier.padding(innerpadding))
                }
            }

            composable(Routes.INFO){
                InfoScreen(modifier = Modifier.padding(innerpadding))
            }

            composable(Routes.GRID) {
                GridScreen(
                    movies = uiState.movies,
                    onMovieClick = { movie ->
                        viewModel.setSelectedMovie(movie)
                        viewModel.loadReviews(movie.id)
                        navController.navigate(Routes.DETAIL)
                    },
                    modifier = Modifier.padding(innerpadding)
                )
            }
            composable(Routes.EXTRA) {
                ExtraScreen(uiState = uiState, modifier = Modifier.padding(innerpadding))
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar(navController: NavHostController){
    CenterAlignedTopAppBar(
        title = {
            Text("My Movie App")
        },

        //NavigationIcon stands for the button on the left.
        navigationIcon = {
            IconButton(onClick = {
                navController.navigate(Routes.LIST){ popUpTo(Routes.LIST) }
            }) {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home"
                )
            }
        },



        //actions stand for the button on the right.
        actions = {

            IconButton(onClick = {
                navController.navigate(Routes.GRID)
            }) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Grid View"
                )
            }

            IconButton(onClick = {
                navController.navigate(Routes.INFO)
            }) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "Info"
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Lab2RaymondAndOBrienTheme {
        runApp()
    }
}