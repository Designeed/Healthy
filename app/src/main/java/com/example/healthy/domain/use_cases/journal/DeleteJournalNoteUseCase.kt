package com.example.healthy.domain.use_cases.journal

import com.example.healthy.domain.repository.JournalRepository

class DeleteJournalNoteUseCase {
    suspend fun execute(id: Long, repository: JournalRepository) = repository.deleteJournalNoteById(id)
}