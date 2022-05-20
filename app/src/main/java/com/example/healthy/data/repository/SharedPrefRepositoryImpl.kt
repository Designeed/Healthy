package com.example.healthy.data.repository

import android.content.Context
import com.example.healthy.data.storage.ThemesSharedPref
import com.example.healthy.domain.repository.SharedPrefRepository
import com.example.healthy.domain.util.Themes

class SharedPrefRepositoryImpl(private val context: Context) : SharedPrefRepository {
    override suspend fun saveThemeSetting(param: Themes) {
        ThemesSharedPref(context).theme = param.value
    }

    override suspend fun getThemesSetting(): Themes {
        val value = ThemesSharedPref(context).theme
        return Themes.from(value)
    }
}
