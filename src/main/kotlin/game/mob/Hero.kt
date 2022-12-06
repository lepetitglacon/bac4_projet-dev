package game.mob

import engine.GameEngine
import engine.entities.CollidableEntity
import engine.entities.enums.DrawablePosition
import engine.entities.interfaces.Levelable
import engine.entities.interfaces.Weaponized
import engine.entities.factory.SpriteFactory
import engine.entities.item.Soul
import engine.entities.weapon.Weapon
import engine.events.hero.HeroEventType
import engine.sound.SoundManager
import java.awt.Color
import java.awt.Graphics2D

class Hero : CollidableEntity(), Levelable, Weaponized {
    override var xp: Int = 0
    override var xpToNextLevel: Int = 100
    override var level: Int = 1
    override val weapons: MutableList<Weapon> = mutableListOf()

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
            val diff = xp - xpToNextLevel
            xpToNextLevel += 25
            level++
            xp = 0 + diff
            GameEngine.heroEventManager.notify(HeroEventType.XP_LEVELUP)
        }
    }

    fun checkCollisionWithEnemies() {
        GameEngine.game.collidableEntities.forEach {
            it as Enemy
            if (collides(it)) {
                it.applyDamage(this)
                GameEngine.heroEventManager.notify(HeroEventType.HP_DOWN)
            }
        }
    }

    fun checkCollisionWithObjects() {
        GameEngine.game.objects.forEach {
            if (collides(it)) {
                when(it) {
                    is Soul -> {
                        xp += it.xp
                        it.delete = true
                        GameEngine.heroEventManager.notify(HeroEventType.XP_UP)
                    }
                }
            }
        }
    }

    override fun fireAll() {
        TODO("Not yet implemented")
    }
}