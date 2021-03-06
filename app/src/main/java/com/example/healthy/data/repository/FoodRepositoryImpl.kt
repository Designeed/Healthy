package com.example.healthy.data.repository

import android.database.sqlite.SQLiteConstraintException
import com.example.healthy.data.room.food.FoodsDao
import com.example.healthy.data.room.food.entity.*
import com.example.healthy.domain.model.Food
import com.example.healthy.domain.repository.FoodRepository
import kotlinx.coroutines.flow.*
import java.lang.Exception

class FoodRepositoryImpl(private val foodDao: FoodsDao): FoodRepository {

    override suspend fun add(entity: Food){
        try {
            foodDao.addFood(FoodDbEntity.fromFoodModel(entity))
        } catch (ex: SQLiteConstraintException) {
            throw ex
        }
    }

    override suspend fun getFoodByTitle(param: String): Food {
        val foundedEntity = foodDao.findByTitle(param) ?: throw Exception()

        return foundedEntity.toFoodModel()
    }

    override fun getAllFood(): Flow<List<Food>>{
        return foodDao.getAllFood().map {
            entity -> entity.sortedBy{
                it.title
            }.map {
                it.toFoodModel()
            }
        }
    }

    override suspend fun getIdByTitle(param: String): Long = foodDao.getIdByTitle(param)

    override suspend fun deleteFood(param: Long) = foodDao.deleteFoodById(param)

    override suspend fun updateTitle(foodId: Long, param: String) {
        try {
            foodDao.updateFoodTitle(UpdateFoodTitleTuple(id = foodId, title = param))
        } catch (ex: SQLiteConstraintException) {
            throw ex
        }
    }

    override suspend fun updateProteins(foodId: Long, param: Int) = foodDao.updateFoodProtein(UpdateFoodProteinTuple(id = foodId, protein = param))

    override suspend fun updateFats(foodId: Long, param: Int) = foodDao.updateFoodFats(UpdateFoodFatsTuple(id = foodId, fats = param))

    override suspend fun updateCarbs(foodId: Long, param: Int) = foodDao.updateFoodCarbs(UpdateFoodCarbsTuple(id = foodId, carbs = param))

    override suspend fun updateCalories(foodId: Long, param: Int) = foodDao.updateFoodCalories(UpdateFoodCaloriesTuple(id = foodId, calories = param))


}