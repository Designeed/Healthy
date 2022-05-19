package com.example.healthy.domain.use_cases.journal

import com.example.healthy.domain.model.Food
import com.example.healthy.domain.model.Journal
import com.example.healthy.domain.repository.JournalRepository
import java.text.DateFormat
import java.util.*

class AddJournalNoteUseCase {
     suspend fun execute(food: Food, weight: Float, repository: JournalRepository) {
        val calculatedFood = calculateFood(food, weight)
        val journal = Journal(
            calculatedFood,
            getCurrentDate()
        )
        repository.addJournalNote(journal)
    }

    private fun calculateFood(food: Food, param: Float) : Food {
        val weight = param / 100
        return Food(
            food.title,
            (food.protein * weight).toInt(),
            (food.fats * weight).toInt(),
            (food.carbs * weight).toInt(),
            (food.calories * weight).toInt()
        )
    }

    private fun getCurrentDate() : String = DateFormat.getDateTimeInstance().format(Date())
}