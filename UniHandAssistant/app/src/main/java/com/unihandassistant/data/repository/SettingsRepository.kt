package com.unihandassistant.data.repository

import android.content.Context
import android.content.SharedPreferences
import com.unihandassistant.data.models.Settings

class SettingsRepository(context: Context) {
    
    private val prefs: SharedPreferences = context.getSharedPreferences("unihand_settings", Context.MODE_PRIVATE)
    
    fun saveSettings(settings: Settings) {
        prefs.edit().apply {
            putString("position", settings.position.name)
            putString("sensitivity", settings.sensitivity.name)
            putString("speed", settings.speed.name)
            putString("combination", settings.combination.name)
            apply()
        }
    }
    
    fun loadSettings(): Settings {
        return Settings(
            position = Settings.Position.valueOf(
                prefs.getString("position", Settings.Position.RIGHT.name) ?: Settings.Position.RIGHT.name
            ),
            sensitivity = Settings.Sensitivity.valueOf(
                prefs.getString("sensitivity", Settings.Sensitivity.MEDIUM.name) ?: Settings.Sensitivity.MEDIUM.name
            ),
            speed = Settings.Speed.valueOf(
                prefs.getString("speed", Settings.Speed.NORMAL.name) ?: Settings.Speed.NORMAL.name
            ),
            combination = Settings.Combination.valueOf(
                prefs.getString("combination", Settings.Combination.STANDARD.name) ?: Settings.Combination.STANDARD.name
            )
        )
    }
}
