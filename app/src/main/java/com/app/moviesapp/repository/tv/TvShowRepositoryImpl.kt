package com.app.moviesapp.repository.tv

import com.app.moviesapp.network.model.response.GenreListResponse
import com.app.moviesapp.network.model.response.tv_shows.TvShowDetailsResponse
import com.app.moviesapp.network.model.response.tv_shows.TvShowListResponse
import com.app.moviesapp.source.tv_shows.TvShowsDataSource
import com.app.moviesapp.states.ResponseState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TvShowRepositoryImpl @Inject constructor(
    private val tvShowDataSource: TvShowsDataSource
) : TvShowRepository {
    override suspend fun getDiscoverTvShowList(): ResponseState<TvShowListResponse> {
        return withContext(Dispatchers.IO) {
            return@withContext tvShowDataSource.getDiscoverTvShowList()
        }
    }
    override suspend fun getTvShowDetails(tvShowId: Int): ResponseState<TvShowDetailsResponse> {
        return withContext(Dispatchers.IO) {
            return@withContext tvShowDataSource.getTvShowDetail(tvShowId)
        }
    }

    override suspend fun getTvShowsGenreList(): ResponseState<GenreListResponse> {
        return withContext(Dispatchers.IO) {
            return@withContext tvShowDataSource.getTvShowsGenreList()
        }
    }
}