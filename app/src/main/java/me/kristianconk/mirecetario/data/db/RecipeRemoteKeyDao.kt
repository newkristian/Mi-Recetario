package me.kristianconk.mirecetario.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RecipeRemoteKeyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveRemoteKey(keys: RecipeRemoteKeyEntity)

    @Query("SELECT * FROM recipe_remote_keys WHERE id=:id")
    suspend fun getRemoteKeyByRecipeId(id: Int): RecipeRemoteKeyEntity?

    @Query("DELETE FROM recipe_remote_keys")
    suspend fun clearRemoteKeys()

    @Query("SELECT * FROM recipe_remote_keys WHERE id = (SELECT MAX(id) FROM recipe_remote_keys)")
    suspend fun getLastRemoteKey(): RecipeRemoteKeyEntity?
}