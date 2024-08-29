package com.app.moviesapp.network.model.response.tv_shows

import com.google.gson.annotations.SerializedName

data class TvShowListResponse(
    val page: Int,
    val results: List<TvShowModel>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int,
)


data class TvShowModel(
    val id: Long,
    val name: String,
    val overview: String,
    @SerializedName("vote_average")
    val voteAvg: Float,
    @SerializedName("vote_count")
    val voteCount: Int,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("backdrop_path")
    val backDropPath: String
)
