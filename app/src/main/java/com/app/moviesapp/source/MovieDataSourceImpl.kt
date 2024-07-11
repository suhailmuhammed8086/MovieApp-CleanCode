package com.app.moviesapp.source

import com.app.moviesapp.network.service.MoviesApiService
import javax.inject.Inject

class MovieDataSourceImpl @Inject constructor(
    private val movieApiService: MoviesApiService
) : MovieDataSource {
}