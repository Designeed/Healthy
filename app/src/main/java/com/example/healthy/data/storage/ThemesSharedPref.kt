package com.example.healthy.data.storage

import android.content.Context
import com.example.healthy.domain.util.Themes

class ThemesSharedPref(context: Context) {
    private val prefs = context.getSharedPreferences(Constants.SETTINGS, Context.MODE_PRIVATE)

    var theme: Int
        get() = prefs.getInt(Constants.THEMES_KEY, Themes.Auto.value)
        set(id) {
            prefs.edit().putInt(Constants.THEMES_KEY, id).apply()
        }

    object Constants {
        const val SETTINGS = "SettingsName"
        const val THEMES_KEY = "ThemesKey"
    }
}
