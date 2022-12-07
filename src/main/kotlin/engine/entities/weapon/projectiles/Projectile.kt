package engine.entities.weapon.projectiles

import engine.GameEngine
import engine.entities.CollidableEntity
import engine.entities.Entity
import engine.entities.interfaces.Attacker
import engine.entities.interfaces.Cooldownable
import engine.events.hero.HeroEventType
import game.mob.Hero

abstract class Projectile : CollidableEntity(), Attacker, Cooldownable {

    fun checkCollisionWith(entities: MutableList<CollidableEntity>) {
        if (canMakeAction()) {
            entities.forEach {
                if (collides(it)) {
                    applyDamage(it)
                    if (it is Hero) GameEngine.heroEventManager.notify(HeroEventType.HP_DOWN)
                }
            }
            resetTimer()
        }
    }
}