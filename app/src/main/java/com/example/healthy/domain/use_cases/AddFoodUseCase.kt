package com.example.healthy.domain.use_cases

import com.example.healthy.domain.model.Food
import com.example.healthy.domain.repository.FoodRepository

class AddFoodUseCase {
    suspend fun execute(entity: Food, repository: FoodRepository) = repository.add(entity = entity)
}