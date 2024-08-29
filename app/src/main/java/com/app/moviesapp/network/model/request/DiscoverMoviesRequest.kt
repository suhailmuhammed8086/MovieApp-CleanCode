package com.app.moviesapp.network.model.request

data class DiscoverMoviesRequest(
    val genreIds: List<Int> = emptyList()
)