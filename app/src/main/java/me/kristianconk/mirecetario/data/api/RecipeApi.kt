package me.kristianconk.mirecetario.data.api

import me.kristianconk.mirecetario.domain.model.Recipe
import retrofit2.Retrofit

class RecipeApi(
    val retrofit: Retrofit
) {

    val apiService = retrofit.create(RecipeApiService::class.java)

    suspend fun getRecipes(page: Int): List<Recipe> {
        // este condicional es para simular una respuesta paginada debido a que el backend
        // no puede procesar peticiones paginadas
        val recipes =
            if (page < 2) apiService.getRecipes().map { it.mapToRecipe() } else emptyList()
        return recipes
    }
}