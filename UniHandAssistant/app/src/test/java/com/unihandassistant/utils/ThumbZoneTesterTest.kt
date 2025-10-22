package com.unihandassistant.utils

import android.content.Context
import android.view.View
import com.unihandassistant.data.models.Settings
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ThumbZoneTesterTest {
    
    @Mock
    private lateinit var context: Context
    
    @Mock
    private lateinit var view: View
    
    private lateinit var tester: ThumbZoneTester
    private lateinit var testSettings: Settings
    
    @Before
    fun setUp() {
        tester = ThumbZoneTester(context)
        testSettings = Settings(
            position = Settings.Position.RIGHT,
            sensitivity = Settings.Sensitivity.MEDIUM,
            speed = Settings.Speed.NORMAL,
            combination = Settings.Combination.STANDARD
        )
    }
    
    @Test
    fun `startTesting should set up touch listener`() {
        // When
        tester.startTesting(view, testSettings)
        
        // Then
        verify(view).setOnTouchListener(any())
    }
    
    @Test
    fun `stopTesting should remove touch listener`() {
        // Given
        tester.startTesting(view, testSettings)
        
        // When
        tester.stopTesting(view)
        
        // Then
        verify(view).setOnTouchListener(null)
    }
    
    @Test
    fun `getTestResults should return empty list initially`() {
        // When
        val results = tester.getTestResults()
        
        // Then
        assert(results.isEmpty())
    }
    
    @Test
    fun `getThumbZoneAccuracy should return 0 when no tests`() {
        // When
        val accuracy = tester.getThumbZoneAccuracy()
        
        // Then
        assert(accuracy == 0f)
    }
    
    @Test
    fun `getAverageTouchPosition should return 0,0 when no tests`() {
        // When
        val position = tester.getAverageTouchPosition()
        
        // Then
        assert(position.first == 0f)
        assert(position.second == 0f)
    }
    
    @Test
    fun `clearResults should clear test results`() {
        // Given
        tester.clearResults()
        
        // When
        val results = tester.getTestResults()
        
        // Then
        assert(results.isEmpty())
    }
}
