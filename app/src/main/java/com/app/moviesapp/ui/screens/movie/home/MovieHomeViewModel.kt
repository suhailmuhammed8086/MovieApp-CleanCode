package com.app.moviesapp.ui.screens.movie.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.moviesapp.network.model.response.GenreListResponse
import com.app.moviesapp.network.model.response.movies.MoviesListResponse
import com.app.moviesapp.repository.movie.MovieRepository
import com.app.moviesapp.states.ResponseState
import com.app.moviesapp.tools.OperationsStateHandler
import com.app.moviesapp.utils.log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieHomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository
): ViewModel() {


    val genreApiCall = OperationsStateHandler(viewModelScope){responseState->
        _movieHomeScreenState.update { it.copy(genreApiState = responseState) }
    }
    val nowPlayingMoviesApiCall = OperationsStateHandler(viewModelScope) { responseState ->
        responseState.log("responseState1")
        _movieHomeScreenState.update { it.copy(nowPlayingMoviesApiState = responseState) }
    }
    val popularMoviesApiCall = OperationsStateHandler(viewModelScope) { responseState ->
        responseState.log("responseState2")
        _movieHomeScreenState.update { it.copy(popularMoviesApiState = responseState) }
    }
    val topRatedMoviesApiCall = OperationsStateHandler(viewModelScope) { responseState ->
        responseState.log("responseState3")
        _movieHomeScreenState.update { it.copy(topRatedMoviesApiState = responseState) }
    }
    val upComingMoviesApiCall = OperationsStateHandler(viewModelScope) { responseState ->
        responseState.log("responseState4")
        _movieHomeScreenState.update { it.copy(upComingMoviesApiState = responseState) }
    }

    private val _movieHomeScreenState = MutableStateFlow(MovieHomeState())
    val movieHomeScreenState = _movieHomeScreenState.asStateFlow()



    init {
        loadData()
    }

    fun loadData() {
        viewModelScope.launch (Dispatchers.IO){
            genreApiCall.loadSuspend(movieRepository::getMovieGenreList)
            nowPlayingMoviesApiCall.loadSuspend (movieRepository::getNowPlayingMovies)
            popularMoviesApiCall.loadSuspend (movieRepository::getPopularMovies)
            topRatedMoviesApiCall.loadSuspend (movieRepository::getTopRatedMovies)
            upComingMoviesApiCall.loadSuspend (movieRepository::getUpComingMovies)
        }

    }

    data class MovieHomeState(
        var genreApiState: ResponseState<GenreListResponse> = ResponseState.Idle,
        var nowPlayingMoviesApiState: ResponseState<MoviesListResponse> = ResponseState.Idle,
        var popularMoviesApiState: ResponseState<MoviesListResponse> = ResponseState.Idle,
        var topRatedMoviesApiState: ResponseState<MoviesListResponse> = ResponseState.Idle,
        var upComingMoviesApiState: ResponseState<MoviesListResponse> = ResponseState.Idle
    )
}