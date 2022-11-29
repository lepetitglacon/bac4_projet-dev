package engine.interfaces.drawable.positions

import engine.Vector2
import engine.enums.DrawablePosition

interface PositionRelativeInterface {
    val drawingPosition: DrawablePosition
    val relativeCenter: Vector2?


}