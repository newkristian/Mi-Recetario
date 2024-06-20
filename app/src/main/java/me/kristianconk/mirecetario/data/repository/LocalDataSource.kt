package me.kristianconk.mirecetario.data.repository

import androidx.paging.PagingSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import me.kristianconk.mirecetario.data.db.RecipeDao
import me.kristianconk.mirecetario.data.db.RecipeEntity
import me.kristianconk.mirecetario.data.db.RecipeRemoteKeyDao
import me.kristianconk.mirecetario.data.db.RecipeRemoteKeyEntity
import me.kristianconk.mirecetario.data.db.toDB
import me.kristianconk.mirecetario.data.db.toDomain
import me.kristianconk.mirecetario.domain.model.Recipe

class LocalDataSource(
    val recipeDao: RecipeDao,
    val recipeRemoteKeyDao: RecipeRemoteKeyDao
) {
    fun recipes(): PagingSource<Int, RecipeEntity> = recipeDao.recipes()

    suspend fun getRecipes(): List<Recipe> = withContext(Dispatchers.IO) {
        val movies = recipeDao.getRecipes()
        return@withContext if (movies.isNotEmpty()) {
            movies.map { it.toDomain() }
        } else {
            emptyList()
        }
    }

    suspend fun searchRecipe(name: String): List<Recipe> = withContext(Dispatchers.IO) {
        return@withContext recipeDao.searchByNameOrIngredient(name).map {
            it.toDomain()
        }
    }

    suspend fun saveRecipes(recipes: List<Recipe>) = withContext(Dispatchers.IO) {
        recipeDao.saveRecipes(recipes.map { it.toDB() })
    }

    suspend fun getLastRemoteKey(): RecipeRemoteKeyEntity? = withContext(Dispatchers.IO) {
        recipeRemoteKeyDao.getLastRemoteKey()
    }

    suspend fun saveRemoteKey(key: RecipeRemoteKeyEntity) = withContext(Dispatchers.IO) {
        recipeRemoteKeyDao.saveRemoteKey(key)
    }

    suspend fun clearRecipes() = withContext(Dispatchers.IO) {
        recipeDao.clearRecipes()
    }

    suspend fun clearRemoteKeys() = withContext(Dispatchers.IO) {
        recipeRemoteKeyDao.clearRemoteKeys()
    }
}