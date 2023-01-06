package engine.entity.gui.component

import engine.GameEngine
import java.awt.Color
import java.awt.Graphics2D

class BarComponent(var x: Int = 0, var y: Int = 0, var width: Int = 0, var height: Int = 0)
{
    constructor(width: Int, height: Int) : this(GameEngine.window.WIDTH/2 - width/2,GameEngine.window.HEIGHT - height - 25, width, height)

    val PADDING = 2
    var static = false

    var filled: Int = 100
    var maxFilled: Int = 100
    var color: Color = Color(44, 248, 11)
    var completingColor: Color = Color(122, 48, 7)
    var backgroundColor: Color = Color(217,217,217, 230)

    fun getFilledWidth(): Int {
        val centpourcent = width - (2 * PADDING)
        if (filled < 0) filled = 0
        return centpourcent * filled / maxFilled
    }

    fun getNonFilledWidth(): Int {
        return  width - getFilledWidth()
    }

    fun draw(g: Graphics2D) {
        g.color = backgroundColor
        g.fillRect(x, y, width, height)

        g.color = color
        g.fillRect(x + PADDING, y + PADDING, getFilledWidth(), height - 2*PADDING)

        g.color = completingColor
        g.fillRect(x + getFilledWidth(), y + PADDING, getNonFilledWidth() - PADDING, height - 2*PADDING)
    }
}