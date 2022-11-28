import Renderer.WINDOW_HEIGHT
import Renderer.WINDOW_WIDTH
import Renderer.timeElapsed
import weapons.Weapon
import java.awt.Color
import java.awt.Graphics2D
import kotlin.math.*

class Hero(
    override var color: Color,
    override var damages: Int,
    override var width: Int,
    override var height: Int,
    override var size: Int,
    override var maxHp: Int,
    override var hp: Int,
    override var regeneration: Int,
    override var position: Vector2,
    override var velocity: Vector2,
    override var angle: Int,
    override var speed: Int,
    override val weapons: MutableList<Weapon>
) : Entity() {

    override fun draw(g: Graphics2D) {
        val startX = WINDOW_WIDTH / 2 - size / 2
        val startY = WINDOW_HEIGHT / 2 - size / 2
        val centerX = startX + size / 2
        val centerY = startY + size / 2
        val normalized = Vector2.normalize(Renderer.userInputVector)
        val endX = centerX + velocity.x * 2
        val endY = centerY + velocity.y * 2

        g.color = color
        g.fillOval(startX, startY, size, size)
        g.color = Color.GREEN
        g.drawLine(centerX, centerY, endX.toInt(), endY.toInt())

        drawHealthbar(g)
    }

    override fun goTo() {
        TODO("Not yet implemented")
    }

    override fun move() {
        TODO("Not yet implemented")
    }

    override fun collides(entity: Entity) {
        TODO("Not yet implemented")
    }

    override fun move(vector: Vector2) {
        // update position by speed
        vector.normalize()
        velocity.x += (vector.x * speed).toInt()
        velocity.y += (vector.y * speed).toInt()

        position.x += velocity.x.toInt()
        position.y += velocity.y.toInt()

        if (velocity.x != 0.0) {
            velocity.x = (velocity.x * 0.5).toInt().toDouble()
        }
        if (velocity.y != 0.0) {
            velocity.y = (velocity.y * 0.5).toInt().toDouble()
        }

    }
}