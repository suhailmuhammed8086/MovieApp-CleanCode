package com.app.moviesapp.ui.composes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.app.moviesapp.ui.theme.Black
import com.app.moviesapp.ui.theme.Gold
import com.app.moviesapp.ui.theme.GreyBlack
import com.app.moviesapp.ui.theme.ListItemBoxRatio
import com.app.moviesapp.ui.theme.ShimmerColor
import com.app.moviesapp.ui.theme.White
import com.app.moviesapp.ui.theme.h2Title
import com.app.moviesapp.ui.theme.h3Title
import com.app.moviesapp.ui.theme.h4Title
import com.app.moviesapp.ui.theme.mediumContent
import com.app.moviesapp.ui.theme.poppinsFont
import com.app.moviesapp.ui.utils.HSpace
import com.app.moviesapp.ui.utils.VSpace
import com.app.moviesapp.utils.ImageLoader

/**Use to list Movie or Tv Show lists*/
@Composable
fun GridListItemCompose(
    modifier: Modifier = Modifier,
    uniqueId: Long,
    title: String,
    imagePath: String?,
    rating: Float? = null,
    onItemClick: (id: Long) -> Unit
) {
    Box(
        modifier = modifier
            .padding(start = 10.dp, end = 5.dp, top = 10.dp)
            .background(color = Color.DarkGray, shape = RoundedCornerShape(2))
            .clickable { onItemClick(uniqueId) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            SubcomposeAsyncImage(
                model =  ImageLoader.getUrl(imagePath?:""),
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(2f / 3f),
                loading = {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .width(10.dp)
                            .height(10.dp),

                        color = Color.White
                    )
                }
            )
            Column() {
                Text(
                    text = title,
                    Modifier.padding(horizontal = 5.dp, 2.dp),
                    minLines = 2,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = TextStyle(
                        color = Color.White,
                        fontFamily = poppinsFont,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 15.sp,
                    )
                )
            }
        }

        if (rating != null) {
            Surface(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .offset(10.dp, 10.dp),
                color = Color.Gray.copy(alpha = 0.5f),
                shape = RoundedCornerShape(100)
            ) {
                Text(
                    text = String.format("%.1f", rating),
                    Modifier.padding(horizontal = 7.dp),
                    style = TextStyle(
                        color = Color.White,
                        fontFamily = poppinsFont,
                        fontWeight = FontWeight.Normal,
                        fontSize = 15.sp
                    )
                )
            }
        }
    }
}
@Composable
fun HorizontalListItemCompose(
    modifier: Modifier = Modifier,
    uniqueId: Long,
    title: String,
    imagePath: String?,
    rating: Float? = null,
    onItemClick: (id: Long) -> Unit
) {
    Box(
        modifier = modifier
            .padding(end = 15.dp, top = 10.dp)
            .width(160.dp)
            .aspectRatio(ListItemBoxRatio * .8f)
            .clickable { onItemClick(uniqueId) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            // Image
            SubcomposeAsyncImage(
                model = ImageLoader.getUrl(imagePath ?: ""),
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(ListItemBoxRatio)
                    .background(color = Color.DarkGray, shape = RoundedCornerShape(2))
                ,
                loading = {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .width(10.dp)
                            .height(10.dp)
                            .align(Alignment.Center),

                        color = Color.White
                    )
                }
            )
            Column() {
                // Movie Title
                VSpace(space = 10.dp)
                Text(
                    text = title,
                    Modifier.padding(vertical =  2.dp),
                    minLines = 2,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = h4Title
                        .copy(fontWeight = FontWeight.Normal)
                )
            }
        }

        // Rating
        if (rating != null) {
            Surface(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .offset((5).dp, 5.dp),
                color = Black,
//                shape = RoundedCornerShape(100)
            ) {
                Row (
                    modifier = Modifier
                        .padding(horizontal = 3.dp, vertical = 0.5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = String.format("%.1f", rating),
                        style = TextStyle(
                            color = White,
                            fontFamily = poppinsFont,
                            fontWeight = FontWeight.Medium,
                            fontSize = 12.sp
                        )
                    )
                    HSpace(space = 2.dp)
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = "",
                        tint = Gold,
                        modifier = Modifier.size(14.dp)
                    )
                }
            }
        }
    }
}


