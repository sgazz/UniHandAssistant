package com.unihandassistant.ui.widgets

import android.content.Context
import android.graphics.Matrix
import android.util.AttributeSet
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.unihandassistant.domain.models.ImageState

class ImageViewer @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ImageView(context, attrs, defStyleAttr) {
    
    private var imageState: ImageState? = null
    private val matrix = Matrix()
    
    fun setImageState(state: ImageState) {
        this.imageState = state
        updateImage()
    }
    
    private fun updateImage() {
        imageState?.let { state ->
            // Load image
            state.imageUri?.let { uri ->
                Glide.with(context)
                    .load(uri)
                    .into(this)
            }
            
            // Apply transformations
            applyTransformations(state)
        }
    }
    
    private fun applyTransformations(state: ImageState) {
        matrix.reset()
        
        // Apply zoom
        matrix.postScale(state.zoomLevel, state.zoomLevel)
        
        // Apply rotation
        matrix.postRotate(state.rotation)
        
        // Apply position
        matrix.postTranslate(state.positionX, state.positionY)
        
        imageMatrix = matrix
    }
    
    fun updateZoom(zoomLevel: Float) {
        imageState = imageState?.copy(zoomLevel = zoomLevel)
        updateImage()
    }
    
    fun updateRotation(rotation: Float) {
        imageState = imageState?.copy(rotation = rotation)
        updateImage()
    }
    
    fun updatePosition(x: Float, y: Float) {
        imageState = imageState?.copy(positionX = x, positionY = y)
        updateImage()
    }
    
    fun reset() {
        imageState = imageState?.let { state ->
            state.copy(
                zoomLevel = 1.0f,
                rotation = 0.0f,
                positionX = 0.0f,
                positionY = 0.0f,
                isMoveMode = false
            )
        }
        updateImage()
    }
}
