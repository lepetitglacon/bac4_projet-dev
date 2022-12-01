import Renderer.WINDOW_HEIGHT
import Renderer.WINDOW_WIDTH
import engine.maths.Vector2
import java.awt.Color
import java.awt.Graphics2D

/**
 * Une entité de base
 */
abstract class Entity {

    var maxHp = 100
    var hp = 100
    var maxMana = 100
    var mana = 100
    var attack = 10

    val weapons = mutableListOf<Weapon>()

    var position = Vector2()
    var velocity = Vector2()
    var angle = 0
    var rayon = Vector2(ENTITY_RAYON.toDouble()).distance(Vector2())
    var size = ENTITY_RAYON
    var speed = 2.5
    var color: Color = Color.LIGHT_GRAY
    var state: EntityState = EntityState.IDLE

    fun collides(e: Entity): Boolean {
        return position.distance(e.position) < size
    }

    fun repulse(entity: Entity) {
        val distance = position.distance(entity.position)
        val overlap = (distance - size) / 2
        position.x -= overlap * (position.x - entity.position.x) / distance
        position.y -= overlap * (position.y - entity.position.y) / distance
        entity.position.x += overlap * (position.x - entity.position.x) / distance
        entity.position.y += overlap * (position.y - entity.position.y) / distance
    }

    fun mustDie(e: Entity): Boolean {
        val collides = collides(e)

        if (collides) {
            hp -= e.attack
            e.hp -= attack
        }

        return collides && e.attack > hp
    }

    fun attack() {
        weapons.forEach { it.attack() }
    }

    abstract fun draw(g: Graphics2D)

    fun drawHealthbar(g: Graphics2D) {
        g.color = Color.BLACK
        g.fillRect(getDrawingX(), getDrawingY() - 10, size, 5)

        when (true) {
            (hp < 25) -> g.color = Color.RED
            (hp < 50) -> g.color = Color.YELLOW
            else -> g.color = Color.GREEN
        }
        g.fillRect(getDrawingX() + 1, getDrawingY() - 9, (size * hp / 100) - 2, 3)
    }

    fun getDrawingX(): Int {
        return (position.x - size / 2 - Renderer.hero.position.x + WINDOW_WIDTH / 2).toInt()
    }

    fun getDrawingY(): Int {
        return (position.y - size / 2 - Renderer.hero.position.y + WINDOW_HEIGHT / 2).toInt()
    }

}