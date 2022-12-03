package engine.entity.interfaces

import engine.entity.Entity

interface Attacker {
    var damages: Int

    fun attack(e: Entity) {
        e.hp -= damages
    }
}