package com.example.healthy.domain.use_case

import android.widget.Space
import com.example.healthy.domain.model.Food
import com.example.healthy.domain.repository.FoodRepository

typealias FoodListener = (foods: List<Food>) -> Unit

class FoodService {
    private var listeners= mutableListOf<FoodListener>()
    private var foodList = mutableListOf<Food>()

    fun loadFood(){

        notifyChanges()
    }

    fun addFood(title: String, protein: Int, fats: Int, carbs: Int, calories: Int){
        foodList.add(Food(
            title = title,
            protein = protein,
            fats = fats,
            carbs = carbs,
            calories = calories))

        notifyChanges()
    }

    fun addListener(listener: FoodListener){
        listeners.add(listener)
        listener.invoke(foodList)
    }

    fun deleteListener(listener: FoodListener){
        listeners.remove(listener)
    }

    private fun notifyChanges() {
        listeners.forEach { it.invoke(foodList) }
    }

}