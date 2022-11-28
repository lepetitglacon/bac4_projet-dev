package interfaces

import weapons.Weapon

interface WeaponizableInterface {
    val weapons: MutableList<Weapon>
}