package com.app.moviesapp.ui.screens.tv.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.moviesapp.network.model.response.movies.MoviesListResponse
import com.app.moviesapp.network.model.response.tv_shows.TvShowListResponse
import com.app.moviesapp.repository.tv.TvShowRepository
import com.app.moviesapp.states.ResponseState
import com.app.moviesapp.tools.OperationsStateHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class TvShowsHomeViewModel @Inject constructor(
    private val tvShowRepository: TvShowRepository
): ViewModel() {

    val discoverTvShowApiHandler = OperationsStateHandler(viewModelScope){
        _tvShowListResponseState.value = it
    }
    val _tvShowListResponseState = MutableStateFlow<ResponseState<TvShowListResponse>>(ResponseState.Idle)
    val tvShowListResponseState = _tvShowListResponseState.asStateFlow()

    fun getDiscoverMovies() {
        discoverTvShowApiHandler.load {
            tvShowRepository.getDiscoverTvShowList()
        }
    }
}