package com.example.healthy.domain.use_case

import com.example.healthy.domain.model.Food

typealias FoodListener = (foods: List<Food>) -> Unit

class FoodService {

    private var listeners= mutableListOf<FoodListener>()

    private var foodList = listOf<Food>()

    init {
        foodList = listOf(
            Food("Гречкаaaaaaaaaaaaaaaaaa", 1111111111, 222222222, 33333333, 444444444),
            Food("Гречка", 123, 300, 50, 453),
            Food("Гречка", 1, 2, 3, 4),
            Food("Гречка", 1, 2, 3, 4),
            Food("Гречка", 1, 2, 3, 4),
            Food("Каша", 1, 2, 3, 4),
            Food("Каша", 1, 2, 3, 4),
            Food("Каша", 1, 2, 3, 4),
            Food("Каша", 1, 2, 3, 4),
            Food("Каша", 1, 2, 3, 4),
            Food("Манная", 1, 2, 3, 4),
            Food("Манная", 1, 2, 3, 4),
            Food("Манная", 1, 2, 3, 4),
            Food("Манная", 1, 2, 3, 4)
        )
    }

    fun addListener(listener: FoodListener){
        listeners.add(listener)
        listener.invoke(foodList)
    }

    fun deleteListener(listener: FoodListener){
        listeners.remove(listener)
    }

}