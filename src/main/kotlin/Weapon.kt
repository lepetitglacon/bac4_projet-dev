import engine.Renderer
import engine.Renderer.WINDOW_HEIGHT
import engine.Renderer.WINDOW_WIDTH
import engine.Vector2
import java.awt.Graphics2D
import java.time.LocalTime

abstract class Weapon {

    var position = Vector2()
    var velocity = Vector2()
    var damage = 10
    var range = Renderer.game.hero.size + 25
    var speed: Int = 25
    var attackSpeed: Long = 100_000_000 // 100 ms
    var cooldown: Long = 5
    var lastCooldown: LocalTime = LocalTime.now()
    var needRemoval = false

    abstract fun collides(e: Entity): Boolean
    abstract fun attack()
    abstract fun draw(g: Graphics2D)
    abstract fun checkCollision()
    abstract fun updatePosition(vector: Vector2)
    abstract fun fire()

    fun canFire(): Boolean {
        return lastCooldown.plusSeconds(cooldown) < LocalTime.now()
    }

    fun handleCooldown() {
        lastCooldown = LocalTime.now()
    }

    fun getDrawingX(): Int {
        return (position.x - range / 2 - Renderer.game.hero.position.x + WINDOW_WIDTH / 2).toInt()
    }

    fun getDrawingY(): Int {
        return (position.y - range / 2 - Renderer.game.hero.position.y + WINDOW_HEIGHT / 2).toInt()
    }

    fun isAttacking(): Boolean {
        return lastCooldown.plusNanos(attackSpeed) > LocalTime.now()
    }
}
