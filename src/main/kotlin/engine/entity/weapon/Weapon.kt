package engine.entity.weapon

import engine.entity.Entity
import engine.entity.sprite.Sprite
import java.awt.Graphics2D
import java.time.Instant

abstract class Weapon
{
    open var cooldown: Long = 500 // ms
    var lastCooldown = Instant.now().toEpochMilli()

    open fun canFire(): Boolean
    {
        return lastCooldown + cooldown < Instant.now().toEpochMilli()
    }

    abstract fun update()
    abstract fun draw(g: Graphics2D)
}