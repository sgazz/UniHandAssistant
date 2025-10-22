package com.unihandassistant.domain.usecases

import com.unihandassistant.domain.models.ImageState

class RotationUseCase {
    
    fun rotateLeft(currentState: ImageState): ImageState {
        val newRotation = (currentState.rotation - 15f) % 360f
        return currentState.copy(rotation = newRotation)
    }
    
    fun rotateRight(currentState: ImageState): ImageState {
        val newRotation = (currentState.rotation + 15f) % 360f
        return currentState.copy(rotation = newRotation)
    }
    
    fun resetRotation(currentState: ImageState): ImageState {
        return currentState.copy(rotation = 0f)
    }
}
