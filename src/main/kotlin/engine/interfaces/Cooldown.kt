package engine.interfaces

import java.time.LocalTime

interface Cooldown {
    var cooldown: Long
    var lastCooldown: LocalTime

    fun canFire(): Boolean {
        return lastCooldown.plusSeconds(cooldown) < LocalTime.now()
    }

    fun fire()
}