package engine.entity.mob.enemy.boss

import engine.entity.Entity
import java.time.Instant

abstract class EnemyBoss : Entity() {
    val cooldown = 5000 // ms
    val lastCooldown: Instant = Instant.now()

    abstract fun attack()
}