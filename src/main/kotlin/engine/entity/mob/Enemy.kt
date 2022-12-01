package engine.entity.mob

import engine.GameEngine
import engine.entity.Entity
import engine.entity.MovableEntity
import engine.entity.enums.DrawablePosition
import engine.entity.sprite.Sprite
import java.awt.Color
import java.awt.Graphics2D

class Enemy : MovableEntity() {

    init {
        width = 32
        height = 32
        drawingRelative = GameEngine.game.hero
        drawingPosition = DrawablePosition.RELATIVE_TO_HERO
        sprite = Sprite.getPokemonSprite()
    }

    override fun draw(g: Graphics2D) {
        if (GameEngine.debug) {
            drawDebugPosition(g)
            g.color = Color.RED
            g.fillOval(getDrawingPosition().x, getDrawingPosition().y, width, height)
        }
        g.drawImage(sprite, null, getDrawingPosition().x - width/2, getDrawingPosition().y - height)
    }

    override fun move() {

    }

}