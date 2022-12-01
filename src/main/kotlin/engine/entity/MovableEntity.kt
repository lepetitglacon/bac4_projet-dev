package engine.entity

import engine.entity.interfaces.Movable
import engine.maths.Vector2

abstract class MovableEntity : Entity(), Movable {
    override var velocity: Vector2 = Vector2()
    override var speed: Int = 5
}