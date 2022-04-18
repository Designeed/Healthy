package com.example.healthy.domain.repository

import com.example.healthy.domain.model.Food

interface FoodRepository {

    suspend fun add(entity: Food)

    suspend fun findByTitle(param: String): Food

    suspend fun getAllFood(): List<Food>

    suspend fun getById(param: Long): Food

    suspend fun updateTitle(foodId: Long, param: String)

    suspend fun updateProteins(foodId: Long, param: Int)

    suspend fun updateFats(foodId: Long, param: Int)

    suspend fun updateCarbs(foodId: Long, param: Int)

    suspend fun updateCalories(foodId: Long, param: Int)
}