package com.app.moviesapp.ui.screens.movie.list

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.moviesapp.network.model.request.DiscoverMoviesRequest
import com.app.moviesapp.network.model.response.movies.MoviesListResponse
import com.app.moviesapp.repository.movie.MovieRepository
import com.app.moviesapp.states.ResponseState
import com.app.moviesapp.tools.OperationsStateHandler
import com.app.moviesapp.ui.screens.movie.list.MovieListViewModel.PageType.*
import com.app.moviesapp.utils.constants.ArgKeys
import com.app.moviesapp.utils.log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private var pageType: PageType = PageType.default
    private var genreId: Int = -1

    val movieListApiCall = OperationsStateHandler(viewModelScope){ responseState->
        _movieListScreeState.update { it.copy(movieListApiState = responseState) }
    }

    private val _movieListScreeState = MutableStateFlow(MovieListScreenState())
    val movieListScreeState = _movieListScreeState.asStateFlow()


    init {
        pageType = PageType.parse(savedStateHandle.get<String>(ArgKeys.MOVIE_PAGE_TYPE))
        when (pageType) {
            POPULAR -> {
                movieListApiCall.load(movieRepository::getPopularMovies)
            }
            GENRE_WISE ->{
                genreId = savedStateHandle.get<Int>(ArgKeys.GENRE_ID) ?: -1
                movieListApiCall.load {
                    movieRepository.getDiscoverMoviesList(DiscoverMoviesRequest(
                        listOf(genreId)
                    ))
                }
            }
            NOW_PLAYING -> {
                movieListApiCall.load(movieRepository::getNowPlayingMovies)
            }
            TOP_RATED -> {
                movieListApiCall.load(movieRepository::getTopRatedMovies)
            }
            UP_COMING -> {
                movieListApiCall.load(movieRepository::getUpComingMovies)
            }
        }
    }

    data class MovieListScreenState(
        val movieListApiState: ResponseState<MoviesListResponse> = ResponseState.Idle
    )

    enum class PageType{
        POPULAR,
        GENRE_WISE,
        NOW_PLAYING,
        TOP_RATED,
        UP_COMING;
        companion object{
            val default = POPULAR
            fun parse(name: String?): PageType {
                return when (name) {
                    POPULAR.name -> POPULAR
                    GENRE_WISE.name -> GENRE_WISE
                    NOW_PLAYING.name -> NOW_PLAYING
                    TOP_RATED.name -> TOP_RATED
                    UP_COMING.name -> UP_COMING
                    else -> POPULAR
                }
            }
        }
    }
}