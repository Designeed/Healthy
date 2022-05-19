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
                Journal(
                    Food(
                        it.foodTitle,
                        it.protein,
                        it.fats,
                        it.carbs,
                        it.calories),
                    it.date)
            }.sortedByDescending {
                it.date
            }
        }
    }

    override suspend fun getJournalId(title: String, date: String): Long = journalDao.getJournalId(title, date)

    override suspend fun deleteJournalNoteById(param: Long) = journalDao.deleteJournalNoteById(param)

    override suspend fun addJournalNote(journal: Journal) =
        journalDao.addJournalNote(JournalDbEntity(
            0,
            journal.food.title,
            journal.food.protein,
            journal.food.fats,
            journal.food.carbs,
            journal.food.calories,
            journal.date))

}