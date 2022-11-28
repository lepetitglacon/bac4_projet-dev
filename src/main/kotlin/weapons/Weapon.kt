package weapons

import Entity
import Renderer
import Renderer.WINDOW_HEIGHT
import Renderer.WINDOW_WIDTH
import Vector2
import interfaces.FireableInterface
import interfaces.PositionInterface
import java.awt.Graphics2D
import java.time.LocalTime

abstract class Weapon {
    var damage = 10
    open var size = Renderer.hero.size + 25
    var attackSpeed: Long = 100_000_000 // 100 ms

    /**
     * Nombre de seconde de cooldown
     */
    var cooldown: Long = 5
    var lastCooldown: LocalTime = LocalTime.now()

    var needRemoval = false

    abstract fun collides(e: Entity): Boolean
    abstract fun attack()
    abstract fun draw(g: Graphics2D)
    abstract fun checkCollision()
    abstract fun updatePosition(vector: Vector2)
    abstract fun fire()

    fun canFire(): Boolean {
        return lastCooldown.plusSeconds(cooldown) < LocalTime.now()
    }

    fun handleCooldown() {
        lastCooldown = LocalTime.now()
    }



    fun isAttacking(): Boolean {
        return lastCooldown.plusNanos(attackSpeed) > LocalTime.now()
    }
}
