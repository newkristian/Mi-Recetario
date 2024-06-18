package me.kristianconk.mirecetario.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
import me.kristianconk.mirecetario.ui.theme.MiRecetarioTheme
import me.kristianconk.mirecetario.util.simpleRecipe

@Composable
fun RecipeSimpleRow(recipe: Recipe, onClick: (Recipe) -> Unit, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Card(
        onClick = { onClick(recipe) }, modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row {
            Column {
                Text(text = recipe.title, modifier = Modifier.padding(8.dp))
                Text(text = recipe.description, modifier = Modifier.padding(horizontal = 8.dp))
            }
            recipe.urlPhoto?.let {
                AsyncImage(
                    model = ImageRequest.Builder(context).data(it).build(),
                    contentDescription = "Foto receta",
                    contentScale = ContentScale.Inside
                )
            } ?: run {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.meal),
                    contentDescription = "Foto receta",
                    modifier = Modifier
                        .padding(8.dp)
                        .size(width = 100.dp, height = 100.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun RecipeSimpleRowPreview(modifier: Modifier = Modifier) {
    MiRecetarioTheme {
        RecipeSimpleRow(simpleRecipe, onClick = {})
    }
}