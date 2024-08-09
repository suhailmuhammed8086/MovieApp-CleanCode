package com.app.moviesapp.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.SubcomposeAsyncImage
import com.app.moviesapp.R
import com.app.moviesapp.network.model.response.movies.MovieModel
import com.app.moviesapp.ui.BottomBarScreens
import com.app.moviesapp.ui.Screens
import com.app.moviesapp.ui.activity.ui.theme.homeScreenIconSize
import com.app.moviesapp.ui.screens.movie.home.MovieHomeScreen
import com.app.moviesapp.ui.screens.movie.home.MovieHomeViewModel
import com.app.moviesapp.ui.screens.tv.home.TvShowHomeScreen
import com.app.moviesapp.ui.theme.poppinsFont
import com.app.moviesapp.ui.theme.topBarTitleStyle
import com.app.moviesapp.ui.utils.VSpace


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: HomeScreenViewModel = hiltViewModel(),
) {
    val bottomNavController = rememberNavController()

    Scaffold(
        modifier
            .fillMaxSize()
            .background(color = Color.Black),
        topBar = {
            TopBar(
                title = LocalContext.current.getString(R.string.app_name),
                onSearchClick = {
                    navController.navigate(Screens.Search.route)
                },
                onSettingsClick = {
                    navController.navigate(Screens.Settings.route)
                }
            )
        },
        content = {padValue->
            NavHost(navController = bottomNavController, startDestination = BottomBarScreens.MovieList.route,){
                composable(BottomBarScreens.MovieList.route,){
                    MovieHomeScreen(paddingValues = padValue, navController = navController)
                }
                composable(BottomBarScreens.TvShowList.route){
                    TvShowHomeScreen(paddingValues = padValue, navController = navController)
                }
                composable(BottomBarScreens.Favourites.route){
                    DummyScreen(text = "Favorites \n Coming soon")
                }
            }

        },
        bottomBar = {

            BottomBar(
                options = viewModel.options,
                defaultSelection = viewModel.bottomBarSelectedOption.intValue,
                onOptionClick = { option ->
                    bottomNavController.navigate(option.route){
                        bottomNavController.popBackStack()
                        launchSingleTop = true
                    }
                    viewModel.bottomBarSelectedOption.intValue = option.id
                }
            )
        }
    )

}

@Composable
fun TopBar(title: String, onSearchClick: () -> Unit, onSettingsClick: () -> Unit) {
    Box(
        modifier = Modifier
            .defaultMinSize(minHeight = 70.dp)
            .fillMaxWidth()
            .background(color = Color.Black)
            .padding(horizontal = 15.dp)
    ) {
        Icon(
            Icons.Filled.Search,
            tint = Color.White,
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.CenterStart)
                .size(homeScreenIconSize)
                .clickable(onClick = onSearchClick)
        )
        Text(
            text = title,
            style = topBarTitleStyle,
            modifier = Modifier
                .align(Alignment.Center),
            onTextLayout = {}
        )
        Icon(
            Icons.Filled.Settings,
            tint = Color.White,
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .size(homeScreenIconSize)
                .clickable(onClick = onSettingsClick)
        )
    }
}



@Composable
fun ErrorText(modifier: Modifier = Modifier, errorText: String, errorTextColor: Color = Color.White, onRetry: (()->Unit)? = null) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = errorText,
            style = TextStyle(
                fontFamily = poppinsFont,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                color = errorTextColor
            )
        )
        if (onRetry != null) {
            VSpace(space = 10.dp)
            Text(
                text = "Retry",
                style = TextStyle(
                    fontFamily = poppinsFont,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = errorTextColor
                ),
                modifier = Modifier
                    .clickable(onClick = onRetry)
            )
        }
    }
}

data class BottomOption(
    val id: Int,
    val name: String,
    val icon: Int,
    val route: String
) {

}

@Composable
fun BottomBar(options: List<BottomOption>,defaultSelection: Int, onOptionClick: (option: BottomOption) -> Unit) {
    var selectedOptionId by remember {
        mutableIntStateOf(defaultSelection)
    }
    Row(
        Modifier
            .defaultMinSize(150.dp)
            .fillMaxWidth()
            .background(color = Color.Black),
        horizontalArrangement = Arrangement.Absolute.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {

        options.forEach { option ->
            Icon(
                painter = painterResource(id = option.icon),
                tint = if (option.id == selectedOptionId) Color.White else Color.Gray,
                contentDescription = option.name,
                modifier = Modifier
                    .padding(20.dp)
                    .size(homeScreenIconSize)
                    .clickable(
                        onClick = {
                            selectedOptionId = option.id
                            onOptionClick(option)
                        }, enabled = selectedOptionId != option.id
                    )
            )
        }
    }
}

@Composable
fun MovieItem(modifier: Modifier = Modifier, model: MovieModel, onMovieItemClick: (item: MovieModel)-> Unit) {
    Box(
        modifier = modifier
            .padding(start = 10.dp, end = 5.dp, top = 10.dp)
            .background(color = Color.DarkGray, shape = RoundedCornerShape(2))
            .clickable { onMovieItemClick(model) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            SubcomposeAsyncImage(
                model = "http://image.tmdb.org/t/p/w500/${model.posterPath}",
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(2f / 3f),
                loading = {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(10.dp)
                    )
                }
            )
            Column() {
                Text(
                    text = model.title,
                    Modifier.padding(horizontal = 5.dp, 2.dp),
                    minLines = 2,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(
                        color = Color.White,
                        fontFamily = poppinsFont,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 15.sp,
                    )
                )
            }
        }

        Surface(
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(10.dp, 10.dp),
            color = Color.Gray,
            shape = RoundedCornerShape(100)
        ) {
            Text(
                text = String.format("%.1f", model.voteAvg),
                Modifier.padding(horizontal = 7.dp),
                style = TextStyle(
                    color = Color.White,
                    fontFamily = poppinsFont,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp
                )
            )
        }


    }
}


@Composable
fun DummyScreen(text: String) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.Black)
    ){
        Text(
            text = text,
            style = TextStyle(
                color = Color.White,
                fontFamily = poppinsFont,
                fontWeight = FontWeight.Medium,
                fontSize = 12.sp,
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.Center)

        )
    }

}