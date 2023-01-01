package engine.entity.gui.component

import java.awt.Color
import java.awt.Graphics2D
import java.awt.Rectangle

class WindowGui(val x: Int = 0, val y: Int = 0, val width: Int = 0, val height: Int = 0) {

    fun draw(g: Graphics2D) {
        g.color = Color(0, 0, 0, 150)
        g.fillRect(x, y, width, height)
        g.color = Color.WHITE
        g.drawLine(x, y, x + width, y)
        g.drawLine(x, y + height, x + width, y + height)
    }
}