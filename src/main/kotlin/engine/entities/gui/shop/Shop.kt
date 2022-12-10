package engine.entities.gui.shop

import engine.GameEngine
import engine.entities.MovableEntity
import engine.entities.enums.DrawablePosition
import engine.maths.Vector2
import java.awt.Color
import java.awt.Graphics2D

class Shop : MovableEntity() {



    override var drawingPosition: Vector2 = Vector2()
    override var drawingPositionType: DrawablePosition = DrawablePosition.CENTERED
    override var width: Int = GameEngine.window.WIDTH / 2
    override var height: Int = GameEngine.window.HEIGHT / 2

    override fun move() {
        TODO("Not yet implemented")
    }

    override fun draw(g: Graphics2D) {
        g.color = Color(0, 0, 0, 200)
        g.fillRoundRect(getDrawingPosition().x, getDrawingPosition().y, width, height, 30, 30)
    }
}