package engine.entity.mob

import engine.GameEngine
import engine.entity.enums.DrawablePosition
import engine.entity.Entity
import engine.entity.MovableEntity
import engine.entity.sprite.Sprite
import java.awt.Color
import java.awt.Graphics2D

class Hero : MovableEntity() {

    init {
        width = 32
        height = 32
        drawingPosition = DrawablePosition.CENTERED
        drawingRelative = null
        sprite = Sprite.getHeroSprite()
    }

    override fun draw(g: Graphics2D) {
        if (GameEngine.debug) {
            drawDebugPosition(g)
            g.color = Color.YELLOW
            g.fillOval(getDrawingPosition().x, getDrawingPosition().y, width, height)
        }
        g.drawImage(sprite, null, getDrawingPosition().x - width/2, getDrawingPosition().y - height)
    }

    override fun move() {
        moveFromKeyboard()
    }


}