package interfaces

import Vector2

interface PositionInterface {
    var position: Vector2
    var velocity: Vector2
    var angle: Int
    var speed: Int

    fun goTo()
    fun move()
}