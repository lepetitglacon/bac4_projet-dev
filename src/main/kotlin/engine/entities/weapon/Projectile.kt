package engine.entities.weapon

import engine.GameEngine
import engine.entities.CollidableEntity
import engine.entities.interfaces.Attacker
import engine.entities.interfaces.Cooldownable

abstract class Projectile : CollidableEntity(), Attacker, Cooldownable {

    fun checkCollisionWithEnemies() {
        GameEngine.game.collidableEntities.forEach {
            if (collides(it)) {
                applyDamage(it)
            }
        }
    }

    fun fire() {
        if (canFire()) {
            println("can fire")
        }
    }
}