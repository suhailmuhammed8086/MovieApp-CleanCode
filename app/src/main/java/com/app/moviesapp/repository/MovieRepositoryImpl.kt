package com.app.moviesapp.repository

import com.app.moviesapp.source.MovieDataSource
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieDataSource: MovieDataSource
) : MovieRepository {
}