import Renderer.WINDOW_HEIGHT
import Renderer.WINDOW_WIDTH
import java.awt.Color
import java.awt.Graphics2D

class Enemy() : Entity() {

    init {
        attack = 1
    }

    fun move() {

        // go to hero
        position.translateTo(Renderer.hero.position, speed.toInt())

        // handle collision between enemies themselves
        Renderer.entities.forEach {
            if (it != this) {
                if (it.collides(this)) {
                    it.repulse(this)

                    // cyber psycho movement
//                val distance = it.position.distance(this.position)
//                it.position.x += distance/2
//                it.position.y += distance/2
//                position.x -= distance/2
//                position.y -= distance/2
                }
            }
        }
    }

    override fun draw(g: Graphics2D) {
        drawHealthbar(g)

        // body
        g.color = color
        g.fillOval(getDrawingX(), getDrawingY(), size, size)
        g.color = Color.black

        // line to hero
//        val centerX = getDrawingX() + size / 2
//        val centerY = getDrawingY() + size / 2
//        val endX = WINDOW_WIDTH / 2 + directionToHero.x * 2
//        val endY = WINDOW_HEIGHT / 2 + directionToHero.y * 2
//        g.color = Color.PINK
//        g.drawLine(centerX, centerY, endX.toInt(), endY.toInt())

        // vector to hero
//        g.color = Color.GREEN
//        g.drawLine(centerX, centerY, position.x.toInt(), position.y.toInt())
    }

}