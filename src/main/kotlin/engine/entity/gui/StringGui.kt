package engine.entity.gui

import engine.entity.Entity
import engine.entity.MovableEntity
import engine.entity.enums.DrawablePosition
import java.awt.Color
import java.awt.Graphics2D
import kotlin.String

class StringGui(
    val string: String,
    override var drawingPosition: DrawablePosition,
    override var drawingRelative: Entity?,
    val color: Color?,
    val bgColor: Color?
) : MovableEntity() {

    init {

    }

    override fun draw(g: Graphics2D) {
        if (bgColor !== null) g.color = bgColor
        g.fillRect(getDrawingPosition().x, getDrawingPosition().y, width, height)

        if (color !== null) g.color = color
        g.drawString(string, getDrawingPosition().x, getDrawingPosition().y)
    }

    override fun move() {

    }
}