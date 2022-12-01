package engine.entity.mob

import engine.GameEngine
import engine.entity.Entity
import engine.entity.MovableEntity
import engine.entity.enums.DrawablePosition
import java.awt.Color
import java.awt.Graphics2D

class Enemy : MovableEntity() {

    init {
        width = 25
        height = 25
        drawingRelative = GameEngine.game.hero
        drawingPosition = DrawablePosition.RELATIVE
    }

    override fun draw(g: Graphics2D) {
        if (GameEngine.debug) {
            drawDebugPosition(g)
            g.color = Color.RED
            g.fillOval(getDrawingPosition().x, getDrawingPosition().y, width, height)
        }
    }

    override fun move() {

    }

}