package engine.entity.weapon.component.grenade

import engine.GameEngine
import engine.entity.Entity
import engine.entity.mob.enemy.Enemy
import engine.entity.weapon.component.projectile.Projectile
import engine.math.MathUtility

class Grenade : Projectile() {
    val blastRadius = 64


    override fun onHit(entity: Entity) {
        when (entity) {
            is Enemy -> {
                if (!allreadyHitEnemy.contains(entity)) {
                    allreadyHitEnemy.add(entity)
                    entity.applyDamage(damages)

                    GameEngine.game?.enemies?.forEach {
                        if (blastCollides(it)) {
                            println("grenade blast collides")
                            it.applyDamage(damages)
                        }
                    }

                    hp--
                }
            }
            else -> { println("bullet hit something unknown") }

        }
    }

    fun blastCollides(entity: Entity): Boolean = MathUtility.collides(this, entity, blastRadius)
}