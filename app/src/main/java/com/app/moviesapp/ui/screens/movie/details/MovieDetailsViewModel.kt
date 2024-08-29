package com.app.moviesapp.ui.screens.movie.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.moviesapp.network.model.response.movies.MovieDetailsResponse
import com.app.moviesapp.network.model.response.movies.MovieImagesResponse
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
    private var movieId: Long = savedStateHandle.get<Long>(ArgKeys.MOVIE_ID) ?: -1

    val movieDetailsApiCall =
        OperationsStateHandler<MovieDetailsResponse>(viewModelScope) { responseState ->
            _movieDetailsScreenState.update { it.copy(movieDetailsResponse = responseState) }
        }
    val movieImagesApiCall =
        OperationsStateHandler<MovieImagesResponse>(viewModelScope) { responseState ->
            _movieDetailsScreenState.update { it.copy(movieImagesResponse = responseState) }
        }

    private val _movieDetailsScreenState = MutableStateFlow(MovieDetailsScreenState())
    val movieDetailsScreenState = _movieDetailsScreenState.asStateFlow()

    init {
        loadMovieDetails(movieId)
    }


    private fun loadMovieDetails(movieId: Long) {
        viewModelScope.launch (Dispatchers.IO){
            // Calling API Sequentially
            movieDetailsApiCall.loadSuspend { movieRepository.getMovieDetails(movieId) }
            movieImagesApiCall.loadSuspend { movieRepository.getMovieImages(movieId) }
        }

    }

    data class MovieDetailsScreenState(
        val movieDetailsResponse: ResponseState<MovieDetailsResponse> = ResponseState.Idle,
        val movieImagesResponse: ResponseState<MovieImagesResponse> = ResponseState.Idle
    )

}