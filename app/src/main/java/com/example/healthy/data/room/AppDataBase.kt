package com.example.healthy.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.healthy.data.room.food.FoodsDao
import com.example.healthy.data.room.food.entity.FoodDbEntity
import com.example.healthy.data.room.journal.JournalDao
import com.example.healthy.data.room.journal.entity.JournalDbEntity
import com.example.healthy.data.utils.DateTypeConverter

@Database(
    version = 1,
    entities = [
        FoodDbEntity::class,
        JournalDbEntity::class
    ]
)
@TypeConverters(DateTypeConverter::class)

abstract class AppDataBase: RoomDatabase(){

    abstract fun getFoodsDao(): FoodsDao

    abstract fun getJournalDao(): JournalDao
}