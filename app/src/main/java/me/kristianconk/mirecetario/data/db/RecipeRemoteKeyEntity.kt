package me.kristianconk.mirecetario.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipe_remote_keys")
data class RecipeRemoteKeyEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val prevPage: Int?,
    val nextPage: Int?
)