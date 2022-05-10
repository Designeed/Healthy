package com.example.healthy.domain.repository

import com.example.healthy.domain.model.Food
import kotlinx.coroutines.flow.Flow

interface FoodRepository {

    suspend fun add(entity: Food)

    suspend fun getFoodByTitle(param: String): Food

    fun getAllFood(): Flow<List<Food>>

    suspend fun getIdByTitle(param: String): Long

    suspend fun deleteFood(param: Long)

    suspend fun updateTitle(foodId: Long, param: String)

    suspend fun updateProteins(foodId: Long, param: Int)

    suspend fun updateFats(foodId: Long, param: Int)

    suspend fun updateCarbs(foodId: Long, param: Int)

    suspend fun updateCalories(foodId: Long, param: Int)
}