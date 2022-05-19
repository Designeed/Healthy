package com.example.healthy.domain.repository

import com.example.healthy.domain.model.Journal
import kotlinx.coroutines.flow.Flow

interface JournalRepository {

    fun getAllJournal(): Flow<List<Journal>>

    suspend fun getJournalId(title: String, date: String): Long

    suspend fun deleteJournalNoteById(param: Long)

    suspend fun addJournalNote(journal: Journal)
}