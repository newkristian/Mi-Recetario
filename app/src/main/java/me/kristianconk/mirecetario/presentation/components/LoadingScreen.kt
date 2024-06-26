package me.kristianconk.mirecetario.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import me.kristianconk.mirecetario.R
import me.kristianconk.mirecetario.ui.theme.MiRecetarioTheme

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.kitchen))
        LottieAnimation(composition, modifier = Modifier.align(Alignment.Center))
    }
}

@Preview
@Composable
fun LoadingScreenPreview(modifier: Modifier = Modifier) {
    MiRecetarioTheme {
        LoadingScreen()
    }
}