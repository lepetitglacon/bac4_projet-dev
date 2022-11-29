package engine.gui

import engine.GameEngine
import engine.Renderer
import engine.Vector2
import engine.factories.EntityFactory
import java.awt.Color
import java.awt.Graphics2D

class Hud {
    var backgroundColor = Color(0,0,0,25)
    var margin = 25

    fun draw(g: Graphics2D) {
        g.color = backgroundColor

        val left = EntityFactory.createRectangle(Vector2(0.0 + margin, 0.0 + margin), 50, 50)
        val right = EntityFactory.createRectangle(Vector2((GameEngine.gui.windowWidth - 50 - margin).toDouble(), (0 + margin).toDouble()), 50, 50)

        // left
        g.fillRect(
            left.position.x.toInt(),left.position.y.toInt(), left.width, left.height)

        g.fillRect(right.position.x.toInt(),right.position.y.toInt(), right.width, right.height)
    }
}