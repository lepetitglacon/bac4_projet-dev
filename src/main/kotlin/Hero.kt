import Renderer.FRAME_PER_MSEC
import Renderer.WINDOW_HEIGHT
import Renderer.WINDOW_WIDTH
import java.awt.Color
import java.awt.Graphics2D
import kotlin.math.cos
import kotlin.math.sin

class Hero(var posX: Int, var posY: Int, var size: Int, var position: Vector2 = Vector2(0.0,0.0), var velocity: Vector2 = Vector2(0.0,0.0), var angle: Double = 0.0) {
    var speed = 10.0 // Max distance per tick

    fun draw(g: Graphics2D) {
        val centerX = WINDOW_WIDTH / 2
        val centerY = WINDOW_HEIGHT / 2
        // Draw the hero always in the center of the screen
        g.color = Color.blue
        g.fillOval( centerX - 15, centerY - 15, 30, 30)
        g.color = Color.white
        g.fillOval( centerX - 10, centerY - 10, 20, 20)
        g.color = Color.red
        g.fillOval( centerX - 5, centerY - 5, 10, 10)
    }

    fun isColliding(e: Enemy): Boolean {
        val distance = Math.sqrt(Math.pow((posX - e.posX).toDouble(), 2.0) + Math.pow((posY - e.posY).toDouble(), 2.0))
        return distance < (size / 2 + e.size / 2)
    }

    fun move(vector: Vector2) {
        // update position by speed
        vector.normalize()
        position.x += (vector.x * speed).toInt()
        position.y += (vector.y * speed).toInt()
        posX += (vector.x * speed).toInt()
        posY += (vector.y * speed).toInt()
    }
}