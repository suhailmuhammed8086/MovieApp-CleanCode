package com.app.moviesapp.ui

sealed class Screens(val route: String) {
    data object Home: Screens("home")
    data object Detail: Screens("detail")
    data object Search: Screens("search")
    data object Settings: Screens("settings")
}

sealed class BottomBarScreens(val route: String){
    data object MovieList: BottomBarScreens("movie_list")
    data object TvShowList: BottomBarScreens("Tv_show")
    data object Favourites: BottomBarScreens("favourites")
}