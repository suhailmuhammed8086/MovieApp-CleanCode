package com.app.moviesapp.ui.activity.home

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import com.app.moviesapp.R
import com.app.moviesapp.states.ResponseState
import com.app.moviesapp.ui.theme.MoviesAppTheme
import com.app.moviesapp.ui.theme.poppinsFont
import com.app.moviesapp.utils.log
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : ComponentActivity() {
    private val viewModel: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesAppTheme {
                // A surface container using the 'background' color from the theme
                Home(appName = getString(R.string.app_name))
            }
        }

        // Test API CALL
        viewModel.discoverMovieResponse.state.observe(this){
            Log.e("TAG", "onCreate: $it",)
            when(it) {
                is ResponseState.Failed -> {
                    it.error.log("Error")
                }
                ResponseState.Loading -> {}
                is ResponseState.Success -> {}
                is ResponseState.ValidationError -> {

                }
            }
        }

        viewModel.getDiscoverMovies()
    }
}


@Composable
fun Home(appName: String) {
    Box(
        Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
        ,
    ) {
        Text(text = appName,
            modifier = Modifier.align(Alignment.Center),
            style = TextStyle(
                fontFamily = poppinsFont,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = TextUnit(2f, TextUnitType.Sp)
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    MoviesAppTheme {
       Home("AppName")
    }
}