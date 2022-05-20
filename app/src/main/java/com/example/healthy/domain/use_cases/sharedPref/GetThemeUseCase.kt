package com.example.healthy.domain.use_cases.sharedPref

import com.example.healthy.domain.repository.SharedPrefRepository
import com.example.healthy.domain.util.Themes

class GetThemeUseCase {
    suspend fun execute(repository: SharedPrefRepository) : Themes = repository.getThemesSetting()
}