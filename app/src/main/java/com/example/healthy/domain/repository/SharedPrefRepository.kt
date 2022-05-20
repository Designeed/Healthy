package com.example.healthy.domain.repository

import com.example.healthy.domain.util.Themes

interface SharedPrefRepository {
    suspend fun saveThemeSetting(param: Themes)
    suspend fun getThemesSetting() : Themes
}