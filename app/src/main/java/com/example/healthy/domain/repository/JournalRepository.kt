package com.example.healthy.domain.repository

import com.example.healthy.domain.model.Food
import com.example.healthy.domain.model.Journal
import kotlinx.coroutines.flow.Flow
import java.util.*

interface JournalRepository {

    fun getAllJournal(): Flow<List<Journal>>

    suspend fun getJournalId(foodId: Long, date: String): Long

    suspend fun deleteJournalNoteById(param: Long)

    suspend fun updateJournalNote(foodId: Long, food: Food, journal: Journal)

    suspend fun addJournalNote(foodId: Long, food: Food, journal: Journal)
}