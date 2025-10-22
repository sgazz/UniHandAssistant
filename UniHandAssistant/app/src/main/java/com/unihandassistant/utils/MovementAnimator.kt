package com.unihandassistant.utils

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.view.View
import com.unihandassistant.data.models.Settings

object MovementAnimator {
    
    fun animateImageMovement(
        view: View,
        fromX: Float, fromY: Float,
        toX: Float, toY: Float,
        settings: Settings,
        onUpdate: (Float, Float) -> Unit
    ) {
        val duration = getAnimationDuration(settings.speed)
        
        val animatorX = ObjectAnimator.ofFloat(fromX, toX)
        val animatorY = ObjectAnimator.ofFloat(fromY, toY)
        
        animatorX.duration = duration
        animatorY.duration = duration
        
        animatorX.addUpdateListener { animation ->
            val currentX = animation.animatedValue as Float
            val currentY = animatorY.animatedValue as Float
            onUpdate(currentX, currentY)
        }
        
        animatorY.addUpdateListener { animation ->
            val currentY = animation.animatedValue as Float
            val currentX = animatorX.animatedValue as Float
            onUpdate(currentX, currentY)
        }
        
        animatorX.start()
        animatorY.start()
    }
    
    private fun getAnimationDuration(speed: Settings.Speed): Long {
        return when (speed) {
            Settings.Speed.SLOW -> 500L
            Settings.Speed.NORMAL -> 300L
            Settings.Speed.FAST -> 150L
        }
    }
    
    fun animateSmoothTransition(
        view: View,
        fromX: Float, fromY: Float,
        toX: Float, toY: Float,
        settings: Settings,
        onComplete: () -> Unit
    ) {
        val duration = getAnimationDuration(settings.speed)
        
        val animatorX = ObjectAnimator.ofFloat(fromX, toX)
        val animatorY = ObjectAnimator.ofFloat(fromY, toY)
        
        animatorX.duration = duration
        animatorY.duration = duration
        
        animatorX.addUpdateListener { animation ->
            val currentX = animation.animatedValue as Float
            val currentY = animatorY.animatedValue as Float
            view.translationX = currentX
            view.translationY = currentY
        }
        
        animatorY.addUpdateListener { animation ->
            val currentY = animation.animatedValue as Float
            val currentX = animatorX.animatedValue as Float
            view.translationX = currentX
            view.translationY = currentY
        }
        
        animatorX.addListener(object : android.animation.AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: android.animation.Animator) {
                onComplete()
            }
        })
        
        animatorX.start()
        animatorY.start()
    }
}
