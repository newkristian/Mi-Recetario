package me.kristianconk.mirecetario.presentation.feature.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.paging.compose.collectAsLazyPagingItems
import me.kristianconk.mirecetario.presentation.navigation.HomeNavHost
import me.kristianconk.mirecetario.ui.theme.MiRecetarioTheme
import org.koin.androidx.compose.koinViewModel

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MiRecetarioTheme {
                HomeNavHost(homeViewModel = koinViewModel())
            }
        }
    }
}

