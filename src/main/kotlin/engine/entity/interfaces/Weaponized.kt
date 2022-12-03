package engine.entity.interfaces

import engine.entity.weapon.Projectile

interface Weaponized {
    val weapons: MutableList<Projectile>

    fun fireAll()
}