package engine.entity.weapon.effect

import java.time.Instant

/**
 * duration in ms
 */
abstract class WeaponEffect(val start: Instant = Instant.now(), val duration: Int = 1000)
{
    abstract fun apply()

    fun isFinshed() : Boolean = start.toEpochMilli() + duration < Instant.now().toEpochMilli()
}