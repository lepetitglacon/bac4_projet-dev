package engine.entity.mob

import Vector2
import engine.GameEngine
import engine.entity.DrawablePosition
import engine.entity.Entity
import java.awt.Color
import java.awt.Graphics2D

class Hero : Entity() {
    init {
        drawingPosition = DrawablePosition.CENTERED
    }

    override fun draw(g: Graphics2D) {
        if (GameEngine.debug) {
            g.color = Color.YELLOW
            g.fillOval(getDrawingPosition(null).x.toInt(), getDrawingPosition(null).y.toInt(), width, height)
        }

    }

    override fun move() {

    }

}