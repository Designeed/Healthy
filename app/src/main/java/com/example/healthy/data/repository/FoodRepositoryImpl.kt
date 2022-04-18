package com.example.healthy.data.repository

import android.database.sqlite.SQLiteConstraintException
import com.example.healthy.data.room.food.FoodsDao
import com.example.healthy.data.room.food.entities.*
import com.example.healthy.domain.model.Food
import com.example.healthy.domain.repository.FoodRepository
import java.lang.Exception

class FoodRepositoryImpl(private val foodDao: FoodsDao): FoodRepository {

    override suspend fun add(entity: Food) {

        try {
            foodDao.addFood(FoodDbEntities.fromFoodModel(entity))
        } catch (e: SQLiteConstraintException) {
            val appException = Exception()
            appException.initCause(e)
            throw appException
        }
    }

    override suspend fun findByTitle(param: String): Food {
        val foundedEntity = foodDao.findByTitle(param) ?: throw Exception("Данное блюдо не было найдено")

        return foundedEntity.toFoodModel()
    }

    override suspend fun getAllFood(): List<Food> = foodDao.getAllFood().map { it.toFoodModel() }

    override suspend fun getById(param: Long): Food = foodDao.getById(param).toFoodModel()

    override suspend fun updateTitle(foodId: Long, param: String) {
        foodDao.updateFoodTitle(UpdateFoodTitleTuple(id = foodId, title = param))
    }

    override suspend fun updateProteins(foodId: Long, param: Int) {
        foodDao.updateFoodProtein(UpdateFoodProteinTuple(id = foodId, protein = param))
    }

    override suspend fun updateFats(foodId: Long, param: Int) {
        foodDao.updateFoodFats(UpdateFoodFatsTuple(id = foodId, fats = param))
    }

    override suspend fun updateCarbs(foodId: Long, param: Int) {
        foodDao.updateFoodCarbs(UpdateFoodCarbsTuple(id = foodId, carbs = param))
    }

    override suspend fun updateCalories(foodId: Long, param: Int) {
        foodDao.updateFoodCalories(UpdateFoodCaloriesTuple(id = foodId, calories = param))
    }

}