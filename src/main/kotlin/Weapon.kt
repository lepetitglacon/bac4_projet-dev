import java.awt.Graphics2D
import java.time.LocalTime

abstract class Weapon {

    var position = Vector2()
    var attack = 10
    var range = 5
    var cooldown: Long = 5
    var lastCooldown: LocalTime = LocalTime.now()

    fun attack() {
        if (canFire()) fire()
    }

    abstract fun collides(e: Entity): Boolean

    abstract fun draw(g: Graphics2D)

    fun canFire(): Boolean {
        val cooldownTime = lastCooldown.plusSeconds(cooldown)
        return cooldownTime < LocalTime.now()
    }

    fun fire() {

        Renderer.entities.forEach {
            val collides = collides(it)
            if (collides) {
                it.hp -= attack
            }
        }

        lastCooldown = LocalTime.now()
        println("fire")
    }
}
