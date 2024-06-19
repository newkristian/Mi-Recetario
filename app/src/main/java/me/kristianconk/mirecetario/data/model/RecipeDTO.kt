package me.kristianconk.mirecetario.data.model

import me.kristianconk.mirecetario.domain.model.Recipe
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.Date

data class RecipeDTO(
    val id: String,
    val title: String,
    val description: String,
    val urlPhoto: String?,
    val ingredients: List<String>,
    val steps: List<String>,
    val notes: String?,
    val creationDate: String,
    val latitude: Long?,
    val longitude: Long?
) {
    fun mapToRecipe() = Recipe(
        id = id,
        title = title,
        description = description,
        urlPhoto = urlPhoto,
        ingredients = ingredients,
        steps = steps,
        notes = notes ?: "",
        creationDate = Date(),
        latitude = latitude,
        longitude = longitude
    )

}

