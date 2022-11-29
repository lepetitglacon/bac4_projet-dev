package engine.factories

import engine.GameEngine
import engine.Vector2
import engine.entities.physical.Rectangle
import java.awt.Color

object EntityFactory {

    fun createRectangle() = Rectangle(GameEngine.gui.center(), 50, 50, Color.DARK_GRAY)
    fun createRectangle(position: Vector2) = Rectangle(position, 50, 50, Color.DARK_GRAY)
    fun createRectangle(position: Vector2, width: Int) = Rectangle(position, width, width, Color.DARK_GRAY)
    fun createRectangle(position: Vector2, width: Int, height: Int) = Rectangle(position, width, height, Color.DARK_GRAY)


}