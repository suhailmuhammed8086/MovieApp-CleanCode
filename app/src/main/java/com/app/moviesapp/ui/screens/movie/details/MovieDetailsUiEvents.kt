package com.app.moviesapp.ui.screens.movie.details

sealed interface MovieDetailsUiEvents {
    data object RefreshDetailsApi: MovieDetailsUiEvents
    data object RefreshImagesApi: MovieDetailsUiEvents
    data object RefreshSimilarMoviesApi: MovieDetailsUiEvents
    data object RefreshRecommendedMoviesApi: MovieDetailsUiEvents

}