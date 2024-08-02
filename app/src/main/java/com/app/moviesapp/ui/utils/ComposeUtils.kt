package com.app.moviesapp.ui.utils

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp


@Composable
fun HSpace(space: Dp) {
    Spacer(
        Modifier
            .width(space)
    )
}
@Composable
fun VSpace(space: Dp) {
    Spacer(
        Modifier
            .height(space)
    )
}