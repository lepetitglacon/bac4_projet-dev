import Renderer.FRAME_PER_MSEC
import Renderer.WINDOW_HEIGHT
import Renderer.WINDOW_WIDTH
import java.awt.Color
import java.awt.Graphics2D
import kotlin.math.cos
import kotlin.math.sin

class Hero() : Entity() {

    init {
        color = Color.ORANGE
    }

    override fun draw(g: Graphics2D) {
        val startX = WINDOW_WIDTH / 2 - size / 2
        val startY = WINDOW_HEIGHT / 2 - size / 2
        val centerX = startX + size / 2
        val centerY = startY + size / 2
        val normalized = Vector2.normalize(Renderer.userInputVector)
        val endX = centerX + normalized.x * (size/2)
        val endY = centerY + normalized.y * (size/2)

        g.color = color
        g.fillOval(startX, startY, size, size)
        g.color = Color.GREEN
        g.drawLine(centerX, centerY, endX.toInt(), endY.toInt())

        drawHealthbar(g)
    }

    fun move(vector: Vector2) {
        // update position by speed
        vector.normalize()
        position.x += (vector.x * speed).toInt()
        position.y += (vector.y * speed).toInt()
    }

    override fun attack() {
        TODO("Not yet implemented")
    }
}