package com.unihandassistant.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.unihandassistant.data.models.Settings
import com.unihandassistant.data.repository.SettingsRepository

class SettingsViewModel(private val context: Context) : ViewModel() {
    
    private val settingsRepository = SettingsRepository(context)
    
    private val _settings = MutableLiveData<Settings>()
    val settings: LiveData<Settings> = _settings
    
    init {
        loadSettings()
    }
    
    fun loadSettings() {
        _settings.value = settingsRepository.loadSettings()
    }
    
    fun updatePosition(position: Settings.Position) {
        _settings.value?.let { currentSettings ->
            val newSettings = currentSettings.copy(position = position)
            _settings.value = newSettings
            settingsRepository.saveSettings(newSettings)
        }
    }
    
    fun updateSensitivity(sensitivity: Settings.Sensitivity) {
        _settings.value?.let { currentSettings ->
            val newSettings = currentSettings.copy(sensitivity = sensitivity)
            _settings.value = newSettings
            settingsRepository.saveSettings(newSettings)
        }
    }
    
    fun updateSpeed(speed: Settings.Speed) {
        _settings.value?.let { currentSettings ->
            val newSettings = currentSettings.copy(speed = speed)
            _settings.value = newSettings
            settingsRepository.saveSettings(newSettings)
        }
    }
    
    fun updateCombination(combination: Settings.Combination) {
        _settings.value?.let { currentSettings ->
            val newSettings = currentSettings.copy(combination = combination)
            _settings.value = newSettings
            settingsRepository.saveSettings(newSettings)
        }
    }
}
