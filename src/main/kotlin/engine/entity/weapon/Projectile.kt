package engine.entity.weapon

import engine.GameEngine
import engine.entity.CollidableEntity
import engine.entity.Entity
import engine.entity.interfaces.Attacker
import engine.entity.interfaces.Cooldownable

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