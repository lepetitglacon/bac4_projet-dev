package engine.entity.interfaces

import engine.GameEngine
import engine.maths.Vector2

interface Movable : Drawable {
    var velocity: Vector2
    var speed: Int

    fun move()

    fun moveFromKeyboard() {
        val vector = GameEngine.input.userInputVector.clone().normalize()
        velocity += vector * speed
        startPosition += velocity
        if (velocity.x != 0.0) {
            velocity.x = (velocity.x * 0.5).toInt().toDouble()
        }
        if (velocity.y != 0.0) {
            velocity.y = (velocity.y * 0.5).toInt().toDouble()
        }
    }
}