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
    tableName = "journal",
    foreignKeys = [
        ForeignKey(
            entity = FoodDbEntity::class,
            parentColumns = ["id"],
            childColumns = ["food_id"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)

data class JournalDbEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "food_id") val foodId: Long,
    val date: Date)
