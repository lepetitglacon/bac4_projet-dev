package interfaces

import Renderer.WINDOW_HEIGHT
import Renderer.WINDOW_WIDTH
import java.awt.Graphics2D

interface DrawableInterface : PositionInterface {
    var width: Int
    var height: Int
    var size: Int

    fun draw(g: Graphics2D)

    fun getDrawingX(): Int {
        return (position.x - size / 2 - Renderer.hero.position.x + WINDOW_WIDTH / 2).toInt()
    }

    fun getDrawingY(): Int {
        return (position.y - size / 2 - Renderer.hero.position.y + WINDOW_HEIGHT / 2).toInt()
    }
}