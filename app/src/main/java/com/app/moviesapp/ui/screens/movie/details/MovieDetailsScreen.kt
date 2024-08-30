package com.app.moviesapp.ui.screens.movie.details

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import com.app.moviesapp.R
import com.app.moviesapp.network.model.response.movies.GenreModel
import com.app.moviesapp.network.model.response.movies.MovieDetailsResponse
import com.app.moviesapp.network.model.response.movies.MovieImagesResponse
import com.app.moviesapp.states.ResponseState
import com.app.moviesapp.ui.Screens
import com.app.moviesapp.ui.composes.BoxWrapper
import com.app.moviesapp.ui.composes.ErrorText
import com.app.moviesapp.ui.composes.GenreCompose
import com.app.moviesapp.ui.screens.movie.list.MovieListViewModel
import com.app.moviesapp.ui.theme.Black
import com.app.moviesapp.ui.theme.DetailsScreenTopBarIconSize
import com.app.moviesapp.ui.theme.Gold
import com.app.moviesapp.ui.theme.GreyBlack
import com.app.moviesapp.ui.theme.ListItemBoxRatio
import com.app.moviesapp.ui.theme.TopBarMinHeight
import com.app.moviesapp.ui.theme.h1Title
import com.app.moviesapp.ui.theme.h3Title
import com.app.moviesapp.ui.theme.homeScreenIconSize
import com.app.moviesapp.ui.theme.mediumContent
import com.app.moviesapp.ui.theme.regularContent
import com.app.moviesapp.ui.utils.VSpace
import com.app.moviesapp.utils.ImageLoader
import com.app.moviesapp.utils.constants.ArgKeys
import com.app.moviesapp.utils.log
import com.app.moviesapp.utils.withArgs

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MovieDetailsScreen(
    viewModel: MovieDetailsViewModel = hiltViewModel(),
    navController: NavController
) {
    val context = LocalContext.current
    val state by viewModel.movieDetailsScreenState.collectAsState()
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black),
        topBar = {
            MovieDetailsTopBar(
                showWebIcon = !state.movieDetailsResponse.getSuccessResponse()?.homepage.isNullOrEmpty(),
                onBackClick = {
                    navController.popBackStack()
                },
                onWebViewClick = {
                    state.movieDetailsResponse.getSuccessResponse()?.homepage?.let { url->
                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.data = Uri.parse(url)
                        context.startActivity(intent)
                    }
                    // open webView content
                }
            )
        },
    ){
        Box(
            modifier = Modifier
                .background(Color.Black)
                .padding(top = TopBarMinHeight)
                .fillMaxSize()
        ) {

            ResponseState.HandleComposeState(
                responseState = state.movieDetailsResponse,
                onLoading = {
                    // Onscreen loader
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(25.dp)
                                .align(Alignment.Center),
                            color = Color.White
                        )
                    }
                },
                onSuccess = { response ->
                    if (response != null) {
                        ContentScreen(
                            movieDetails = response,
                            movieImages = state.movieImagesResponse,
                            onImageLoadFailed = viewModel.movieImagesApiCall::retry,
                            onGenreClick = { genre ->
                                navController.navigate(
                                    Screens.MovieList.withArgs()
                                        .addArg(ArgKeys.MOVIE_PAGE_TYPE, MovieListViewModel.PageType.GENRE_WISE)
                                        .addArg(ArgKeys.PAGE_TITLE, genre.name)
                                        .addArg(ArgKeys.GENRE_ID, genre.id)
                                        .route()
                                )
                            }
                        )
                    }
                },
                onFailed = { error, errorCode ->
                    // OnScreen Error
                    Box(
                        modifier =
                        Modifier.fillMaxSize()
                    ) {
                        ErrorText(
                            modifier = Modifier.align(Alignment.Center),
                            errorText = error,
                            onRetry = viewModel.movieDetailsApiCall::retry
                        )
                    }
                }
            )
        }
    }

}

@Composable
fun MovieDetailsTopBar(
    showWebIcon: Boolean,
    onBackClick: () -> Unit,
    onWebViewClick:  ()-> Unit
) {
    Row(
        modifier = Modifier
            .defaultMinSize(minHeight = TopBarMinHeight)
            .fillMaxWidth()
//            .background(color = GreyBlack.copy(alpha = 0.8f))
            .padding(horizontal = 15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Back button
        Icon(
            Icons.AutoMirrored.Filled.KeyboardArrowLeft,
            tint = Color.White,
            contentDescription = "",
            modifier = Modifier
                .size(DetailsScreenTopBarIconSize)
                .clickable(onClick = onBackClick)
        )
        // Web Icon
        if (showWebIcon) {
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                painter = painterResource(id = R.drawable.ic_web),
                tint = Color.White,
                contentDescription = "",
                modifier = Modifier
                    .size(DetailsScreenTopBarIconSize)
                    .clickable(onClick = onWebViewClick)
            )
        }
    }
}

