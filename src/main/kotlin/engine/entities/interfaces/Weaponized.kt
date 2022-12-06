package engine.entities.interfaces

import engine.entities.weapon.Projectile

interface Weaponized {
    val weapons: MutableList<Projectile>

    fun fireAll()
}