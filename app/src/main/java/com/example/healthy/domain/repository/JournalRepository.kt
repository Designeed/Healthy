package com.example.healthy.domain.repository

import com.example.healthy.domain.model.Journal
import kotlinx.coroutines.flow.Flow
import java.util.*

interface JournalRepository {

    fun getAllJournal(): Flow<List<Journal>>

    suspend fun getJournalId(foodId: Long, date: Date): Long

    suspend fun deleteJournalNoteById(param: Long)

    suspend fun updateJournalNote(foodId: Long, journal: Journal)

    fun addJournalNote(foodId: Long, journal: Journal)
}