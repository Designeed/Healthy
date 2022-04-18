package com.example.healthy.domain.use_case

import com.example.healthy.domain.model.Food
import com.example.healthy.domain.repository.FoodRepository

typealias FoodListener = (foods: List<Food>) -> Unit

class FoodService {

    private var listeners= mutableListOf<FoodListener>()

    private var foodList = listOf<Food>()

    init {

    }

    fun addListener(listener: FoodListener){
        listeners.add(listener)
        listener.invoke(foodList)
    }

    fun deleteListener(listener: FoodListener){
        listeners.remove(listener)
    }

}