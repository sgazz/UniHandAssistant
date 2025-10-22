package com.unihandassistant.utils

import android.content.Context
import android.view.View
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityManager
import com.unihandassistant.data.models.Settings

object AccessibilityUtils {
    
    fun setupAccessibility(view: View, settings: Settings) {
        // Enable accessibility features
        view.isAccessibilityFocused = true
        view.isFocusable = true
        view.isClickable = true
        
        // Set content description based on settings
        val positionText = when (settings.position) {
            Settings.Position.LEFT -> "Left thumb zone"
            Settings.Position.RIGHT -> "Right thumb zone"
        }
        
        view.contentDescription = positionText
    }
    
    fun announceAccessibilityEvent(context: Context, message: String) {
        val accessibilityManager = context.getSystemService(Context.ACCESSIBILITY_SERVICE) as AccessibilityManager
        
        if (accessibilityManager.isEnabled) {
            val event = AccessibilityEvent.obtain()
            event.eventType = AccessibilityEvent.TYPE_ANNOUNCEMENT
            event.text.add(message)
            accessibilityManager.sendAccessibilityEvent(event)
        }
    }
    
    fun setupAccessibilityForRadialMenu(view: View, settings: Settings) {
        // Setup accessibility for radial menu
        view.isAccessibilityFocused = true
        view.isFocusable = true
        view.isClickable = true
        
        val positionText = when (settings.position) {
            Settings.Position.LEFT -> "Radial menu for left thumb"
            Settings.Position.RIGHT -> "Radial menu for right thumb"
        }
        
        view.contentDescription = positionText
    }
    
    fun setupAccessibilityForSettings(view: View, settings: Settings) {
        // Setup accessibility for settings menu
        view.isAccessibilityFocused = true
        view.isFocusable = true
        view.isClickable = true
        
        val positionText = when (settings.position) {
            Settings.Position.LEFT -> "Settings menu for left thumb"
            Settings.Position.RIGHT -> "Settings menu for right thumb"
        }
        
        view.contentDescription = positionText
    }
    
    fun announceSettingsChange(context: Context, setting: String, value: String) {
        val message = "Setting changed: $setting to $value"
        announceAccessibilityEvent(context, message)
    }
    
    fun announceImageOperation(context: Context, operation: String) {
        val message = "Image operation: $operation"
        announceAccessibilityEvent(context, message)
    }
    
    fun announceMoveModeChange(context: Context, isEnabled: Boolean) {
        val message = if (isEnabled) "Move mode enabled" else "Move mode disabled"
        announceAccessibilityEvent(context, message)
    }
}
