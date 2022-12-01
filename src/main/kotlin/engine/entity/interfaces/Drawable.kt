package engine.entity.interfaces

import engine.GameEngine
import engine.entity.Entity
import engine.entity.enums.DrawablePosition
import engine.logger.Logger
import engine.maths.Vector2
import engine.maths.Vector2Int
import java.awt.Color
import java.awt.Graphics2D
import java.awt.image.BufferedImage

interface Drawable {
    var startPosition: Vector2
    var centerPosition: Vector2
    var drawingPosition: DrawablePosition
    var drawingRelative: Entity?
    var width: Int
    var height: Int
    var sprite: BufferedImage?

    fun draw(g: Graphics2D) {
        drawDebugPosition(g)
    }

//    fun drawHealthBar()

    fun drawDebugPosition(g: Graphics2D) {
        g.color = Color.black
        g.drawString("${getDrawingPosition()}", getDrawingPosition().x, getDrawingPosition().y - 15)
        g.color = Color.black
        g.drawString("${startPosition}", getDrawingPosition().x, getDrawingPosition().y)
    }

    fun getDrawingPosition(): Vector2Int {
        when (drawingPosition) {
            DrawablePosition.RELATIVE -> {
                if (drawingRelative == null) {
                    Logger.error("Can't get drawing position of Relative if relative is not assigned")
                    return startPosition.toInt()
                }
                return Vector2Int(
                    (startPosition.x + drawingRelative!!.getDrawingPosition().x + drawingRelative!!.width/2).toInt(),
                    (startPosition.y + drawingRelative!!.getDrawingPosition().y + drawingRelative!!.height/2).toInt()
                )
            }
            DrawablePosition.RELATIVE_TO_HERO -> {
                return Vector2Int(
                    (startPosition.x - width / 2 - GameEngine.game.hero.startPosition.x + GameEngine.window.WIDTH / 2).toInt(),
                    (startPosition.y - width / 2 - GameEngine.game.hero.startPosition.y + GameEngine.window.HEIGHT / 2).toInt()
                )
            }
            DrawablePosition.CENTERED -> {
                return Vector2Int(
                    (GameEngine.window.WIDTH/2 - width/2),
                    (GameEngine.window.HEIGHT/2 - height/2)
                )
            }
            DrawablePosition.ABSOLUTE -> {
                return startPosition.toInt()
            }
        }
    }
}