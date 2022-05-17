package com.example.healthy.data.repository

import com.example.healthy.data.room.journal.JournalDao
import com.example.healthy.data.room.journal.entity.JournalDbEntity
import com.example.healthy.domain.model.Journal
import com.example.healthy.domain.repository.JournalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.*

class JournalRepositoryImpl(private val journalDao: JournalDao) : JournalRepository {
    override fun getAllJournal(): Flow<List<Journal>> {
        return journalDao.getAllJournal().map { entities ->
            entities.map {
                val foodEntity = it.key
                val journalEntity = it.value

                Journal(
                    foodEntity.toFoodModel(),
                    journalEntity.date
                )
            }
        }
    }

    override suspend fun getJournalId(foodId: Long, date: Date): Long = journalDao.getJournalId(foodId, date)

    override suspend fun updateJournalNote(foodId: Long, journal: Journal) = journalDao.updateJournalNote(JournalDbEntity(0, foodId, journal.date))

    override suspend fun deleteJournalNoteById(param: Long) = journalDao.deleteJournalNoteById(param)

    override fun addJournalNote(foodId: Long, journal: Journal) = journalDao.addJournalNote(JournalDbEntity(0, foodId, journal.date))

}