package engine.entity.item

import engine.GameEngine
import engine.entity.CollidableEntity
import engine.entity.enums.DrawablePosition
import engine.entity.factory.SpriteFactory
import engine.maths.Vector2
import java.awt.Color
import java.awt.Graphics2D

class Soul(override var position: Vector2) : CollidableEntity() {
    val xp: Int = 25

    init {
        width = 32
        height = 32
        sprite = SpriteFactory.getSoul()
        drawingPositionType = DrawablePosition.RELATIVE_TO_HERO
    }

    override fun draw(g: Graphics2D) {
        if (GameEngine.debug) {
            g.drawString("$position, $drawingPosition", getDrawingPosition().x, getDrawingPosition().y)
            g.color = Color.PINK
            g.fillOval(getDrawingPosition().x, getDrawingPosition().y, width, height)
        }
        g.drawImage(sprite, null, getDrawingPosition().x - width/2, getDrawingPosition().y - height/2)
    }

    override fun move() {
        setDrawingPositionFromPosition()
    }
}