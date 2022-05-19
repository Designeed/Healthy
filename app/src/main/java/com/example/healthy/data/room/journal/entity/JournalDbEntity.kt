package com.example.healthy.data.room.journal.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.healthy.data.room.food.entity.FoodDbEntity
import com.example.healthy.domain.model.Food
import com.example.healthy.domain.model.Journal
import java.util.*

@Entity(
    tableName = "journal"
)

data class JournalDbEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val foodTitle: String,
    val protein: Int,
    val fats: Int,
    val carbs: Int,
    val calories: Int,
    val date: String)

