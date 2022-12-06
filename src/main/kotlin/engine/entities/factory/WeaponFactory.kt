package engine.entities.factory

import engine.entities.weapon.Stink

object WeaponFactory {

    fun createStink(): Stink {
        return Stink()
    }
}