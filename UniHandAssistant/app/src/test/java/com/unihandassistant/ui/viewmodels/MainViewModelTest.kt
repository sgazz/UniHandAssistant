package com.unihandassistant.ui.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.unihandassistant.domain.models.ImageState
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {
    
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    
    @Mock
    private lateinit var imageStateObserver: Observer<ImageState>
    
    @Mock
    private lateinit var radialMenuVisibilityObserver: Observer<Boolean>
    
    private lateinit var viewModel: MainViewModel
    
    @Before
    fun setUp() {
        viewModel = MainViewModel()
        viewModel.imageState.observeForever(imageStateObserver)
        viewModel.isRadialMenuVisible.observeForever(radialMenuVisibilityObserver)
    }
    
    @Test
    fun `loadImage should update image state`() {
        // Given
        val imageUri = "content://media/external/images/media/123"
        
        // When
        viewModel.loadImage(imageUri)
        
        // Then
        verify(imageStateObserver).onChanged(any())
    }
    
    @Test
    fun `toggleRadialMenu should toggle visibility`() {
        // When
        viewModel.toggleRadialMenu()
        
        // Then
        verify(radialMenuVisibilityObserver).onChanged(true)
        
        // When
        viewModel.toggleRadialMenu()
        
        // Then
        verify(radialMenuVisibilityObserver).onChanged(false)
    }
    
    @Test
    fun `zoomIn should update image state`() {
        // Given
        viewModel.loadImage("test_uri")
        
        // When
        viewModel.zoomIn()
        
        // Then
        verify(imageStateObserver, atLeast(2)).onChanged(any())
    }
    
    @Test
    fun `zoomOut should update image state`() {
        // Given
        viewModel.loadImage("test_uri")
        
        // When
        viewModel.zoomOut()
        
        // Then
        verify(imageStateObserver, atLeast(2)).onChanged(any())
    }
    
    @Test
    fun `rotateLeft should update image state`() {
        // Given
        viewModel.loadImage("test_uri")
        
        // When
        viewModel.rotateLeft()
        
        // Then
        verify(imageStateObserver, atLeast(2)).onChanged(any())
    }
    
    @Test
    fun `rotateRight should update image state`() {
        // Given
        viewModel.loadImage("test_uri")
        
        // When
        viewModel.rotateRight()
        
        // Then
        verify(imageStateObserver, atLeast(2)).onChanged(any())
    }
    
    @Test
    fun `resetImage should reset image state`() {
        // Given
        viewModel.loadImage("test_uri")
        
        // When
        viewModel.resetImage()
        
        // Then
        verify(imageStateObserver, atLeast(2)).onChanged(any())
    }
    
    @Test
    fun `toggleMoveMode should toggle move mode`() {
        // Given
        viewModel.loadImage("test_uri")
        
        // When
        viewModel.toggleMoveMode()
        
        // Then
        verify(imageStateObserver, atLeast(2)).onChanged(any())
    }
}
