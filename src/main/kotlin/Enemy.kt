import Renderer.WINDOW_HEIGHT
import Renderer.WINDOW_WIDTH
import java.awt.Color
import java.awt.Graphics2D

class Enemy() : Entity() {

    init {
        attack = 1
    }

    override fun draw(g: Graphics2D) {
        drawHealthbar(g)
        g.color = color
        g.fillOval(
            (position.x - Renderer.hero.position.x + WINDOW_WIDTH / 2 - size / 2).toInt(),
            (position.y - Renderer.hero.position.y + WINDOW_HEIGHT / 2 - size / 2).toInt(),
            size,
            size
        )
        g.color = Color.black
    }

}