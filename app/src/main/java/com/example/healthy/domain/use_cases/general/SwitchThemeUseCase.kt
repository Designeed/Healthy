package com.example.healthy.domain.use_cases.general

import androidx.appcompat.app.AppCompatDelegate.*
import com.example.healthy.domain.util.Themes

class SwitchThemeUseCase {
    fun execute(theme: Themes) {
        when (theme) {
            Themes.Light -> setDefaultNightMode(MODE_NIGHT_NO)

            Themes.Dark -> setDefaultNightMode(MODE_NIGHT_YES)

            Themes.Auto -> setDefaultNightMode(MODE_NIGHT_FOLLOW_SYSTEM)
        }
    }
}