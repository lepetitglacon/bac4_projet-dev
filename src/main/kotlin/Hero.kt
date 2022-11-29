import engine.Renderer
import engine.Renderer.WINDOW_HEIGHT
import engine.Renderer.WINDOW_WIDTH
import engine.Vector2
import java.awt.Color
import java.awt.Graphics2D
import kotlin.math.*

class Hero() : Entity() {
    val gun = Gun()
    var deaths = 0
    var dead = false

    init {
        color = Color.ORANGE
        speed = 5.0
    }

    override fun draw(g: Graphics2D) {
        val startX = WINDOW_WIDTH / 2 - size / 2
        val startY = WINDOW_HEIGHT / 2 - size / 2
        val centerX = startX + size / 2
        val centerY = startY + size / 2
        val normalized = Renderer.inputHandler.userInputVector.clone().normalize()
        val endX = centerX + velocity.x * 2
        val endY = centerY + velocity.y * 2

        g.color = color
        g.fillOval(startX, startY, size, size)
        g.color = Color.GREEN
        g.drawLine(centerX, centerY, endX.toInt(), endY.toInt())

        drawHealthbar(g)
    }

    fun move(vector: Vector2) {
        // update position by speed
        vector.normalize()
        velocity.x += (vector.x * speed).toInt()
        velocity.y += (vector.y * speed).toInt()

        position.x += velocity.x.toInt()
        position.y += velocity.y.toInt()
        angle = angle()

        if (velocity.x != 0.0) {
            velocity.x = (velocity.x * 0.5).toInt().toDouble()
        }
        if (velocity.y != 0.0) {
            velocity.y = (velocity.y * 0.5).toInt().toDouble()
        }

    }

    private fun angle(): Int {
        val normalized = velocity.clone().normalize()
        return (cos(normalized.x) + sin(normalized.y) * 180 / PI).toInt()
    }

    override fun attack() {
        weapons.forEach { it.attack() }
        gun.attack()
    }
}