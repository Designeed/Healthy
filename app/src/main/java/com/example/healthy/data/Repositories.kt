package com.example.healthy.data

import android.content.Context
import androidx.room.Room
import com.example.healthy.data.room.AppDataBase

object Repositories {
    private lateinit var applicationContext: Context

    private val database: AppDataBase by lazy<AppDataBase>{
        Room.databaseBuilder(applicationContext, AppDataBase::class.java, "Healthy.db").build()
    }

    fun init(context: Context){
        applicationContext = context
    }
}