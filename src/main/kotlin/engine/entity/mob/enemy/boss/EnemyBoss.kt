package engine.entity.mob.enemy.boss

import engine.entity.mob.enemy.Enemy
import java.time.Instant

abstract class EnemyBoss : Enemy() {
    val cooldown = 5000 // ms
    val lastCooldown: Instant = Instant.now()

}