package engine.entities.particle

import engine.Vector2
import engine.entities.Entity
import java.awt.Color
import java.awt.Graphics2D

class Pane(override val position: Vector2, override val width: Int, override val height: Int,
           override val color: Color
) : AbstractParticleEntity() {
    override fun draw(g: Graphics2D) {
        g.color = color
        g.fillRect(getDrawingPosition().x.toInt(), getDrawingPosition().y.toInt(), width, height)
    }
}