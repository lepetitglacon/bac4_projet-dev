package engine.entity.mob

import engine.GameEngine
import engine.entity.enums.DrawablePosition
import engine.entity.Entity
import engine.entity.MovableEntity
import java.awt.Color
import java.awt.Graphics2D

class Hero : MovableEntity() {

    init {
        drawingPosition = DrawablePosition.CENTERED
        drawingRelative = null
    }

    override fun draw(g: Graphics2D) {
        if (GameEngine.debug) {
            drawDebugPosition(g)
            g.color = Color.YELLOW
            g.fillOval(getDrawingPosition().x, getDrawingPosition().y, width, height)
        }
    }

    override fun move() {
        moveFromKeyboard()
    }


}