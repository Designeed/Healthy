package com.example.healthy.domain.use_cases

import com.example.healthy.domain.model.Food
import com.example.healthy.domain.repository.FoodRepository

class EditFoodUseCase{
    suspend fun execute(editedFood: Food, repository: FoodRepository) {
        val currentFood = repository.getFoodByTitle(selectedFoodTitle)
        val currentId = repository.getIdByTitle(selectedFoodTitle)

        if (currentFood.title != editedFood.title)
            repository.updateTitle(currentId, editedFood.title)

        if (currentFood.protein != editedFood.protein)
            repository.updateProteins(currentId, editedFood.protein)

        if (currentFood.fats != editedFood.fats)
            repository.updateFats(currentId, editedFood.fats)

        if (currentFood.carbs != editedFood.carbs)
            repository.updateCarbs(currentId, editedFood.carbs)

        if (currentFood.calories != editedFood.calories)
            repository.updateCalories(currentId, editedFood.calories)
    }

    companion object {
        lateinit var selectedFoodTitle: String
    }
}