@Composable
fun VerticalDetailedListItem(
    modifier: Modifier = Modifier,
    uniqueId: Long,
    title: String,
    imagePath: String?,
    backgroundImagePath: String?,
    rating: Float? = null,
    overView: String,
    releaseDate: String,
    onItemClick: (id: Long) -> Unit
) {
    Box (
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .clickable { onItemClick(uniqueId) }
    ){
        // Background Image
        SubcomposeAsyncImage(
            model = ImageLoader.getUrl(backgroundImagePath ?: ""),
            contentDescription = "",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxSize()
            ,
            loading = {

            }
        )

        // Main Content
        Row (
            modifier = Modifier
                .fillMaxSize()
                .background(Black.copy(alpha = .8f))
                .padding(10.dp)
        ){
            // Poster Image
            Box (
                modifier = modifier.fillMaxHeight()
            ){
                SubcomposeAsyncImage(
                    model = ImageLoader.getUrl(imagePath ?: ""),
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .fillMaxHeight()
                        .aspectRatio(ListItemBoxRatio)
                    ,
                    loading = {

                    }
                )
                // Rating
                if (rating != null) {
                    Surface(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .offset(10.dp, 10.dp),
                        color = Color.Gray.copy(alpha = 0.5f),
                        shape = RoundedCornerShape(100)
                    ) {
                        Text(
                            text = String.format("%.1f", rating),
                            Modifier.padding(horizontal = 7.dp),
                            style = TextStyle(
                                color = Color.White,
                                fontFamily = poppinsFont,
                                fontWeight = FontWeight.Normal,
                                fontSize = 15.sp
                            )
                        )
                    }
                }

            }


            HSpace(space = 10.dp)
            // Other Details
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
            ) {
                VSpace(space = 10.dp)
                Text(
                    text = title,
                    Modifier.padding(horizontal = 5.dp),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = h2Title
                )
                VSpace(space = 5.dp)
                Text(
                    text = overView,
                    Modifier.padding(horizontal = 5.dp),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = mediumContent
                )
            }
        }
    }
}

@Composable
fun GenreCompose(
    modifier: Modifier = Modifier,
    genreId: Long,
    name: String,
    onClick: (genreId: Long) -> Unit
) {
    Box(modifier = modifier
        .background(GreyBlack.copy(alpha = 0.7f), shape = RoundedCornerShape(10))
        .clickable { onClick(genreId) }
    ) {
        Text(
            text = name,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 5.dp),
            style = TextStyle(
                fontFamily = poppinsFont,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = Color.White,
            )
        )
    }
}
@Composable
fun GenreShimmerCompose(
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier
        .background(ShimmerColor, shape = RoundedCornerShape(10))
    ) {
        Text(
            text = "",
            modifier = Modifier
                .width(50.dp)
                .padding(horizontal = 7.dp, vertical = 1.dp),
            style = TextStyle(
                fontFamily = poppinsFont,
                fontSize = 15.sp,
                color = Color.White,
            )
        )
    }
}


@Composable
fun ImagePagerItem(
    modifier: Modifier = Modifier,
    imagePath: String,
    onClick: () -> Unit
) {
    SubcomposeAsyncImage(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1070 / 600f)
        ,
        model = ImageLoader.getUrl(imagePath),
        contentDescription = "",
        onLoading = {

        }
    )
}

@Composable
fun ErrorText(modifier: Modifier = Modifier, errorText: String, errorTextColor: Color = Color.White, onRetry: (()->Unit)? = null) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = errorText,
            style = TextStyle(
                fontFamily = poppinsFont,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                color = errorTextColor
            )
        )
        if (onRetry != null) {
            VSpace(space = 10.dp)
            Text(
                text = "Retry",
                style = TextStyle(
                    fontFamily = poppinsFont,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = errorTextColor
                ),
                modifier = Modifier
                    .clickable(onClick = onRetry)
            )
        }
    }
}


@Composable
fun BoxWrapper(
    modifier: Modifier = Modifier,
    insetPadding: Dp = 10.dp,
    content: @Composable() (BoxScope.() -> Unit)
) {
    Box(
        modifier = modifier
            .background(GreyBlack.copy(alpha = 0.8f), shape = RoundedCornerShape(2))
            .padding(insetPadding),
        content = content
    )
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    VerticalDetailedListItem(
        uniqueId = 1,
        title = "Title",
        imagePath = "",
        backgroundImagePath = "",
        rating = 4.5f,
        overView = "over view".repeat(20),
        releaseDate = "12-2-2222",
        onItemClick = {})
}