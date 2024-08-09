package com.app.moviesapp.ui.screens.movie.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.moviesapp.network.model.response.movies.MoviesListResponse
import com.app.moviesapp.repository.movie.MovieRepository
import com.app.moviesapp.states.ResponseState
import com.app.moviesapp.tools.OperationsStateHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MovieHomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository
): ViewModel() {

    val discoverMovieResponse2 = OperationsStateHandler(viewModelScope){
        _movieListResponseState.value = it
    }
    val _movieListResponseState = MutableStateFlow<ResponseState<MoviesListResponse>>(ResponseState.Idle)
    val movieListResponseState = _movieListResponseState.asStateFlow()

    fun getDiscoverMovies() {
        discoverMovieResponse2.load {
            movieRepository.getDiscoverMoviesList()
        }
    }


    data class HomeState(
        val responseState: ResponseState<MoviesListResponse>
    )
}