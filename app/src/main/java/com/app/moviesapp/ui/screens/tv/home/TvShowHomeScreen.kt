package com.app.moviesapp.ui.screens.tv.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import com.app.moviesapp.network.model.response.tv_shows.TvShowModel
import com.app.moviesapp.states.ResponseState
import com.app.moviesapp.ui.Screens
import com.app.moviesapp.ui.screens.home.ErrorText
import com.app.moviesapp.ui.theme.poppinsFont


@Composable
fun TvShowHomeScreen(
    paddingValues: PaddingValues,
    viewModel: TvShowsHomeViewModel = hiltViewModel(),
    navController: NavController
) {
    LaunchedEffect(Unit) {
        viewModel.getDiscoverMovies()
    }

    val state by viewModel.tvShowListResponseState.collectAsState()
    var parentWidth by remember {
        mutableIntStateOf(0)
    }
    Column(
        Modifier
            .fillMaxSize()
            .padding(
                top = paddingValues.calculateTopPadding(),
                bottom = paddingValues.calculateBottomPadding()
            )
            .background(color = Color.Black)
            .onSizeChanged {
                parentWidth = it.width
            }
//            .verticalScroll(rememberScrollState())
    ) {
        ResponseState.HandleComposeState(state,
            onLoading = {
                Box(Modifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = Color.White
                    )
                }
            },

            onSuccess = { response->
                val singleCellWidth = 500
                val count = (parentWidth/singleCellWidth).coerceAtLeast(2)
                response?.results?.let { items ->
                    LazyVerticalGrid(columns = GridCells.Fixed(count)) {
                        items(items.size) {
                            TvShowItem(model = items[it]) {
                                navController.navigate(Screens.Detail.route)
                            }
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
                        onRetry = {
                            viewModel.discoverTvShowApiHandler.retry()
                        }
                    )
                }
            }
        )
    }
}

@Composable
fun TvShowItem(modifier: Modifier = Modifier, model: TvShowModel, onTvShowItemClick: (item: TvShowModel)-> Unit) {
    Box(
        modifier = modifier
            .padding(start = 10.dp, end = 5.dp, top = 10.dp)
            .background(color = Color.DarkGray, shape = RoundedCornerShape(2))
            .clickable { onTvShowItemClick(model) }
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
                    CircularProgressIndicator()
                }
            )
            Column() {
                Text(
                    text = model.name,
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

