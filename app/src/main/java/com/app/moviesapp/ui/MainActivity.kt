package com.app.moviesapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.app.moviesapp.ui.screens.home.HomeScreen
import com.app.moviesapp.ui.screens.movie.details.MovieDetailsScreen
import com.app.moviesapp.ui.screens.movie.list.MovieListScreen
import com.app.moviesapp.ui.screens.search.SearchScreen
import com.app.moviesapp.ui.theme.MoviesAppTheme
import com.app.moviesapp.ui.utils.composableWithArgs
import com.app.moviesapp.utils.constants.ArgKeys
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MoviesAppTheme {
                val navController = rememberNavController()
                NavHost(navController = navController ,Screens.Home.route){
                    composable(Screens.Home.route){
                        HomeScreen(navController = navController)
                    }
                    composableWithArgs(
                        route = Screens.Detail.route,
                        arguments = listOf(
                            navArgument(ArgKeys.MOVIE_ID){
                                type = NavType.LongType
                                defaultValue = 0L
                            },
                        )
                    ){
                        MovieDetailsScreen( navController = navController)
                    }
                    composable(Screens.Search.route){
                        SearchScreen(navController = navController)
                    }
                    composable(Screens.Settings.route){
                        SearchScreen(navController = navController)
                    }

                    composableWithArgs(
                        route = Screens.MovieList.route,
                        arguments = listOf(
                            navArgument(ArgKeys.MOVIE_PAGE_TYPE){
                                type = NavType.StringType
                                nullable = true
                            },
                            navArgument(ArgKeys.PAGE_TITLE){
                                type = NavType.StringType
                                nullable = true
                            },
                            navArgument(ArgKeys.GENRE_ID){
                                type = NavType.IntType
                                defaultValue = -1
                            }
                        )
                    ){
                        val title = it.arguments?.getString(ArgKeys.PAGE_TITLE) ?: ""
                        MovieListScreen(title,navController = navController)
                    }

                }
            }
        }
    }
}

