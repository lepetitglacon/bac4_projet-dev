package engine.entity

import engine.maths.Vector2
import engine.entity.enums.DrawablePosition
import engine.entity.gui.component.BarGui
import engine.entity.interfaces.Drawable
import java.awt.Graphics2D
import java.awt.image.BufferedImage

abstract class Entity : Drawable {
    override var position: Vector2 = Vector2()
    override var drawingPosition: Vector2 = Vector2()
    override var width: Int = 64
    override var height: Int = 64
    override var drawingPositionType: DrawablePosition = DrawablePosition.ABSOLUTE
    override var drawingPositionTypeRelative: Entity? = null
    override var sprite: BufferedImage? = null
    override var delete: Boolean = false

    var maxHp: Int = 100
    var hp: Int = 100

    fun drawHpBar(g: Graphics2D) {
        val healthBar = BarGui()
        healthBar.drawingPositionType = DrawablePosition.RELATIVE
        healthBar.drawingPositionTypeRelative = this
        healthBar.width = width
        healthBar.height = 5
        healthBar.position = Vector2(0.0, -8.0)
        healthBar.filled = hp
        healthBar.maxFilled = maxHp
        healthBar.draw(g)
    }
}