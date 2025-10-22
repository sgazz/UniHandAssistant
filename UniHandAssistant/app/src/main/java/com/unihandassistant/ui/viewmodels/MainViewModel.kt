package com.unihandassistant.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.unihandassistant.data.repository.ImageRepository
import com.unihandassistant.domain.models.ImageState
import com.unihandassistant.domain.usecases.ZoomUseCase
import com.unihandassistant.domain.usecases.RotationUseCase
import com.unihandassistant.domain.usecases.ResetUseCase

class MainViewModel : ViewModel() {
    
    private val imageRepository = ImageRepository(android.app.Application())
    private val zoomUseCase = ZoomUseCase()
    private val rotationUseCase = RotationUseCase()
    private val resetUseCase = ResetUseCase()
    
    private val _imageState = MutableLiveData<ImageState>()
    val imageState: LiveData<ImageState> = _imageState
    
    private val _isRadialMenuVisible = MutableLiveData<Boolean>()
    val isRadialMenuVisible: LiveData<Boolean> = _isRadialMenuVisible
    
    fun loadImage(imageUri: String) {
        val newState = imageRepository.importImage(imageUri)
        _imageState.value = newState
    }
    
    fun toggleRadialMenu() {
        _isRadialMenuVisible.value = !(_isRadialMenuVisible.value ?: false)
    }
    
    fun zoomIn() {
        _imageState.value?.let { currentState ->
            val newState = zoomUseCase.zoomIn(currentState)
            _imageState.value = newState
        }
    }
    
    fun zoomOut() {
        _imageState.value?.let { currentState ->
            val newState = zoomUseCase.zoomOut(currentState)
            _imageState.value = newState
        }
    }
    
    fun rotateLeft() {
        _imageState.value?.let { currentState ->
            val newState = rotationUseCase.rotateLeft(currentState)
            _imageState.value = newState
        }
    }
    
    fun rotateRight() {
        _imageState.value?.let { currentState ->
            val newState = rotationUseCase.rotateRight(currentState)
            _imageState.value = newState
        }
    }
    
    fun resetImage() {
        _imageState.value?.let { currentState ->
            val newState = resetUseCase.resetImage(currentState)
            _imageState.value = newState
        }
    }
    
    fun openSettings() {
        // TODO: Implement settings functionality
    }
    
    fun toggleMoveMode() {
        _imageState.value?.let { currentState ->
            val newState = currentState.copy(isMoveMode = !currentState.isMoveMode)
            _imageState.value = newState
        }
    }
}
