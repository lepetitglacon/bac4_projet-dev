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
    var position: Vector2
    var drawingPosition: Vector2
    var drawingPositionType: DrawablePosition
    var drawingPositionTypeRelative: Entity?
    var width: Int
    var height: Int
    var sprite: BufferedImage?

    fun draw(g: Graphics2D) {
        drawDebugPosition(g)
    }

//    fun drawHealthBar()

    fun drawDebugPosition(g: Graphics2D) {
        g.color = Color.white
//        g.drawString("${getDrawingPosition()}", getDrawingPosition().x, getDrawingPosition().y - 30)
        g.drawString("dra ${drawingPosition.toInt()}", getDrawingPosition().x, getDrawingPosition().y - 15)
        g.drawString("pos ${position.toInt()}", getDrawingPosition().x, getDrawingPosition().y)
        g.color = Color.red
        g.drawOval(getDrawingPosition().x, getDrawingPosition().y, 2,2)
    }

    fun getDrawingPosition(overridePosition: DrawablePosition? = null): Vector2Int {
        var dp = drawingPositionType
        if (overridePosition !== null) dp = overridePosition

        when (dp) {
            DrawablePosition.RELATIVE -> {
                if (drawingPositionTypeRelative == null) {
                    Logger.error("Can't get drawing position of Relative if relative is not assigned")
                    return position.toInt()
                }
                return Vector2Int(
                    (position.x + drawingPositionTypeRelative!!.getDrawingPosition().x + drawingPositionTypeRelative!!.width/2).toInt(),
                    (position.y + drawingPositionTypeRelative!!.getDrawingPosition().y + drawingPositionTypeRelative!!.height/2).toInt()
                )
            }
            DrawablePosition.RELATIVE_TO_HERO -> {
                return Vector2Int(
                    (drawingPosition.x - GameEngine.game.hero.position.x + GameEngine.window.WIDTH / 2).toInt(),
                    (drawingPosition.y - GameEngine.game.hero.position.y + GameEngine.window.HEIGHT / 2).toInt()
                )
            }
            DrawablePosition.CENTERED -> {
                return Vector2Int(
                    (GameEngine.window.WIDTH/2 - width/2),
                    (GameEngine.window.HEIGHT/2 - height/2)
                )
            }
            DrawablePosition.ABSOLUTE -> {
                return position.toInt()
            }
            DrawablePosition.ABSOLUTE_CENTERED -> {
                return Vector2Int(
                    (position.x - width/2).toInt(),
                    position.y.toInt()
                )
            }
            else -> {return Vector2Int()}
        }
    }
}