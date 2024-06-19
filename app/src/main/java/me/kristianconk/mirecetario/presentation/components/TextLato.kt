package me.kristianconk.mirecetario.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import me.kristianconk.mirecetario.ui.theme.LatoFontFamily

@Composable
fun TextLato(description: String, modifier: Modifier = Modifier) {
    Text(
        text = description,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        style = MaterialTheme.typography.bodySmall,
        fontFamily = LatoFontFamily,
        color = MaterialTheme.colorScheme.onSurface
    )
}