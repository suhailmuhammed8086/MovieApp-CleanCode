package com.app.moviesapp.ui.composes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.app.moviesapp.ui.activity.ui.theme.GreyBlack
import com.app.moviesapp.ui.activity.ui.theme.ShimmerColor
import com.app.moviesapp.ui.theme.poppinsFont

/**Use to list Movie or Tv Show lists*/
@Composable
fun ListItemCompose(
    modifier: Modifier = Modifier,
    uniqueId: Int,
    title: String,
    imagePath: String?,
    rating: Float? = null,
    onItemClick: (id: Int) -> Unit
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
                model = "http://image.tmdb.org/t/p/w500/${imagePath}",
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
                color = Color.Gray,
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
fun GenreCompose(
    modifier: Modifier = Modifier,
    genreId: Long,
    name: String,
    onClick: (genreId: Long) -> Unit
) {
    Box(modifier = modifier
        .background(Color.White, shape = RoundedCornerShape(10))
        .clickable { onClick(genreId) }
    ) {
        Text(
            text = name,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 5.dp),
            style = TextStyle(
                fontFamily = poppinsFont,
                fontSize = 15.sp,
                color = GreyBlack,
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

@Preview(showBackground = true)
@Composable
fun Preview() {
    val genres = listOf("Action", "Fantacy", "Scientific","Adventure")
    Row {
        genres.forEachIndexed { index, s ->
            GenreShimmerCompose(
                modifier = Modifier.padding(horizontal = 5.dp),
            )
        }
    }
}