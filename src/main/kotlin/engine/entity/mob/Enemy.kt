package engine.entity.mob

import engine.GameEngine
import engine.entity.CollidableEntity
import engine.entity.enums.DrawablePosition
import engine.entity.interfaces.Attacker
import engine.entity.sprite.Sprite
import java.awt.Color
import java.awt.Graphics2D

class Enemy : CollidableEntity(), Attacker {
    override var damages: Int = 5

    init {
        width = 32
        height = 32
        drawingPositionType = DrawablePosition.RELATIVE_TO_HERO
        sprite = Sprite.getPokemonSprite()
        speed = 2.0
    }

    override fun draw(g: Graphics2D) {
       if (GameEngine.debug) {
            drawDebugPosition(g)
            g.color = Color.RED
            g.fillOval(getDrawingPosition().x, getDrawingPosition().y, width, height)
            g.color = Color.BLUE
            g.fillOval(getDrawingPosition().x + width/2, getDrawingPosition().y + height/2, 3, 3)
        }
        g.drawImage(sprite, null, getDrawingPosition().x, getDrawingPosition().y)

        g.color = Color.black
        g.drawOval(drawingPosition.x.toInt(), drawingPosition.y.toInt(), width, height)
        g.drawString("$position", getDrawingPosition().x, getDrawingPosition().y - 30)
    }

    override fun move() {
        setDrawingPositionFromPosition()
        moveTo(GameEngine.game.hero.position)
    }

}