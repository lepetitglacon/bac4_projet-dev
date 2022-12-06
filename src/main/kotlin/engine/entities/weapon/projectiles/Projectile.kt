package engine.entities.weapon.projectiles

import engine.GameEngine
import engine.entities.CollidableEntity
import engine.entities.interfaces.Attacker
import engine.entities.interfaces.Cooldownable

abstract class Projectile : CollidableEntity(), Attacker, Cooldownable {

    fun checkCollisionWithEnemies() {
        GameEngine.game.collidableEntities.forEach {
            if (collides(it) && canMakeAction()) {
                applyDamage(it)
            }
        }
    }
}