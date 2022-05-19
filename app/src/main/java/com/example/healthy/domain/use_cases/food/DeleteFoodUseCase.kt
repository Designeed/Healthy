package com.example.healthy.domain.use_cases.food

import com.example.healthy.domain.repository.FoodRepository
import java.lang.Exception

class DeleteFoodUseCase {
    suspend fun execute(title: String, repository: FoodRepository) {
        try {
            val id = repository.getIdByTitle(title)
            repository.deleteFood(id)
        } catch (ex: Exception) {
        }
    }
}