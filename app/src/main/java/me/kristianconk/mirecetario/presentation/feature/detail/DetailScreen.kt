package me.kristianconk.mirecetario.presentation.feature.detail

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import me.kristianconk.mirecetario.R
import me.kristianconk.mirecetario.domain.model.Recipe
import me.kristianconk.mirecetario.presentation.components.TextLato
import me.kristianconk.mirecetario.presentation.components.TitleAlice
import me.kristianconk.mirecetario.ui.theme.AliceFontFamily
import me.kristianconk.mirecetario.ui.theme.LatoFontFamily
import me.kristianconk.mirecetario.ui.theme.MiRecetarioTheme
import me.kristianconk.mirecetario.util.simpleRecipe

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(recipe: Recipe, actions: DetailActions) {
    val context = LocalContext.current
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(title = {
                Row(){
                    IconButton(onClick = actions.onBackClick) {
                        Icon(imageVector = ImageVector.vectorResource(id = com.google.android.material.R.drawable.abc_ic_ab_back_material), contentDescription = "atras")
                    }
                    TitleAlice(title = recipe.title)
                }
            })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background)
                .verticalScroll(rememberScrollState())
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp)
            ) {
                recipe.urlPhoto?.let {
                    AsyncImage(
                        model = ImageRequest.Builder(context).data(it).build(),
                        contentDescription = "Foto receta",
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxSize()
                    )
                } ?: run {
                    Image(
                        imageVector = ImageVector.vectorResource(id = R.drawable.meal),
                        contentDescription = "Foto receta",
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxSize()
                    )
                }
            }
            TextLato(description = recipe.description)
            TitleAlice(title = "Ingredientes")
            recipe.ingredients.forEach {
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.smooth_check),
                        contentDescription = "bullet",
                        modifier = Modifier.size(16.dp, 16.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    TextLato(description = it)
                }
            }
            TitleAlice(title = "Instrucciones")
            recipe.steps.forEach {
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.square),
                        contentDescription = "bullet",
                        modifier = Modifier.size(16.dp, 16.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                    TextLato(description = it)
                }
            }
            TitleAlice(title = "Notas")
            TextLato(description = recipe.notes)
            if (recipe.latitude != null && recipe.longitude != null) {
                Button(
                    onClick = { actions.onMapClick(recipe.latitude, recipe.longitude, recipe.title) }, modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(text = "Ver en mapa")
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Preview
@Composable
fun DetailScreenPreview(modifier: Modifier = Modifier) {
    MiRecetarioTheme {
        DetailScreen(simpleRecipe, DetailActions())
    }
}

@Preview
@Composable
fun DetailScreenPreviewDark(modifier: Modifier = Modifier) {
    MiRecetarioTheme(darkTheme = true) {
        DetailScreen(simpleRecipe, DetailActions())
    }
}