package com.app.moviesapp.ui.composes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.app.moviesapp.network.model.response.movies.MoviesListResponse
import com.app.moviesapp.states.ResponseState
import com.app.moviesapp.ui.theme.DarkBlue
import com.app.moviesapp.ui.theme.Gold
import com.app.moviesapp.ui.theme.Grey
import com.app.moviesapp.ui.theme.White
import com.app.moviesapp.ui.theme.h2Title
import com.app.moviesapp.ui.theme.h3Title
import com.app.moviesapp.ui.theme.h5Title
import com.app.moviesapp.ui.utils.VSpace

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
                    .copy(color = White)
            )

            // ViewMore button
            TextButton(onClick = onViewAllClick) {
                Text(
                    text = "View more",
                    style = h5Title
                        .copy(
                            color = DarkBlue,
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
