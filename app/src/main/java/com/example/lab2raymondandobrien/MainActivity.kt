package com.example.lab2raymondandobrien

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lab2raymondandobrien.routes.Routes
import com.example.lab2raymondandobrien.ui.screens.DetailScreen
import com.example.lab2raymondandobrien.ui.screens.ExtraScreen
import com.example.lab2raymondandobrien.ui.screens.GridScreen
import com.example.lab2raymondandobrien.ui.screens.InfoScreen
import com.example.lab2raymondandobrien.ui.screens.ListScreen
import com.example.lab2raymondandobrien.ui.theme.Lab2RaymondAndOBrienTheme
import com.example.lab2raymondandobrien.viewmodel.MovieViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab2RaymondAndOBrienTheme {
                RunApp()
            }
        }
    }
}

@Composable
fun RunApp() {
    val viewModel: MovieViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()
    val navController = rememberNavController()
    var isGridView by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            MyTopBar(
                navController = navController,
                isGridView = isGridView,
                onToggleView = { isGridView = !isGridView }
            )
        }
    ) { innerpadding ->
        NavHost(
            navController = navController,
            startDestination = Routes.LIST
        ) {
            composable(Routes.LIST) {
                if (isGridView) {
                    GridScreen(
                        movies = uiState.movies,
                        viewType = uiState.viewType,
                        onViewTypeChange = viewModel::switchViewType,
                        onMovieClick = { movie ->
                            viewModel.setSelectedMovie(movie)
                            navController.navigate(Routes.DETAIL)
                        },
                        modifier = Modifier.padding(innerpadding)
                    )
                } else {
                    ListScreen(
                        movies = uiState.movies,
                        viewType = uiState.viewType,
                        onViewTypeChange = viewModel::switchViewType,
                        onMovieClick = { movie ->
                            viewModel.setSelectedMovie(movie)
                            navController.navigate(Routes.DETAIL)
                        },
                        modifier = Modifier.padding(innerpadding)
                    )
                }
            }

            composable(Routes.DETAIL) {
                uiState.selectedMovie?.let { movie ->
                    DetailScreen(movie = movie, navController = navController, modifier = Modifier.padding(innerpadding))
                }
            }

            composable(Routes.INFO) {
                InfoScreen(modifier = Modifier.padding(innerpadding))
            }

            composable(Routes.EXTRA) {
                ExtraScreen(uiState = uiState, modifier = Modifier.padding(innerpadding))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar(navController: NavHostController, isGridView: Boolean, onToggleView: () -> Unit) {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    val isOnList = currentRoute == Routes.LIST
    val isOnInfo = currentRoute == Routes.INFO

    CenterAlignedTopAppBar(
        title = { Text("FilmFeed") },
        navigationIcon = {
            if (isOnList) {
                IconButton(onClick = onToggleView) {
                    Icon(
                        imageVector = if (isGridView) Icons.Default.Menu else Icons.Default.GridView,
                        contentDescription = if (isGridView) "List View" else "Grid View"
                    )
                }
            } else {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        },
        actions = {
            IconButton(onClick = {
                if (!isOnInfo) navController.navigate(Routes.INFO) { popUpTo(Routes.LIST) }
            }) {
                Icon(imageVector = Icons.Default.Info, contentDescription = "Info")
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Lab2RaymondAndOBrienTheme {
        RunApp()
    }
}
