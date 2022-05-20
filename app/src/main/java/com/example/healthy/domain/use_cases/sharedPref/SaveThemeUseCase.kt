package com.example.healthy.domain.use_cases.sharedPref

import com.example.healthy.domain.repository.SharedPrefRepository
import com.example.healthy.domain.util.Themes

class SaveThemeUseCase {
    suspend fun execute(param: Themes, repository: SharedPrefRepository) = repository.saveThemeSetting(param)
}