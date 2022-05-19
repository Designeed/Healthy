package com.example.healthy.data.room.journal

import androidx.room.*
import com.example.healthy.data.room.food.entity.FoodDbEntity
import com.example.healthy.data.room.journal.entity.JournalDbEntity
import com.example.healthy.domain.model.Food
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface JournalDao {
    @Query("SELECT * FROM journal")
    fun getAllJournal(): Flow<List<JournalDbEntity>>

    @Query("SELECT id FROM journal WHERE foodTitle = :foodTitle AND date = :date")
    suspend fun getJournalId(foodTitle: String, date: String): Long

    @Query("DELETE FROM journal WHERE id = :param")
    suspend fun deleteJournalNoteById(param: Long)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addJournalNote(journal: JournalDbEntity)
}