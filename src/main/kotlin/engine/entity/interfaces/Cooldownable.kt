package engine.entity.interfaces

import java.time.Duration
import java.time.Instant

interface Cooldownable {
    var cooldownTime: Long
    var lastCooldownTime: Instant

    fun canFire(): Boolean {
        return lastCooldownTime.plusSeconds(cooldownTime).isBefore(Instant.now())
    }
}