package engine.entity.interfaces

import engine.entity.Entity

interface Attacker {
    var damages: Int

    fun applyDamage(e: Entity) {
        e.hp -= damages
    }
}