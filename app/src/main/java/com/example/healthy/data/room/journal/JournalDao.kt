package com.example.healthy.data.room.journal

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.healthy.data.room.food.entity.FoodDbEntity
import com.example.healthy.data.room.journal.entity.JournalDbEntity
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface JournalDao {
    @Query("SELECT * FROM food LEFT JOIN journal ON food.id = journal.food_id")
    fun getAllJournal(): Flow<Map<FoodDbEntity, JournalDbEntity>>

    @Query("SELECT id FROM journal WHERE food_id = :foodId AND date = :date")
    suspend fun getJournalId(foodId: Long, date: Date): Long

    @Query("DELETE FROM journal WHERE id = :param")
    suspend fun deleteJournalNoteById(param: Long)

    @Update(entity = JournalDbEntity::class)
    suspend fun updateJournalNote(journal: JournalDbEntity)

    @Insert
    fun addJournalNote(journal: JournalDbEntity)
}