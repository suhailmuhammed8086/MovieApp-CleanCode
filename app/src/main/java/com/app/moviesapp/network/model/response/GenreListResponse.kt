package com.app.moviesapp.network.model.response

class GenreListResponse(
    val genres: List<GenreModel>
)

data class GenreModel (
    val id: Long,
    val name: String
)