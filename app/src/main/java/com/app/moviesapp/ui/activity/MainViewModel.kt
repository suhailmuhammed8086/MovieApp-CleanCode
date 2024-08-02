package com.app.moviesapp.ui.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.moviesapp.network.model.response.DiscoverMoviesListResponse
import com.app.moviesapp.repository.MovieRepository
import com.app.moviesapp.states.ResponseState
import com.app.moviesapp.tools.OperationsStateHandler
import com.app.moviesapp.tools.OperationsStateHandler2
import com.app.moviesapp.utils.log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val movieRepository: MovieRepository
): ViewModel() {

    val discoverMovieResponse = OperationsStateHandler<DiscoverMoviesListResponse>(viewModelScope)
    val discoverMovieResponse2 = OperationsStateHandler2(viewModelScope){
//        homeState.emit(it)
        it.log("vmState")
        _movieListResponseState.value = it
    }
    val _movieListResponseState = MutableStateFlow<ResponseState<DiscoverMoviesListResponse>>(ResponseState.Idle)
    val movieListResponseState = _movieListResponseState.asStateFlow()
//    val homeState = MutableStateFlow<ResponseState<DiscoverMoviesListResponse>>(ResponseState.Idle)

    fun getDiscoverMovies() {
        discoverMovieResponse2.load {
            delay(2000)
            movieRepository.getDiscoverMoviesList()
        }
    }


    data class HomeState(
        val responseState: ResponseState<DiscoverMoviesListResponse>
    )
}