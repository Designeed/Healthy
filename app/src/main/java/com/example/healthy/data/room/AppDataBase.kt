package com.example.healthy.data.room

import android.content.Context
import androidx.room.*
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

    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDatabase(context: Context): AppDataBase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "healthy_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}