package com.app.moviesapp.ui.activity.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.moviesapp.network.model.response.DiscoverMoviesListResponse
import com.app.moviesapp.repository.MovieRepository
import com.app.moviesapp.tools.OperationsStateHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository
): ViewModel() {

    val discoverMovieResponse = OperationsStateHandler<DiscoverMoviesListResponse>(viewModelScope)

    fun getDiscoverMovies() {
        discoverMovieResponse.load {
            movieRepository.getDiscoverMoviesList()
        }
    }
}