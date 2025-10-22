package com.unihandassistant.domain.usecases

import com.unihandassistant.domain.models.ImageState

class ZoomUseCase {
    
    fun zoomIn(currentState: ImageState): ImageState {
        val newZoomLevel = (currentState.zoomLevel * 1.2f).coerceAtMost(5.0f)
        return currentState.copy(zoomLevel = newZoomLevel)
    }
    
    fun zoomOut(currentState: ImageState): ImageState {
        val newZoomLevel = (currentState.zoomLevel / 1.2f).coerceAtLeast(0.1f)
        return currentState.copy(zoomLevel = newZoomLevel)
    }
    
    fun resetZoom(currentState: ImageState): ImageState {
        return currentState.copy(zoomLevel = 1.0f)
    }
}
