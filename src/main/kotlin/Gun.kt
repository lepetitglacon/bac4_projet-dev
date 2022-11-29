import engine.interfaces.Cooldown
import java.time.LocalTime

class Gun() : Cooldown {
    val bullets = mutableListOf<Bullet>()

    override var cooldown: Long = 1
    override var lastCooldown: LocalTime = LocalTime.now()

    fun attack() {
        if (canFire()) {
            fire()
        }
        bullets.forEach { it.attack() }
    }

    override fun fire() {
        bullets.add(EntityFactory.createBullet())
        lastCooldown = LocalTime.now()
        println(bullets)
    }

}