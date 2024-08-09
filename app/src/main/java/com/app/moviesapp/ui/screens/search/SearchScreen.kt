package com.app.moviesapp.ui.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.app.moviesapp.ui.theme.poppinsFont
import com.app.moviesapp.ui.theme.topBarTitleStyle

@Composable
fun SearchScreen(
    navController: NavController
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .clickable {
                navController.popBackStack()
            }
    ) {
        Text(
            text = "Search Screen \n Coming soon",
            modifier = Modifier
                .align(Alignment.Center),
            style = TextStyle(
                fontFamily = poppinsFont,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                color = Color.White
            )
        )
    }
}