package com.app.moviesapp.ui.screens.movie.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.app.moviesapp.states.ResponseState
import com.app.moviesapp.ui.Screens
import com.app.moviesapp.ui.composes.BoxWrapper
import com.app.moviesapp.ui.composes.GenreCompose
import com.app.moviesapp.ui.composes.MovieListTileCompose
import com.app.moviesapp.ui.screens.movie.list.MovieListViewModel
import com.app.moviesapp.ui.theme.Black
import com.app.moviesapp.ui.utils.VSpace
import com.app.moviesapp.utils.withArgs
import com.app.moviesapp.utils.constants.ArgKeys


@Composable
fun MovieHomeScreen(
    paddingValues: PaddingValues,
    viewModel: MovieHomeViewModel = hiltViewModel(),
    navController: NavController
) {

    fun openMovieDetailScreen(movieId: Long) {
        navController.navigate(
            Screens.Detail.withArgs()
                .addArg(ArgKeys.MOVIE_ID, movieId)
                .route()
        )
    }

    fun openMovieListScreen(type: MovieListViewModel.PageType, title: String){
        navController.navigate(Screens.MovieList.withArgs()
            .addArg(ArgKeys.MOVIE_PAGE_TYPE, type)
            .addArg(ArgKeys.PAGE_TITLE, title)
            .route()
        )
    }

    val state by viewModel.movieHomeScreenState.collectAsState()
    var parentWidth by remember { mutableIntStateOf(0) }


    Column(
        Modifier
            .fillMaxSize()
            .padding(
                top = paddingValues.calculateTopPadding(),
                bottom = paddingValues.calculateBottomPadding()
            )
            .onSizeChanged {
                parentWidth = it.width
            }
            .verticalScroll(rememberScrollState())
    ) {
        VSpace(space = 10.dp)
        ResponseState.HandleComposeState(
            responseState = state.genreApiState,
            onSuccess = { response ->
                if (response != null) {
                    LazyRow {
                        items(response.genres) { genere ->
                            GenreCompose(
                                modifier = Modifier.padding(start = 10.dp),
                                genreId = genere.id, name = genere.name
                            ) {
                                // on Genre Item click
                                navController.navigate(
                                    Screens.MovieList.withArgs()
                                        .addArg(ArgKeys.MOVIE_PAGE_TYPE, MovieListViewModel.PageType.GENRE_WISE)
                                        .addArg(ArgKeys.PAGE_TITLE,genere.name)
                                        .addArg(ArgKeys.CONTENT_ID, genere.id)
                                        .route()
                                )
                            }
                        }
                    }
                }

            }
        )
        VSpace(space = 10.dp)
        // Now playing
        BoxWrapper(
            insetPadding = 0.dp
        ) {
            MovieListTileCompose(
                tileTitle = "Now Playing",
                state = state.nowPlayingMoviesApiState,
                onMovieItemClick = ::openMovieDetailScreen,
                onViewAllClick = {
                    openMovieListScreen(MovieListViewModel.PageType.NOW_PLAYING, "Now Playing")

                },
                onRetry = viewModel.nowPlayingMoviesApiCall::retry

            )
        }
        VSpace(space = 10.dp)

        // Popular Movies
        BoxWrapper(
            insetPadding = 0.dp
        ) {
            MovieListTileCompose(
                tileTitle = "Popular",
                state = state.popularMoviesApiState,
                onMovieItemClick = ::openMovieDetailScreen,
                onViewAllClick = {
                    openMovieListScreen(MovieListViewModel.PageType.POPULAR, "Popular")
                },
                onRetry = viewModel.popularMoviesApiCall::retry
            )
        }
        VSpace(space = 10.dp)

        // Top rated
        BoxWrapper(
            insetPadding = 0.dp
        ) {
            MovieListTileCompose(
                tileTitle = "Top Rated",
                state = state.topRatedMoviesApiState,
                onMovieItemClick = ::openMovieDetailScreen,
                onViewAllClick = {
                    openMovieListScreen(MovieListViewModel.PageType.TOP_RATED, "Top Rated")

                },
                onRetry = viewModel.topRatedMoviesApiCall::retry
            )
        }
        VSpace(space = 10.dp)
        // Up coming movies
        BoxWrapper(
            insetPadding = 0.dp
        ) {
            MovieListTileCompose(
                tileTitle = "Up coming",
                state = state.upComingMoviesApiState,
                onMovieItemClick = ::openMovieDetailScreen,
                onViewAllClick = {
                    openMovieListScreen(MovieListViewModel.PageType.UP_COMING, "Up coming")

                },
                onRetry = viewModel.upComingMoviesApiCall::retry
            )
        }
        VSpace(space = 10.dp)
    }
}

