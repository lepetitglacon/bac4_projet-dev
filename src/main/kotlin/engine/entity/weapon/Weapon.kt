package engine.entity.weapon

import engine.entity.Entity
import engine.entity.sprite.Sprite
import java.awt.Graphics2D
import java.time.Instant

abstract class Weapon
{
    val cooldown: Long = 5000
    var lastCooldown = Instant.now()

    fun canFire(): Boolean
    {
        return lastCooldown.toEpochMilli() + cooldown < Instant.now().toEpochMilli()
    }

    abstract fun update()
    abstract fun draw(g: Graphics2D)
}