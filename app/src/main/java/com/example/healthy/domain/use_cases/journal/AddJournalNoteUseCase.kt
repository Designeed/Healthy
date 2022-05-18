package com.example.healthy.domain.use_cases.journal

import com.example.healthy.domain.model.Food
import com.example.healthy.domain.model.Journal
import com.example.healthy.domain.repository.JournalRepository
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class AddJournalNoteUseCase {
     suspend fun execute(foodId: Long, food: Food, weight: Int, repository: JournalRepository) {
        val calculatedFood = calculateFood(food, weight)
        val journal = Journal(
            calculatedFood,
            getCurrentDate()
        )
        repository.addJournalNote(foodId, calculatedFood, journal)
    }

    private fun calculateFood(food: Food, param: Int) : Food {
        val weight = param / 100
        return Food(
            food.title,
            food.protein * weight,
            food.fats * weight,
            food.carbs * weight,
            food.calories * weight
        )
    }

    private fun getCurrentDate() : String = DateFormat.getDateInstance().format(Date())
}