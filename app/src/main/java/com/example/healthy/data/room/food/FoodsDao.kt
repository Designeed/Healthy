package com.example.healthy.data.room.food

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.healthy.data.room.food.entities.*
import kotlinx.coroutines.flow.Flow


@Dao
interface FoodsDao {
    @Query("SELECT * FROM food WHERE title = :title")
    suspend fun findByTitle(title: String): FoodDbEntities?

    @Query("SELECT * FROM food WHERE id = :foodId")
    fun getById(foodId: Long): Flow<FoodDbEntities>

    @Update(entity = FoodDbEntities::class)
    suspend fun updateFoodTitle(updateFoodTitleTuple: UpdateFoodTitleTuple)

    @Update(entity = FoodDbEntities::class)
    suspend fun updateFoodProtein(updateFoodProteinTuple: UpdateFoodProteinTuple)

    @Update(entity = FoodDbEntities::class)
    suspend fun updateFoodFats(updateFoodFatsTuple: UpdateFoodFatsTuple)

    @Update(entity = FoodDbEntities::class)
    suspend fun updateFoodCarbs(updateCarbsTuple: UpdateFoodCarbsTuple)

    @Update(entity = FoodDbEntities::class)
    suspend fun updateFoodCalories(updateFoodCaloriesTuple: UpdateFoodCaloriesTuple)

    @Insert
    suspend fun addFood(foodDbEntities: FoodDbEntities)
}