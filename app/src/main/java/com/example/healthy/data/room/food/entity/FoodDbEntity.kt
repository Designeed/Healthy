package com.example.healthy.data.room.food.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.healthy.domain.model.Food

@Entity(
    tableName = "food",
    indices = [
        Index("title", unique = true)
    ]
)

data class FoodDbEntity (
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(collate = ColumnInfo.NOCASE) val title: String,
    val protein: Int,
    val fats: Int,
    val carbs: Int,
    val calories: Int,
) {

    fun toFoodModel(): Food = Food(
        title = title,
        protein = protein,
        fats = fats,
        carbs = carbs,
        calories = calories
    )

    companion object {
        fun fromFoodModel(food: Food): FoodDbEntity = FoodDbEntity(
            id = 0,
            title = food.title,
            protein = food.protein,
            fats = food.fats,
            carbs = food.carbs,
            calories = food.calories
        )
    }
}