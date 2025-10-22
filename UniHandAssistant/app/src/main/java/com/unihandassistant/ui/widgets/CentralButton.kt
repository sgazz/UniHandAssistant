package com.unihandassistant.ui.widgets

import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CentralButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FloatingActionButton(context, attrs, defStyleAttr) {
    
    private var isPressed = false
    private var onToggleListener: (() -> Unit)? = null
    
    init {
        setOnClickListener {
            toggle()
        }
    }
    
    fun setOnToggleListener(listener: () -> Unit) {
        onToggleListener = listener
    }
    
    fun toggle() {
        isPressed = !isPressed
        animatePress()
        onToggleListener?.invoke()
    }
    
    private fun animatePress() {
        val scale = if (isPressed) 0.9f else 1.0f
        val animator = ObjectAnimator.ofFloat(this, "scaleX", scale)
        val animatorY = ObjectAnimator.ofFloat(this, "scaleY", scale)
        
        animator.duration = 100
        animatorY.duration = 100
        
        animator.start()
        animatorY.start()
    }
    
    fun setPressedState(pressed: Boolean) {
        isPressed = pressed
        scaleX = if (pressed) 0.9f else 1.0f
        scaleY = if (pressed) 0.9f else 1.0f
    }
}
