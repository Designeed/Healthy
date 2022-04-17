package com.example.healthy.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.healthy.data.room.food.FoodsDao
import com.example.healthy.data.room.food.entities.FoodDbEntities

@Database(
    version = 1,
    entities = [
        FoodDbEntities::class
    ]
)
abstract class AppDataBase: RoomDatabase(){

    abstract fun getFoodsDao(): FoodsDao
}