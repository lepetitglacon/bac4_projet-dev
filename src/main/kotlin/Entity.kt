import Renderer.WINDOW_HEIGHT
import Renderer.WINDOW_WIDTH
import java.awt.Color
import java.awt.Point

/**
 * Une entité de base
 */
abstract class Entity {

    var maxHp = 100
    var hp = 100
    var maxMana = 100
    var mana = 100

    var position = Vector2()
    var velocity = Vector2()
    var rayon = Vector2(ENTITY_RAYON.toDouble()).distance(Vector2())
    var size = ENTITY_RAYON
    var speed = 10.0
    var color: Color = Color.LIGHT_GRAY
    var state: EntityState = EntityState.IDLE

    fun collides(e: Enemy): Boolean {
        return position.distance(e.position) < size
    }

    abstract fun attack()

}