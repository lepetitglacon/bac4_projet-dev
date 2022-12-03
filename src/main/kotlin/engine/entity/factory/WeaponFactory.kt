package engine.entity.factory

import engine.entity.weapon.Stink

object WeaponFactory {

    fun createStink(): Stink {
        return Stink()
    }
}