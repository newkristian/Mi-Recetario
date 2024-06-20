package me.kristianconk.mirecetario.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import me.kristianconk.mirecetario.domain.model.Recipe
import java.util.Date

@Entity(tableName = "recipe")
data class RecipeEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val uuid: String,
    val title: String,
    val description: String,
    val urlPhoto: String?,
    val ingredients:String,
    val steps: String,
    val notes: String,
    val creationDate: String,
    val latitude: Double?,
    val longitude: Double?
)

fun RecipeEntity.toDomain() = Recipe(
    id = uuid,
    title = title,
    description = description,
    urlPhoto = urlPhoto,
    ingredients = ingredients.split("|"),
    steps = steps.split("|"),
    notes = notes,
    creationDate = Date(),
    latitude = latitude,
    longitude = longitude
)

fun Recipe.toDB() = RecipeEntity(
    uuid = id,
    title = title,
    description = description,
    urlPhoto = urlPhoto,
    ingredients = ingredients.joinToString(separator = "|"),
    steps = steps.joinToString(separator = "|"),
    notes = notes,
    creationDate = creationDate.toString(),
    latitude = latitude,
    longitude = longitude
)