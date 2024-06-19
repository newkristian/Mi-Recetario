package me.kristianconk.mirecetario

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import me.kristianconk.mirecetario.presentation.feature.SplashScreen
import me.kristianconk.mirecetario.presentation.feature.home.HomeActivity
import me.kristianconk.mirecetario.ui.theme.MiRecetarioTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MiRecetarioTheme {
                SplashScreen {
                    val i = Intent(this, HomeActivity::class.java)
                    startActivity(i)
                    finish()
                }
            }
        }
    }
}
