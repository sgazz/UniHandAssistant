package com.unihandassistant.domain.usecases

import com.unihandassistant.domain.models.ImageState

class ResetUseCase {
    
    fun resetImage(currentState: ImageState): ImageState {
        return currentState.copy(
            zoomLevel = 1.0f,
            rotation = 0.0f,
            positionX = 0.0f,
            positionY = 0.0f,
            isMoveMode = false
        )
    }
}
