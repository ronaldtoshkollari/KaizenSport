package com.example.kaizensport.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kaizensport.data.local.entity.CategoryEntity

@Dao
interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSportCategory(categoryEntity: CategoryEntity)

    @Query("DELETE FROM sport_categories WHERE id = :id")
    suspend fun deleteSportCategoryEntity(id: String)

    @Query("DELETE FROM sport_categories")
    suspend fun deleteAllCategories()

    @Query("SELECT * FROM sport_categories")
    suspend fun getAllCategories(): List<CategoryEntity>

}