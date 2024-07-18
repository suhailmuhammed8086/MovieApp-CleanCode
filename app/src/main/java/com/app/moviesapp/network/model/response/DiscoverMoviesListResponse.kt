package com.app.moviesapp.network.model.response

import com.google.gson.annotations.SerializedName

data class DiscoverMoviesListResponse(
    val page: Int,
    val results: List<DiscoverMovieModel>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int,
)


data class DiscoverMovieModel(
    val id: Int,
    val title: String,
    val overview: String,
    @SerializedName("vote_average")
    val voteAvg: Float,
    @SerializedName("vote_count")
    val voteCount: Int,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("backdrop_path")
    val backDropPath: String
)
