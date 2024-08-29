package com.app.moviesapp.ui.screens.movie.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.app.moviesapp.network.model.response.movies.MoviesListResponse
import com.app.moviesapp.states.ResponseState
import com.app.moviesapp.ui.Screens
import com.app.moviesapp.ui.composes.BoxWrapper
import com.app.moviesapp.ui.composes.ErrorText
import com.app.moviesapp.ui.composes.GenreCompose
import com.app.moviesapp.ui.composes.HorizontalListItemCompose
import com.app.moviesapp.ui.screens.movie.list.MovieListViewModel
import com.app.moviesapp.ui.theme.Black
import com.app.moviesapp.ui.theme.Gold
import com.app.moviesapp.ui.theme.Grey
import com.app.moviesapp.ui.theme.h2Title
import com.app.moviesapp.ui.theme.h3Title
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
            .background(Black)
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
                                        .addArg(ArgKeys.GENRE_ID, genere.id)
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

@Composable
fun MovieListTileCompose(
    modifier: Modifier = Modifier,
    tileTitle: String,
    state: ResponseState<MoviesListResponse>,
    onViewAllClick: () -> Unit = {},
    onMovieItemClick: (movieId: Long) -> Unit,
    onRetry: () -> Unit
) {
    Column(
        modifier
            .fillMaxWidth()

    ) {
        VSpace(space = 10.dp)
        Row(
            modifier
                .fillMaxWidth()
                .padding(start = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // TileTitle
            Text(
                text = tileTitle,
                style = h2Title
                    .copy(color = Gold)
            )

            // ViewMore button
            TextButton(onClick = onViewAllClick) {
                Text(
                    text = "View more",
                    style = h3Title
                        .copy(
                            color = Grey,
                            fontWeight = FontWeight.Medium
                        )
                )
            }
        }

        ResponseState.HandleComposeState(
            responseState = state,
            onLoading = {
                Box(
                    modifier =
                    Modifier
                        .height(200.dp)
                        .fillMaxWidth()
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.Center),
                        color = Color.White,
                    )
                }
            },
            onSuccess = { response ->
                val movieList = response?.results ?: emptyList()
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    items(movieList.size) {
                        with(movieList[it]) {
                            HorizontalListItemCompose(
                                modifier = Modifier
                                    .padding(start = if (it == 0) 10.dp else 0.dp),
                                uniqueId = id,
                                title = this.title,
                                imagePath = this.posterPath,
                                rating = this.voteAvg,
                                onItemClick = onMovieItemClick
                            )
                        }

                    }
                }
            },
            onFailed = { error, errorCode ->
                Box(
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth()
                ) {
                    ErrorText(
                        modifier = Modifier
                            .align(Alignment.Center),
                        errorText = error,
                        onRetry = onRetry
                    )
                }
            }
        )
        VSpace(space = 10.dp)
    }

}
