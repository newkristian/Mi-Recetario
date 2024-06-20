package me.kristianconk.mirecetario.data.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RecipeDao {

    @Query("SELECT * FROM recipe ORDER BY creationDate, id")
    fun recipes(): PagingSource<Int, RecipeEntity>

    @Query("SELECT * FROM recipe ORDER BY creationDate, id")
    fun getRecipes(): List<RecipeEntity>

    @Query("SELECT * FROM recipe WHERE id = :recipeId")
    suspend fun getRecipe(recipeId: String): RecipeEntity?

    @Query("SELECT * FROM recipe WHERE title LIKE :name OR ingredients LIKE :name")
    suspend fun searchByNameOrIngredient(name:String): List<RecipeEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveRecipes(recipes: List<RecipeEntity>)

    @Query("DELETE FROM recipe")
    suspend fun clearRecipes()
}