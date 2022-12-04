package engine.entity.gui

import engine.GameEngine
import engine.entity.Entity
import engine.entity.MovableEntity
import engine.entity.enums.DrawablePosition
import engine.maths.Vector2
import java.awt.Color
import java.awt.Graphics2D
import kotlin.String

class StringGui() : MovableEntity() {
    var string: String = ""
    var color: Color = Color.WHITE
    var bgColor: Color = Color.DARK_GRAY
    override var drawingPositionType: DrawablePosition = DrawablePosition.ABSOLUTE_CENTERED
    override var drawingPositionTypeRelative: Entity? = null

    init {
        position = Vector2((GameEngine.window.WIDTH/2).toDouble(), (GameEngine.window.HEIGHT - 95).toDouble())
    }

    override fun draw(g: Graphics2D) {
        g.color = color
        g.drawString(string, getDrawingPosition().x, getDrawingPosition().y)
    }

    override fun move() {

    }
}