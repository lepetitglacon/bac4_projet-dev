package engine.entities.interfaces

import engine.entities.Entity
import engine.logger.Logger

interface Attacker {
    var damages: Int

    fun applyDamage(e: Entity) {
        e.hp -= damages
        Logger.log("$this attacked $e ! -$damages hp")
    }
}