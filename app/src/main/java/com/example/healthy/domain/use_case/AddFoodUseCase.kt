package com.example.healthy.domain.use_case

import android.text.BoringLayout
import com.example.healthy.domain.model.Food
import com.example.healthy.domain.repository.FoodRepository

class AddFoodUseCase(private val entity: Food, private val repository: FoodRepository) {

    suspend fun execute() = repository.add(entity = entity)
}