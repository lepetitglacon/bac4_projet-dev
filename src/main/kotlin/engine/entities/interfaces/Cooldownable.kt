package engine.entities.interfaces

import java.time.Instant
import kotlin.time.Duration

interface Cooldownable {
    var cooldownTime: Long
    var lastCooldownTime: Instant

    fun canMakeAction(): Boolean {
        return lastCooldownTime.plusSeconds(cooldownTime).isBefore(Instant.now())
    }

    fun resetTimer() {
        lastCooldownTime = Instant.now()
    }
}