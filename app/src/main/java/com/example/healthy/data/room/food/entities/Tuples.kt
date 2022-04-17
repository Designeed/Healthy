package com.example.healthy.data.room.food.entities

data class UpdateFoodTitleTuple(
    val id: Long,
    val title: String
)

data class UpdateFoodProteinTuple(
    val id: Long,
    val protein: Int
)

data class UpdateFoodFatsTuple(
    val id: Long,
    val fats: Int
)

data class UpdateFoodCarbsTuple(
    val id: Long,
    val carbs: Int
)

data class UpdateFoodCaloriesTuple(
    val id: Long,
    val calories: Int
)
