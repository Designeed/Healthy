package com.example.healthy.domain.use_cases.food

import com.example.healthy.domain.repository.FoodRepository

class DeleteFoodUseCase {
    suspend fun execute(title: String, repository: FoodRepository){
        val id = repository.getIdByTitle(title)
        repository.deleteFood(id)
    }
}