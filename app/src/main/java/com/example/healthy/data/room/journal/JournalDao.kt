package com.example.healthy.data.room.journal

import androidx.room.Dao
import androidx.room.Query
import com.example.healthy.data.room.journal.entity.JournalDbEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface JournalDao {
    @Query("SELECT * FROM journal")
    fun getAllJournal(): Flow<List<JournalDbEntity>>

    @Query("DELETE FROM journal WHERE id = :param")
    suspend fun deleteJournalNoteById(param: Long)
}