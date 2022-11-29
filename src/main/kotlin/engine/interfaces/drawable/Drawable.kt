package engine.interfaces.drawable

import engine.Vector2
import engine.enums.DrawablePosition
import java.awt.Color
import java.awt.Graphics2D

interface Drawable {
    val position: Vector2
    val width: Int
    val height: Int
    val color: Color

    fun draw(g: Graphics2D)

    fun getDrawingPosition() = Vector2((position.x - (width/2)), position.y - (height/2))
}