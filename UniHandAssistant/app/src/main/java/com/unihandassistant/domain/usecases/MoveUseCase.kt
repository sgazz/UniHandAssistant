package com.unihandassistant.domain.usecases

import com.unihandassistant.data.models.Settings
import com.unihandassistant.domain.models.ImageState

class MoveUseCase {
    
    fun calculateMovement(
        gyroX: Float, gyroY: Float, gyroZ: Float,
        accelX: Float, accelY: Float, accelZ: Float,
        currentState: ImageState,
        settings: Settings
    ): ImageState {
        
        // Calculate movement based on sensor data
        val moveX = calculateXMovement(gyroX, accelX, settings)
        val moveY = calculateYMovement(gyroY, accelY, settings)
        
        // Apply sensitivity
        val sensitivityMultiplier = getSensitivityMultiplier(settings.sensitivity)
        val adjustedMoveX = moveX * sensitivityMultiplier
        val adjustedMoveY = moveY * sensitivityMultiplier
        
        // Apply combination logic
        val (finalMoveX, finalMoveY) = applyCombinationLogic(adjustedMoveX, adjustedMoveY, settings.combination)
        
        return currentState.copy(
            positionX = currentState.positionX + finalMoveX,
            positionY = currentState.positionY + finalMoveY
        )
    }
    
    private fun calculateXMovement(gyroX: Float, accelX: Float, settings: Settings): Float {
        // Combine gyroscope and accelerometer data for X movement
        val gyroWeight = 0.7f
        val accelWeight = 0.3f
        
        return (gyroX * gyroWeight + accelX * accelWeight) * 10f
    }
    
    private fun calculateYMovement(gyroY: Float, accelY: Float, settings: Settings): Float {
        // Combine gyroscope and accelerometer data for Y movement
        val gyroWeight = 0.7f
        val accelWeight = 0.3f
        
        return (gyroY * gyroWeight + accelY * accelWeight) * 10f
    }
    
    private fun getSensitivityMultiplier(sensitivity: Settings.Sensitivity): Float {
        return when (sensitivity) {
            Settings.Sensitivity.LOW -> 0.5f
            Settings.Sensitivity.MEDIUM -> 1.0f
            Settings.Sensitivity.HIGH -> 2.0f
        }
    }
    
    private fun applyCombinationLogic(moveX: Float, moveY: Float, combination: Settings.Combination): Pair<Float, Float> {
        return when (combination) {
            Settings.Combination.STANDARD -> Pair(moveX, moveY)
            Settings.Combination.OPPOSITE -> Pair(-moveX, -moveY)
        }
    }
}
