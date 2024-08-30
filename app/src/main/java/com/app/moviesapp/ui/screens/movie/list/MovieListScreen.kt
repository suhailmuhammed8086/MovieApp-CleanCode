package com.app.moviesapp.ui.screens.movie.list

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.app.moviesapp.states.ResponseState
import com.app.moviesapp.ui.Screens
import com.app.moviesapp.ui.composes.ErrorText
import com.app.moviesapp.ui.composes.VerticalDetailedListItem
import com.app.moviesapp.ui.theme.Black
import com.app.moviesapp.ui.theme.h1Title
import com.app.moviesapp.ui.theme.homeScreenIconSize
import com.app.moviesapp.utils.constants.ArgKeys
import com.app.moviesapp.utils.withArgs


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MovieListScreen(
    title: String,
    navController: NavController,
    viewModel: MovieListViewModel = hiltViewModel()
) {

    val state by viewModel.movieListScreeState.collectAsState()
    Scaffold(
        topBar = {
            MovieListTopBar(
                title = title,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    ) {
        Content(
            paddingValues = it,
            state = state,
            onRetryClick = {
                viewModel.movieListApiCall.retry()
            },
            onMovieItemClick = { movieId->
                navController.navigate(
                    Screens.Detail.withArgs()
                        .addArg(ArgKeys.MOVIE_ID, movieId)
                        .route()
                )
            }
        )
    }
}

@Composable
fun Content(
    paddingValues: PaddingValues,
    state: MovieListViewModel.MovieListScreenState,
    onMovieItemClick: (movieId: Long) -> Unit,
    onRetryClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = paddingValues.calculateTopPadding())
            .background(Black)
    ) {
        ResponseState.HandleComposeState(state.movieListApiState,
            onLoading = {
                Box(Modifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = Color.White
                    )
                }
            },
            onSuccess = { response->
                if (response?.results != null) {
                    LazyColumn {
                        items(response.results){
                            VerticalDetailedListItem(
                                modifier = Modifier
                                    .padding(2.dp),
                                uniqueId = it.id,
                                title = it.title,
                                imagePath = it.posterPath,
                                backgroundImagePath = it.backDropPath,
                                rating = it.voteAvg,
                                overView = it.overview,
                                releaseDate = it.releaseDate,
                                onItemClick = onMovieItemClick
                            )
                        }
                    }
                }
            },
            onFailed = { error, errorCode ->
                Box(modifier = Modifier.fillMaxSize()) {
                    ErrorText(
                        modifier = Modifier
                            .align(Alignment.Center),
                        errorText = error,
                        onRetry = onRetryClick
                    )
                }
            }
        )
    }
}

@Composable
fun MovieListTopBar(
    title: String,
    onBackClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .defaultMinSize(minHeight = 70.dp)
            .fillMaxWidth()
            .background(color = Color.Black)
            .padding(horizontal = 15.dp)
    ) {
        Icon(
            Icons.AutoMirrored.Filled.KeyboardArrowLeft,
            tint = Color.White,
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.CenterStart)
                .size(homeScreenIconSize)
                .clickable(onClick = onBackClick)
        )
        Text(
            text = title,
            style = h1Title,
            modifier = Modifier
                .align(Alignment.Center),
            onTextLayout = {}
        )
    }
}