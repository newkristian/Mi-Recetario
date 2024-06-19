package me.kristianconk.mirecetario.data.model

import com.google.gson.annotations.SerializedName
import me.kristianconk.mirecetario.domain.model.Recipe
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
    @SerializedName("latitud")
    val latitude: Double?,
    @SerializedName("longitud")
    val longitude: Double?
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

