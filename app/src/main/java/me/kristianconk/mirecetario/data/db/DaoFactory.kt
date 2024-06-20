package me.kristianconk.mirecetario.data.db

import android.content.Context
import androidx.room.Room

class DaoFactory(context: Context) {
    val db = Room.databaseBuilder(context, RecipeDatabase::class.java, "recipes-db").build()

    fun getRecipeDao() = db.recipeDao()

    fun getRecipeRemoteKeyDao() = db.recipeRemoteKeyDao()
}