package com.example.healthy.data.repository

import com.example.healthy.data.room.journal.JournalDao
import com.example.healthy.data.room.journal.entity.JournalDbEntity
import com.example.healthy.domain.model.Food
import com.example.healthy.domain.model.Journal
import com.example.healthy.domain.repository.JournalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.text.DateFormat
import java.util.*

class JournalRepositoryImpl(private val journalDao: JournalDao) : JournalRepository {
    override fun getAllJournal(): Flow<List<Journal>> {
        return journalDao.getAllJournal().map { entities ->
            entities.map {
                val journalEntity = it.key
                val foodEntity = it.value
                Journal(
                    foodEntity.toFoodModel(),
                    journalEntity.date
                )
            }.sortedByDescending {
                it.date
            }
        }
    }

    override suspend fun getJournalId(foodId: Long, date: String): Long = journalDao.getJournalId(foodId, date)

    override suspend fun updateJournalNote(foodId: Long, food: Food, journal: Journal) =
        journalDao.updateJournalNote(JournalDbEntity(
            0,
            foodId,
            food.protein,
            food.fats,
            food.carbs,
            food.calories,
            journal.date))

    override suspend fun deleteJournalNoteById(param: Long) = journalDao.deleteJournalNoteById(param)

    override suspend fun addJournalNote(foodId: Long, food: Food, journal: Journal) =
        journalDao.addJournalNote(JournalDbEntity(
            0,
            foodId,
            food.protein,
            food.fats,
            food.carbs,
            food.calories,
            journal.date))

}