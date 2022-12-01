package engine.entity

import Vector2
import engine.GameEngine
import engine.logger.Logger
import java.awt.Graphics2D
import java.awt.image.BufferedImage

abstract class Entity {
    var startPosition: Vector2 = Vector2()
        /*set(value) {
            val c = Vector2(value.x + width/2, value.y + height/2)
            if (c != centerPosition) {
                centerPosition = c
            }
            field = value
        }*/
    var centerPosition: Vector2 = Vector2()
        set(value) {
            val s = Vector2(value.x - width/2, value.y - height/2)
            if (s != startPosition) {
                startPosition = s
            }
            field = value
        }
    var width: Int = 100
    var height: Int = 100
    var drawingPosition: DrawablePosition = DrawablePosition.ABSOLUTE
    private var sprite: BufferedImage? = null

    var maxHp: Int = 100
    var hp: Int = 100

    abstract fun draw(g: Graphics2D)
    abstract fun move()

    fun getDrawingPosition(relative: Entity?): Vector2 {
        when (drawingPosition) {
            DrawablePosition.RELATIVE -> {
                if (relative == null) {
                    Logger.error("Can't get drawing position of Relative if relative is not assigned")
                    return startPosition
                }
                return Vector2(
                    startPosition.x + relative.getDrawingPosition(null).x + relative.width/2,
                    startPosition.y + relative.getDrawingPosition(null).y + relative.height/2
                )
            }
            DrawablePosition.CENTERED -> {
                return Vector2(
                    startPosition.x + GameEngine.window.WIDTH/2 - width/2,
                    startPosition.y + GameEngine.window.HEIGHT/2 - height/2
                )
            }
            DrawablePosition.ABSOLUTE -> {
                return startPosition
            }
        }
    }
}