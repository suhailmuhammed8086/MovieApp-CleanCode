package com.app.moviesapp.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.moviesapp.ui.Screens
import com.app.moviesapp.ui.screens.home.HomeScreen
import com.app.moviesapp.ui.screens.search.SearchScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController ,Screens.Home.route){
                composable(Screens.Home.route){
                    HomeScreen(navController = navController)
                }
                composable(Screens.Detail.route){
                    Box(modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black))
                }
                composable(Screens.Search.route){
                    SearchScreen(navController = navController)
                }
                composable(Screens.Settings.route){
                    SearchScreen(navController = navController)
                }

            }
        }

//        viewModel.getDiscoverMovies()
    }
}

