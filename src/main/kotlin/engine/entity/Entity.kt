package engine.entity

import engine.maths.Vector2
import engine.entity.enums.DrawablePosition
import engine.entity.interfaces.Drawable
import java.awt.Graphics2D
import java.awt.image.BufferedImage

abstract class Entity : Drawable {
    override var position: Vector2 = Vector2()
    override var drawingPosition: Vector2 = Vector2()
    override var width: Int = 100
    override var height: Int = 100
    override var drawingPositionType: DrawablePosition = DrawablePosition.ABSOLUTE
    override var drawingPositionTypeRelative: Entity? = null
    override var sprite: BufferedImage? = null

    var maxHp: Int = 100
    var hp: Int = 100
}