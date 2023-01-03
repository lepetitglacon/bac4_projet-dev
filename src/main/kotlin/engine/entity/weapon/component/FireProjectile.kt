package engine.entity.weapon.component

import engine.entity.Entity
import java.time.Instant

class FireProjectile : Projectile() {
    var tick = 750 // ms
    val firedEntities: MutableSet<FiredEntity> = mutableSetOf()

    override fun attack(): Int {
        return damages + super.attack()
    }

    fun applyDamageToFiredEntities() {
        firedEntities.forEach {  }
    }

    class FiredEntity(val entity: Entity)
    {
        val startTime = Instant.now()
        val lastTick = Instant.now()
    }
}