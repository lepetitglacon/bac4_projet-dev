package engine.entities.factory

import engine.entities.weapon.*
import engine.entities.weapon.projectiles.UndeadStink
import engine.entities.weapon.projectiles.SwordSwing

object WeaponFactory {

    fun createSword(): Sword {
        return Sword()
    }

    fun createSwordSwing(): SwordSwing {
        return SwordSwing()
    }


    fun createStink(): UndeadStink {
        return UndeadStink()
    }
}