package com.app.moviesapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.app.moviesapp.R

val poppinsFont = FontFamily(
    Font(R.font.poppins_extrabold, FontWeight.ExtraBold),
    Font(R.font.poppins_bold, FontWeight.Bold),
    Font(R.font.poppins_semibold, FontWeight.SemiBold),
    Font(R.font.poppins_medium, FontWeight.Medium),
    Font(R.font.poppins_regular, FontWeight.Normal),
    Font(R.font.poppins_light, FontWeight.Light),
    Font(R.font.poppins_thin, FontWeight.Thin),
)

val topBarTitleStyle = TextStyle(
    fontFamily = poppinsFont,
    fontWeight = FontWeight.SemiBold,
    fontSize = 18.sp,
    color = Color.White
)

val h1Title = TextStyle(
    fontFamily = poppinsFont,
    fontWeight = FontWeight.SemiBold,
    fontSize = 18.sp,
    color = Color.White
)
val h2Title = TextStyle(
    fontFamily = poppinsFont,
    fontWeight = FontWeight.SemiBold,
    fontSize = 16.sp,
    color = Color.White
)
val h3Title = TextStyle(
    fontFamily = poppinsFont,
    fontWeight = FontWeight.SemiBold,
    fontSize = 14.sp,
    color = Color.White
)
val h4Title = TextStyle(
    fontFamily = poppinsFont,
    fontWeight = FontWeight.Medium,
    fontSize = 13.sp,
    color = Color.White
)
val h5Title = TextStyle(
    fontFamily = poppinsFont,
    fontWeight = FontWeight.Normal,
    fontSize = 12.sp,
    color = Color.White
)
val mediumContent = TextStyle(
    fontFamily = poppinsFont,
    fontWeight = FontWeight.Normal,
    fontSize = 13.sp,
    color = Color.White
)
val regularContent = TextStyle(
    fontFamily = poppinsFont,
    fontWeight = FontWeight.Normal,
    fontSize = 13.sp,
    color = Color.White
)