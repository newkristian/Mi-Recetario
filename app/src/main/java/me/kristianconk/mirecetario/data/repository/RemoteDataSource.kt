package me.kristianconk.mirecetario.data.repository

import me.kristianconk.mirecetario.data.api.RecipeApi
import me.kristianconk.mirecetario.domain.model.Recipe

class RemoteDataSource(
    private val api: RecipeApi
) {
    suspend fun getRecipes(pageNumber: Int): List<Recipe> {
        return api.getRecipes(pageNumber)
    }
}