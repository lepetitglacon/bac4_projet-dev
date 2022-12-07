package engine.entities.factory

import engine.entities.weapon.*
import engine.entities.weapon.projectiles.UndeadStink
import engine.entities.weapon.projectiles.SwordSwing
import engine.maths.Vector2

object WeaponFactory {

    fun createSword(): Sword {
        return Sword()
    }

    fun createSwordSwing(v: Vector2): SwordSwing {
        return SwordSwing(v)
    }


    fun createStink(): UndeadStink {
        return UndeadStink()
    }
}