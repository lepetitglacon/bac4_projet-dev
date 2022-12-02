package engine.entity

import engine.entity.interfaces.Movable
import engine.maths.Vector2

abstract class MovableEntity : Entity(), Movable {
    override var drawingPosition: Vector2 = Vector2()
    override var position: Vector2 = Vector2()
    override var velocity: Vector2 = Vector2()
    override var speed: Double = 1.5
}