package me.kristianconk.mirecetario.presentation.feature

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.kristianconk.mirecetario.R
import me.kristianconk.mirecetario.ui.theme.AliceFontFamily
import me.kristianconk.mirecetario.ui.theme.LatoFontFamily
import me.kristianconk.mirecetario.ui.theme.MiRecetarioTheme

@Composable
fun SplashScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.shakers_logo),
                contentDescription = "Logo recetario",
                modifier = Modifier
                    .size(240.dp, 240.dp),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
            )
            Text(
                text = "Mi recetario",
                fontFamily = AliceFontFamily,
                fontSize = 40.sp,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "listo para cocinar",
                fontFamily = LatoFontFamily,
                color = MaterialTheme.colorScheme.secondary
            )
        }
    }
}

@Preview
@Composable
fun SplashScreenPreview(modifier: Modifier = Modifier) {
    MiRecetarioTheme {
        SplashScreen()
    }
}