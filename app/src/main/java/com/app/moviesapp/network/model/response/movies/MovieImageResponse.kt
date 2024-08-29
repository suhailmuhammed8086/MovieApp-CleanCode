package com.app.moviesapp.network.model.response.movies

import com.google.gson.annotations.SerializedName





data class MovieImagesResponse (
    val backdrops: List<ImageModel>,
    val id: Long,
    val logos: List<ImageModel>,
    val posters: List<ImageModel>
) {

}

data class ImageModel (
    @SerializedName("aspect_ratio")
    val aspectRatio: Double,

    val height: Long,

    @SerializedName("iso_639_1")
    val iso639_1: String? = null,

    @SerializedName("file_path")
    val filePath: String,

    @SerializedName("vote_average")
    val voteAverage: Double,

    @SerializedName("vote_count")
    val voteCount: Long,

    val width: Long
)