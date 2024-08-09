package com.app.moviesapp.ui.screens.home

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.app.moviesapp.R
import com.app.moviesapp.repository.movie.MovieRepository
import com.app.moviesapp.ui.BottomBarScreens
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val repo: MovieRepository
): ViewModel(){
    val bottomBarSelectedOption = mutableIntStateOf(OPTION_MOVIES)


    val options = listOf(
        BottomOption(OPTION_MOVIES, "Movies", R.drawable.ic_movies, BottomBarScreens.MovieList.route),
        BottomOption(OPTION_TV_SHOWS, "Tv Shows", R.drawable.ic_tv_shows, BottomBarScreens.TvShowList.route),
        BottomOption(OPTION_FAVS, "Favourites", R.drawable.ic_favourite, BottomBarScreens.Favourites.route)
    )

    companion object {
        const val OPTION_MOVIES = 1
        const val OPTION_TV_SHOWS = 2
        const val OPTION_FAVS = 3
    }
}