package me.kristianconk.mirecetario.data.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [RecipeEntity::class, RecipeRemoteKeyEntity::class],
    version = 1,
    exportSchema = false
)
abstract class RecipeDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
    abstract fun recipeRemoteKeyDao(): RecipeRemoteKeyDao
}