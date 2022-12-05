package engine.entity.gui.component

import engine.entity.MovableEntity
import engine.entity.enums.DrawablePosition
import java.awt.Color
import java.awt.Graphics2D

class BarGui() : MovableEntity() {
    val PADDING = 2

    var filled: Int = 100
    var maxFilled: Int = 100
    var color: Color = Color(44, 248, 11)
    var completingColor: Color = Color(122, 48, 7)
    var backgroundColor: Color = Color(217,217,217, 230)

    init {
        drawingPositionType = DrawablePosition.ABSOLUTE_CENTERED
    }

    fun getFilledWidth(): Int {
        val centpourcent = width - 2 * PADDING
        return centpourcent * filled / maxFilled
    }

    fun getNonFilledWidth(): Int {
        return  width - getFilledWidth()
    }

    override fun draw(g: Graphics2D) {
        g.color = backgroundColor
        g.fillRect(getDrawingPosition().x, getDrawingPosition().y, width, height)

        g.color = color
        g.fillRect(getDrawingPosition().x + PADDING, getDrawingPosition().y + PADDING, getFilledWidth(), height - 2*PADDING)

        g.color = completingColor
        g.fillRect(getDrawingPosition().x + getFilledWidth(), getDrawingPosition().y + PADDING, getNonFilledWidth() - PADDING, height - 2*PADDING)
    }

    override fun move() {
        TODO("Not yet implemented")
    }
}