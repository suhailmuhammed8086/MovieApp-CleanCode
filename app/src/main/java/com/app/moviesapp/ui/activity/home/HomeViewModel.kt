package com.app.moviesapp.ui.activity.home

import androidx.lifecycle.ViewModel
import com.app.moviesapp.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    movieRepository: MovieRepository
): ViewModel() {

}