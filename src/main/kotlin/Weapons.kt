import Renderer.WINDOW_HEIGHT
import Renderer.WINDOW_WIDTH
import java.awt.Color
import java.awt.Graphics2D

class Sword(): Weapon() {

    init {
        position = Renderer.hero.position
    }

    override fun collides(e: Entity): Boolean {
        return position.distance(e.position) < range
    }

    override fun draw(g: Graphics2D) {
        g.color = Color.MAGENTA
        g.fillArc(WINDOW_WIDTH / 2, WINDOW_HEIGHT / 2, 25, 25, 0, 90)
    }
}