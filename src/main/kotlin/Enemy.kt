import Renderer.WINDOW_HEIGHT
import Renderer.WINDOW_WIDTH
import java.awt.Color
import java.awt.Graphics2D

class Enemy() : Entity() {
    var directionToHero = Vector2()


    init {
        attack = 1
    }

    fun move() {
        val normalized = Vector2.normalize(Vector2.add(position, Renderer.hero.position))
        val newPos = Vector2()

        val posMinusHero = Vector2()
        posMinusHero.x = Renderer.hero.position.x - position.x
        posMinusHero.y = Renderer.hero.position.y - position.y

        newPos.x = position.x + posMinusHero.x / posMinusHero.lenght()  * speed
        newPos.y = position.y + posMinusHero.y / posMinusHero.lenght()  * speed


        position = newPos
    //        position.x += normalized.x * speed
//        position.y += normalized.y * speed
    }

    override fun draw(g: Graphics2D) {
        drawHealthbar(g)

        // body
        g.color = color
        g.fillOval(getDrawingX(), getDrawingY(), size, size)
        g.color = Color.black

        // hero vector
        val centerX = getDrawingX() + size / 2
        val centerY = getDrawingY() + size / 2
        val endX = WINDOW_WIDTH / 2 + directionToHero.x * 2
        val endY = WINDOW_HEIGHT / 2 + directionToHero.y * 2
        g.color = Color.PINK
        g.drawLine(centerX, centerY, endX.toInt(), endY.toInt())

        g.color = Color.GREEN
        g.drawLine(centerX, centerY, position.x.toInt(), position.y.toInt())
    }

}