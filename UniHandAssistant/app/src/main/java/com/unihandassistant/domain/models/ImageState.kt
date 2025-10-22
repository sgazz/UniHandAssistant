package com.unihandassistant.domain.models

data class ImageState(
    val imageUri: String? = null,
    val zoomLevel: Float = 1.0f,
    val rotation: Float = 0.0f,
    val positionX: Float = 0.0f,
    val positionY: Float = 0.0f,
    val isMoveMode: Boolean = false
)
