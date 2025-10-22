package com.unihandassistant.data.repository

import android.content.Context
import android.net.Uri
import com.unihandassistant.domain.models.ImageState

class ImageRepository(private val context: Context) {
    
    fun importImage(uri: String): ImageState {
        return ImageState(
            imageUri = uri,
            zoomLevel = 1.0f,
            rotation = 0.0f,
            positionX = 0.0f,
            positionY = 0.0f,
            isMoveMode = false
        )
    }
    
    fun updateImageState(currentState: ImageState, newState: ImageState): ImageState {
        return currentState.copy(
            zoomLevel = newState.zoomLevel,
            rotation = newState.rotation,
            positionX = newState.positionX,
            positionY = newState.positionY,
            isMoveMode = newState.isMoveMode
        )
    }
    
    fun resetImageState(imageUri: String): ImageState {
        return ImageState(
            imageUri = imageUri,
            zoomLevel = 1.0f,
            rotation = 0.0f,
            positionX = 0.0f,
            positionY = 0.0f,
            isMoveMode = false
        )
    }
}
