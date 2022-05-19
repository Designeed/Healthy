package com.example.healthy.domain.use_cases.journal

import com.example.healthy.domain.repository.JournalRepository

class GetJournalNoteIdUseCase {
    suspend fun execute(title: String, date: String, repository: JournalRepository): Long = repository.getJournalId(title, date)
}