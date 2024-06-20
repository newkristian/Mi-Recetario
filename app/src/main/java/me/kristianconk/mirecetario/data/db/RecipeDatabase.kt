package me.kristianconk.mirecetario.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [RecipeEntity::class, RecipeRemoteKeyEntity::class],
    version = 1,
    exportSchema = false
)
abstract class RecipeDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
    abstract fun recipeRemoteKeyDao(): RecipeRemoteKeyDao

    companion object {
        private var INSTANCE: RecipeDatabase? = null
        const val DB_NAME = "recipes-db"

        fun getInstance(context: Context): RecipeDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }
        }

        private fun buildDatabase(context: Context): RecipeDatabase {
            // esta base solo se usa como cache de informacion del backend por lo que
            // no hay problema con que se destruya en migraciones
            return Room.databaseBuilder(context, RecipeDatabase::class.java, DB_NAME)
                .fallbackToDestructiveMigration().build()
        }
    }
}