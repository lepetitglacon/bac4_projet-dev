package engine.entity.mob

import engine.GameEngine
import engine.entity.CollidableEntity
import engine.entity.enums.DrawablePosition
import engine.entity.MovableEntity
import engine.entity.interfaces.Levelable
import engine.entity.sprite.Sprite
import engine.maths.Vector2
import java.awt.Color
import java.awt.Graphics2D

class Hero : CollidableEntity(), Levelable {
    override var xp: Int = 0
    override var xpToNextLevel: Int = 100

    init {
        width = 32
        height = 32
        drawingPositionType = DrawablePosition.CENTERED
        drawingPositionTypeRelative = null
        sprite = Sprite.getHeroSprite()
        speed = 2.5
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
        setDrawingPositionFromPosition()
        moveFromKeyboard()
    }

    override fun nextLevel() {
        if (xp >= xpToNextLevel) {
//           TODO Events.fire("heroNextLevel")
            xpToNextLevel *= 2
            xp = 0
        }
    }

    fun checkCollisionWithEnemies() {
        GameEngine.game.collidableEntities.forEach {
            it as Enemy
            if (collides(it)) {
                it.attack(this)
            }
        }
    }
}