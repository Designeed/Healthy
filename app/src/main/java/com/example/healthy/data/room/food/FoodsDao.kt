package com.example.healthy.data.room.food

import androidx.room.*
import com.example.healthy.data.room.food.entity.*
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodsDao {
    @Query("SELECT * FROM food WHERE title = :param")
    suspend fun findByTitle(param: String): FoodDbEntity?

    @Query("SELECT * FROM food")
    fun getAllFood(): Flow<List<FoodDbEntity>>

    @Query("SELECT id FROM food WHERE title = :param")
    suspend fun getIdByTitle(param: String): Long

    @Query("DELETE FROM food WHERE id = :param")
    suspend fun deleteFoodById(param: Long)

    @Update(entity = FoodDbEntity::class)
    suspend fun updateFoodTitle(updateFoodTitleTuple: UpdateFoodTitleTuple)

    @Update(entity = FoodDbEntity::class)
    suspend fun updateFoodProtein(updateFoodProteinTuple: UpdateFoodProteinTuple)

    @Update(entity = FoodDbEntity::class)
    suspend fun updateFoodFats(updateFoodFatsTuple: UpdateFoodFatsTuple)

    @Update(entity = FoodDbEntity::class)
    suspend fun updateFoodCarbs(updateCarbsTuple: UpdateFoodCarbsTuple)

    @Update(entity = FoodDbEntity::class)
    suspend fun updateFoodCalories(updateFoodCaloriesTuple: UpdateFoodCaloriesTuple)

    @Insert
    suspend fun addFood(foodDbEntities: FoodDbEntity)
}