package engine.entities.interfaces

import engine.entities.weapon.Weapon

interface Weaponized {
    val weapons: MutableList<Weapon>

    fun fireAll()
}