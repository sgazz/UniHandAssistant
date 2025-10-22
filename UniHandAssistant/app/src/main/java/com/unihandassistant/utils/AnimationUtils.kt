package com.unihandassistant.utils

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.view.animation.OvershootInterpolator

object AnimationUtils {
    
    fun animateRadialMenuIn(views: List<View>, delay: Long = 50) {
        views.forEachIndexed { index, view ->
            view.alpha = 0f
            view.scaleX = 0f
            view.scaleY = 0f
            
            val animatorSet = AnimatorSet()
            val alphaAnimator = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f)
            val scaleXAnimator = ObjectAnimator.ofFloat(view, "scaleX", 0f, 1f)
            val scaleYAnimator = ObjectAnimator.ofFloat(view, "scaleY", 0f, 1f)
            
            alphaAnimator.duration = 300
            scaleXAnimator.duration = 300
            scaleYAnimator.duration = 300
            
            alphaAnimator.startDelay = index * delay
            scaleXAnimator.startDelay = index * delay
            scaleYAnimator.startDelay = index * delay
            
            alphaAnimator.interpolator = OvershootInterpolator(1.2f)
            scaleXAnimator.interpolator = OvershootInterpolator(1.2f)
            scaleYAnimator.interpolator = OvershootInterpolator(1.2f)
            
            animatorSet.playTogether(alphaAnimator, scaleXAnimator, scaleYAnimator)
            animatorSet.start()
        }
    }
    
    fun animateRadialMenuOut(views: List<View>, onComplete: () -> Unit) {
        val animatorSet = AnimatorSet()
        val animators = mutableListOf<ObjectAnimator>()
        
        views.forEach { view ->
            val alphaAnimator = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f)
            val scaleXAnimator = ObjectAnimator.ofFloat(view, "scaleX", 1f, 0f)
            val scaleYAnimator = ObjectAnimator.ofFloat(view, "scaleY", 1f, 0f)
            
            alphaAnimator.duration = 200
            scaleXAnimator.duration = 200
            scaleYAnimator.duration = 200
            
            alphaAnimator.interpolator = DecelerateInterpolator()
            scaleXAnimator.interpolator = DecelerateInterpolator()
            scaleYAnimator.interpolator = DecelerateInterpolator()
            
            animators.addAll(listOf(alphaAnimator, scaleXAnimator, scaleYAnimator))
        }
        
        animatorSet.playTogether(animators as Collection<android.animation.Animator>)
        animatorSet.addListener(object : android.animation.AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: android.animation.Animator) {
                onComplete()
            }
        })
        animatorSet.start()
    }
    
    fun animateButtonPress(view: View, onComplete: () -> Unit) {
        val animatorSet = AnimatorSet()
        val scaleDown = ObjectAnimator.ofFloat(view, "scaleX", 1f, 0.9f)
        val scaleDownY = ObjectAnimator.ofFloat(view, "scaleY", 1f, 0.9f)
        val scaleUp = ObjectAnimator.ofFloat(view, "scaleX", 0.9f, 1f)
        val scaleUpY = ObjectAnimator.ofFloat(view, "scaleY", 0.9f, 1f)
        
        scaleDown.duration = 100
        scaleDownY.duration = 100
        scaleUp.duration = 100
        scaleUpY.duration = 100
        
        animatorSet.playSequentially(
            AnimatorSet().apply { playTogether(scaleDown, scaleDownY) },
            AnimatorSet().apply { playTogether(scaleUp, scaleUpY) }
        )
        
        animatorSet.addListener(object : android.animation.AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: android.animation.Animator) {
                onComplete()
            }
        })
        
        animatorSet.start()
    }
}
