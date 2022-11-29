package engine.interfaces.drawable

import engine.Renderer
import engine.Vector2
import engine.enums.DrawablePosition
import java.awt.Graphics2D

interface DrawableCircle : Drawable {
    val size: Int
}