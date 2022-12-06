package engine.entities.interfaces

import engine.GameEngine
import engine.maths.Vector2

interface Movable : Drawable {
    var velocity: Vector2
    var speed: Double

    fun move()

    fun setDrawingPositionFromPosition() {
        drawingPosition.x = position.x - width/2
        drawingPosition.y = position.y - height/2
    }

    fun moveFromKeyboard() {
        val vector = GameEngine.input.userInputVector.clone().normalize()
        velocity += vector * speed
        position += velocity
        if (velocity.x != 0.0) {
            velocity.x = (velocity.x * 0.5)
        }
        if (velocity.y != 0.0) {
            velocity.y = (velocity.y * 0.5)
        }
    }

    fun moveTo(v: Vector2) {
        position.translateTo(v, speed)
    }
}