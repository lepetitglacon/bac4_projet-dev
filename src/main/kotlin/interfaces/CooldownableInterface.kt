package interfaces

import java.time.LocalTime

interface CooldownableInterface {
    var attackSpeed: Long
    var cooldown: Long
    var lastCooldown: LocalTime

    fun canFire(): Boolean {
        return lastCooldown.plusSeconds(cooldown) < LocalTime.now()
    }
    fun handleCooldown() {
        lastCooldown = LocalTime.now()
    }
}