@Composable
fun ContentScreen(
    movieDetails: MovieDetailsResponse,
    movieImages: ResponseState<MovieImagesResponse>,
    onImageLoadFailed: () -> Unit,
    onGenreClick: (genre: GenreModel) -> Unit
) {
    val contentHorizontalPadding = 15.dp
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Black)
    ) {
        //Banner Image
        SubcomposeAsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(ListItemBoxRatio),
            model = ImageLoader.getUrl(movieDetails.posterPath),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            onLoading = {

            }
        )
        // Main content container
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(Color.Black.copy(alpha = 0.8f))
        ) {
            VSpace(space = 20.dp)
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                // Title
                TitleSection(
                    modifier = Modifier.padding(horizontal = contentHorizontalPadding),
                    title = movieDetails.title?:"",
                    imagePath = movieDetails.posterPath,
                    rating = movieDetails.voteAverage.toFloat()
                )
                VSpace(space = 10.dp)
                // Genre
                LazyRow {
                    items(movieDetails.genres.size){
                        val genre = movieDetails.genres[it]
                        GenreCompose(
                            modifier = Modifier
                                .padding(
                                    start = if (it == 0) contentHorizontalPadding else 0.dp,
                                    end = 5.dp
                                ),
                            genreId = genre.id,
                            name = genre.name,
                            onClick = { onGenreClick(genre) }
                        )
                    }
                }
                VSpace(space = 10.dp)
                // Overview
                BoxWrapper(
                    modifier = Modifier
                        .padding(horizontal = contentHorizontalPadding)
                ) {
                    Text(
                        text = movieDetails.overview,
                        style = regularContent
                    )
                }

                // Images
                ResponseState.HandleComposeState(
                    responseState = movieImages,
                    onSuccess = { images->
                        VSpace(space = 15.dp)
                        Text(text = "Images", style = h3Title, modifier = Modifier.padding(start = contentHorizontalPadding))
                        VSpace(space = 10.dp)
                        if (images != null) {
                            // Posters
                            BoxWrapper(
                                modifier = Modifier
                                    .padding(contentHorizontalPadding)
                            ) {
                                LazyRow {
                                    items(images.posters.size){
                                        val image = images.posters[it]
                                        ImageItem(image.filePath, 200.dp,image.aspectRatio.toFloat())
                                    }
                                }
                            }
                            // Backdrops
                            BoxWrapper(
                                modifier = Modifier
                                    .padding(contentHorizontalPadding)
                            ){
                                LazyRow {
                                    items(images.backdrops.size){
                                        val image = images.backdrops[it]
                                        ImageItem(image.filePath, 200.dp,image.aspectRatio.toFloat())
                                    }
                                }
                            }
                        }
                    },
                    onFailed = { error, errorCode ->
                        Row (
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(15.dp),
                            horizontalArrangement = Arrangement.Center
                        ){
                            ErrorText(errorText = error, onRetry = onImageLoadFailed)
                        }
                    }
                )

            }
        }
    }
}

@Composable
fun ImageItem(
    imagePath: String,
    height: Dp,
    ratio: Float
) {
    SubcomposeAsyncImage(
        modifier = Modifier
            .padding(10.dp)
            .height(height)
            .aspectRatio(ratio)
            .clip(RoundedCornerShape(5)),
        model = ImageLoader.getUrl(imagePath, ImageLoader.ImageSize.W500),
        contentDescription = "",
        onLoading = {

        }
    )
}

@Composable
@Preview(showBackground = true)
fun Preview() {
    Box (
        modifier = Modifier
            .background(Color.Black)
    ){
        TitleSection(title = "Test Name", imagePath = "we", rating = 4f)

    }
}

@Composable
fun TitleSection(
    modifier: Modifier = Modifier,
    title: String,
    imagePath: String,
    rating: Float? = null,
) {
    val imageWidth = 150
    val imageHeight = imageWidth/ListItemBoxRatio
    Row(
        modifier = modifier
            .fillMaxWidth()
    ) {
        // Image Section
        SubcomposeAsyncImage(
            modifier = Modifier
                .width(imageWidth.dp)
                .height(imageHeight.dp)
                .clip(RoundedCornerShape(5)),
            model = ImageLoader.getUrl(imagePath, ImageLoader.ImageSize.W500)
                .log("url"),
            contentDescription = "",
            onLoading = {

            }
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .height(imageHeight.dp)
                .padding(horizontal = 15.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            // Title Section
            Text(
                text = title,
                style = h1Title
            )
            VSpace(space = 5.dp)
            // Rating
            if (rating != null) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Rating Icon
                    Icon(
                        modifier = Modifier
                            .size(15.dp),
                        imageVector = Icons.Filled.Star,
                        contentDescription = "",
                        tint = Gold
                    )
                    // Rating Text
                    Text(
                        text = String.format("%.1f", rating).plus("/10"),
                        Modifier.padding(start = 5.dp),
                        style = mediumContent.copy(
                            color = Gold
                        )
                    )
                }
            }
            VSpace(space = 20.dp)
        }

    }

}