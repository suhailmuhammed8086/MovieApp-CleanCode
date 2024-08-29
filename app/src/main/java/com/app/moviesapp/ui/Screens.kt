package com.app.moviesapp.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavArgument
import androidx.navigation.NavArgumentBuilder
import androidx.navigation.compose.composable

sealed class Screens(val route: String) {
    data object Home: Screens("home")
    data object Detail: Screens("detail")
    data object Search: Screens("search")
    data object Settings: Screens("settings")
    data object MovieList: Screens("movie_list")

}

sealed class BottomBarScreens(val route: String){
    data object MovieList: BottomBarScreens("movie_list")
    data object TvShowList: BottomBarScreens("Tv_show")
    data object Favourites: BottomBarScreens("favourites")
}


//class ParamRegister(private val screen: Screens) {
//
//    private val pathParams = arrayListOf<String>()
//    private val queryParams = arrayListOf<String>()
//    private val navArgument = HashMap<String,NavArgument>()
//    fun addPathParam(key: String): ParamRegister {
//        pathParams.add(key)
//        return this
//    }
//
//    fun addQueryParam(key: String): ParamRegister {
//        queryParams.add(key)
//        return this
//    }
//
//    public fun addNavArguments(
//        name: String,
//        builder: NavArgumentBuilder.() -> Unit
//    ): ParamRegister {
//        navArgument[name] = NavArgumentBuilder().apply(builder).build()
//        return this
//    }
//
//    @Composable
//    fun ToComposable() {
//        var path = screen.route
//        pathParams.forEach {
//            path+= "/{$it}"
//        }
////        if (path.endsWith("/")) path = path.dropLast(1)
//        queryParams.forEach {
//            path += "?$it={$it}"
//        }
//
//        composable()
//    }
//}