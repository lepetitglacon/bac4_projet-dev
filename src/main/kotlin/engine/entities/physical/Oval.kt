package engine.entities.physical

import engine.Vector2
import engine.entities.Entity
import java.awt.Color
import java.awt.Graphics2D

class Oval(
    override val position: Vector2,
    override val width: Int,
    override val height: Int,
    override val color: Color
) : AbstractPhysicalEntity() {
    val radius = width /2

    override fun draw(g: Graphics2D) {
        g.color = color
        g.fillOval(getDrawingPosition().x.toInt(), getDrawingPosition().y.toInt(), width, height)
    }

    override fun collides(e: Entity): Boolean {
        return super.collides(e)
    }
}