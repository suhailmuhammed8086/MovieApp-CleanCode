package com.app.moviesapp.ui.screens.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.Coil
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.app.moviesapp.R
import com.app.moviesapp.network.model.response.DiscoverMovieModel
import com.app.moviesapp.states.ResponseState
import com.app.moviesapp.ui.activity.MainViewModel
import com.app.moviesapp.ui.screens.home.BottomOption.Companion.OPTION_FAVS
import com.app.moviesapp.ui.screens.home.BottomOption.Companion.OPTION_MOVIES
import com.app.moviesapp.ui.screens.home.BottomOption.Companion.OPTION_TV_SHOWS
import com.app.moviesapp.ui.theme.poppinsFont
import com.app.moviesapp.ui.theme.topBarTitleStyle
import com.app.moviesapp.utils.log


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getDiscoverMovies()
    }
    Scaffold(
        modifier
            .fillMaxSize()
            .background(color = Color.Black),
        topBar = {
            TopBar(
                title = LocalContext.current.getString(R.string.app_name),
                onSearchClick = {

                },
                onSettingsClick = {

                }
            )
        },
        content = {
            HomeContent(it, viewModel)
        },
        bottomBar = {
            val options = listOf(
                BottomOption(OPTION_MOVIES, "Movies", R.drawable.ic_movies),
                BottomOption(OPTION_TV_SHOWS, "Tv Shows", R.drawable.ic_tv_shows),
                BottomOption(OPTION_FAVS, "Favourites", R.drawable.ic_favourite)
            )
            BottomBar(
                options = options,
                onOptionClick = { option ->
                    when (option.id) {
                        OPTION_MOVIES -> {

                        }

                        OPTION_TV_SHOWS -> {

                        }

                        OPTION_FAVS -> {

                        }
                    }
                }
            )
        }
    )

}

@Preview
@Composable
private fun Preview() {
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
                .size(25.dp)
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
                .size(25.dp)
                .clickable(onClick = onSettingsClick)
        )
    }
}


@Composable
fun HomeContent(paddingValues: PaddingValues, viewModel: MainViewModel) {
    val state = viewModel.movieListResponseState.collectAsState().value
    Column(
        Modifier
            .fillMaxSize()
            .padding(
                top = paddingValues.calculateTopPadding(),
                bottom = paddingValues.calculateBottomPadding()
            )
            .background(color = Color.Black)
//            .verticalScroll(rememberScrollState())
    ) {
        when (state) {
            ResponseState.Cancelled -> {

            }

            is ResponseState.Failed -> {

            }

            ResponseState.Idle -> {

            }

            ResponseState.Loading -> {
                Box(Modifier.fillMaxSize()) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }

            is ResponseState.Success -> {
                state?.response?.results?.let { items ->
                    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                        items(items.size) {
                            MovieItem(model = items[it])
                        }
                    }
                }
            }

            is ResponseState.ValidationError -> {

            }
        }
    }
}

data class BottomOption(
    val id: Int,
    val name: String,
    val icon: Int
) {
    companion object {
        const val OPTION_MOVIES = 1
        const val OPTION_TV_SHOWS = 2
        const val OPTION_FAVS = 3
    }
}

@Composable
fun BottomBar(options: List<BottomOption>, onOptionClick: (option: BottomOption) -> Unit) {
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
                tint = Color.White,
                contentDescription = option.name,
                modifier = Modifier
                    .padding(20.dp)
                    .size(25.dp)
                    .clickable(onClick = { onOptionClick(option) })
            )
        }
    }
}

@Composable
fun MovieRow(model: DiscoverMovieModel) {
    Row(
        Modifier
            .fillMaxWidth()
    ) {
        MovieItem(
            Modifier
                .weight(.5f)
                .padding(start = 10.dp, end = 5.dp, top = 10.dp), model
        )
        MovieItem(
            Modifier
                .weight(.5f)
                .padding(start = 5.dp, end = 10.dp, top = 10.dp), model
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MovieItem(modifier: Modifier = Modifier, model: DiscoverMovieModel) {
    var isPosterImage by remember {
        mutableStateOf(true)
    }
    Box(
        modifier = modifier
            .aspectRatio(2f / 4f)
            .padding(start = 10.dp, end = 5.dp, top = 10.dp)
            .background(color = Color.DarkGray, shape = RoundedCornerShape(2))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            SubcomposeAsyncImage(
                model = "http://image.tmdb.org/t/p/w500/${
                    if (isPosterImage) {
                        model.posterPath
                    } else {
                        model.backDropPath
                    }
                }",
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(.75f)
                    .combinedClickable(
                        onClick = {},
                        onLongClick = {
                            isPosterImage = !isPosterImage
                        }
                    ),
                loading = {
                    CircularProgressIndicator()
                }
            )
            Column() {
                Text(
                    text = model.title,
                    Modifier.padding(horizontal = 5.dp, 2.dp),
                    style = TextStyle(
                        color = Color.White,
                        fontFamily = poppinsFont,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 15.sp
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
fun SearchBar() {
    var text: String by remember { mutableStateOf("") }
    var showHint: Boolean by remember { mutableStateOf(true) }

    val textStyle = TextStyle(
        color = Color.Black,
        fontFamily = poppinsFont,
        fontWeight = FontWeight.Normal,
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(5.dp))
            .padding(start = 15.dp, end = 10.dp)
            .defaultMinSize(minHeight = 52.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        BasicTextField(
            value = text,
            onValueChange = {
                text = it
            },
            textStyle = textStyle,
            modifier = Modifier
                .onFocusChanged {
                    showHint = !it.hasFocus
                }
                .fillMaxWidth()


        )

        if (showHint && text.isEmpty()) {
            Text(
                text = "Hint Text",
                Modifier.align(Alignment.CenterStart),
                style = textStyle.copy(
                    color = Color.Gray
                ),
            )
        }
    }

}