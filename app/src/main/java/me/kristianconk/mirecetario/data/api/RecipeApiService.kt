package me.kristianconk.mirecetario.data.api

import me.kristianconk.mirecetario.data.model.RecipeDTO
import retrofit2.http.GET

interface RecipeApiService {

    @GET("/recipe")
    suspend fun getRecipes(): List<RecipeDTO>
}