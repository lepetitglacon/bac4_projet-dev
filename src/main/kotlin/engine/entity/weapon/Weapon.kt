package engine.entity.weapon

import engine.entity.Entity
import engine.entity.sprite.Sprite
import engine.resource.SpriteFactory
import java.awt.Graphics2D
import java.time.Instant

abstract class Weapon
{
    abstract var sprite: Sprite
    abstract var cooldown: Long // ms

    var lastCooldown = Instant.now().toEpochMilli()

    open fun canFire(): Boolean
    {
        return lastCooldown + cooldown < Instant.now().toEpochMilli()
    }

    abstract fun update()
    abstract fun draw(g: Graphics2D)
}