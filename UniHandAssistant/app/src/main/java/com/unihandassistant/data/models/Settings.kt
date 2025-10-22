package com.unihandassistant.data.models

data class Settings(
    val position: Position = Position.RIGHT,
    val sensitivity: Sensitivity = Sensitivity.MEDIUM,
    val speed: Speed = Speed.NORMAL,
    val combination: Combination = Combination.STANDARD
) {
    enum class Position {
        LEFT, RIGHT
    }
    
    enum class Sensitivity {
        LOW, MEDIUM, HIGH
    }
    
    enum class Speed {
        SLOW, NORMAL, FAST
    }
    
    enum class Combination {
        STANDARD, OPPOSITE
    }
}
