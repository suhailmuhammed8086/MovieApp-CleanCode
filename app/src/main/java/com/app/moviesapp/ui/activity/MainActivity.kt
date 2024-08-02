package com.app.moviesapp.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.app.moviesapp.ui.NavRoutes
import com.app.moviesapp.ui.screens.home.HomeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
//    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController ,NavRoutes.HOME_SCREEN){
                composable(NavRoutes.HOME_SCREEN){
                    HomeScreen(/*navController = navController*/)
                }

            }
        }

//        viewModel.getDiscoverMovies()
    }
}

