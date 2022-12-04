package engine.entity.mob

import engine.GameEngine
import engine.entity.CollidableEntity
import engine.entity.enums.DrawablePosition
import engine.entity.interfaces.Levelable
import engine.entity.interfaces.Weaponized
import engine.entity.factory.SpriteFactory
import engine.entity.item.Soul
import engine.entity.weapon.Projectile
import engine.sound.SoundManager
import java.awt.Color
import java.awt.Graphics2D

class Hero : CollidableEntity(), Levelable, Weaponized {
    override var xp: Int = 0
    override var xpToNextLevel: Int = 100
    override var level: Int = 1
    override val weapons: MutableList<Projectile> = mutableListOf()

    var pickUpRange: Int = 32

    init {
        width = 32
        height = 32
        drawingPositionType = DrawablePosition.CENTERED
        drawingPositionTypeRelative = null
        sprite = SpriteFactory.getHeroSprite()
        speed = 1.5
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
        GameEngine.game.map.onPlayerMovement()
    }

    override fun checkForLevelUp() {
        if (xp >= xpToNextLevel) {
            SoundManager.play("level up")
            val diff = xp - xpToNextLevel
            xpToNextLevel *= 2
            level++
            xp = 0 + diff
        }
    }

    fun checkCollisionWithEnemies() {
        GameEngine.game.collidableEntities.forEach {
            it as Enemy
            if (collides(it)) {
                it.applyDamage(this)
            }
        }
    }

    fun checkCollisionWithObjects() {
        GameEngine.game.objects.forEach {
            if (collides(it)) {
                when(it) {
                    is Soul -> {
                        SoundManager.play("xp")
                        xp += it.xp
                        it.delete = true
                    }
                }
            }
        }
    }

    override fun fireAll() {
        TODO("Not yet implemented")
    }
}