package engine.entities.interfaces

import engine.entities.Entity

interface Attacker {
    var damages: Int

    fun applyDamage(e: Entity) {
        e.hp -= damages
    }
}