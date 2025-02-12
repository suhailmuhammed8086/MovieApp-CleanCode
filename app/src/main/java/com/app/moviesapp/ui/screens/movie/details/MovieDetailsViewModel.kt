package com.app.moviesapp.ui.screens.movie.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.moviesapp.network.model.response.CreditDetailsResponse
import com.app.moviesapp.network.model.response.movies.MovieDetailsResponse
import com.app.moviesapp.network.model.response.movies.MovieImagesResponse
import com.app.moviesapp.network.model.response.movies.MoviesListResponse
import com.app.moviesapp.repository.movie.MovieRepository
import com.app.moviesapp.states.ResponseState
import com.app.moviesapp.tools.OperationsStateHandler
import com.app.moviesapp.utils.constants.ArgKeys
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    var movieId: Long = savedStateHandle.get<Long>(ArgKeys.MOVIE_ID) ?: -1
        private set

    private val movieDetailsApiCall =
        OperationsStateHandler(viewModelScope) { responseState ->
            _movieDetailsScreenState.update { it.copy(movieDetailsResponse = responseState) }
        }
    private val movieImagesApiCall =
        OperationsStateHandler(viewModelScope) { responseState ->
            _movieDetailsScreenState.update { it.copy(movieImagesResponse = responseState) }
        }
    private val similarMoviesApiCall =
        OperationsStateHandler(viewModelScope) { responseState ->
            _movieDetailsScreenState.update { it.copy(similarMoviesResponse = responseState) }
        }
    private val recommendedMoviesApiCall =
        OperationsStateHandler(viewModelScope) { responseState ->
            _movieDetailsScreenState.update { it.copy(recommendedMoviesResponse = responseState) }
        }
    private val movieCreditsApiCall =
        OperationsStateHandler(viewModelScope) { responseState ->
            _movieDetailsScreenState.update { it.copy(movieCreditsResponse = responseState) }
        }

    private val _movieDetailsScreenState = MutableStateFlow(MovieDetailsScreenState())
    val movieDetailsScreenState = _movieDetailsScreenState.asStateFlow()

    init {
        loadMovieDetails(movieId)
    }

    fun onEvent(event: MovieDetailsUiEvents){
        when (event) {
            MovieDetailsUiEvents.RefreshDetailsApi -> movieDetailsApiCall.retry()
            MovieDetailsUiEvents.RefreshImagesApi -> movieImagesApiCall.retry()
            MovieDetailsUiEvents.RefreshSimilarMoviesApi -> similarMoviesApiCall.retry()
            MovieDetailsUiEvents.RefreshRecommendedMoviesApi -> recommendedMoviesApiCall.retry()
        }
    }


    private fun loadMovieDetails(movieId: Long) {
        viewModelScope.launch (Dispatchers.IO){
            // Calling API Sequentially
            movieDetailsApiCall.loadSuspend { movieRepository.getMovieDetails(movieId) }
            movieImagesApiCall.loadSuspend { movieRepository.getMovieImages(movieId) }
            similarMoviesApiCall.loadSuspend { movieRepository.getSimilarMovies(movieId) }
            recommendedMoviesApiCall.loadSuspend { movieRepository.getRecommendedMovies(movieId) }
            movieCreditsApiCall.loadSuspend { movieRepository.getMovieCredits(movieId) }
        }

    }

    data class MovieDetailsScreenState(
        val movieDetailsResponse: ResponseState<MovieDetailsResponse> = ResponseState.Idle,
        val movieImagesResponse: ResponseState<MovieImagesResponse> = ResponseState.Idle,
        val similarMoviesResponse: ResponseState<MoviesListResponse> = ResponseState.Idle,
        val recommendedMoviesResponse: ResponseState<MoviesListResponse> = ResponseState.Idle,
        val movieCreditsResponse : ResponseState<CreditDetailsResponse> = ResponseState.Idle
    )

}