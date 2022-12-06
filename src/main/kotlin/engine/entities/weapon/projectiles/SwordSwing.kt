package engine.entities.weapon.projectiles

import engine.GameEngine
import java.awt.Color
import java.awt.Graphics2D
import java.time.Instant

class SwordSwing : Projectile() {
    override var damages: Int = 5
    override var cooldownTime: Long = 3
    override var lastCooldownTime: Instant = Instant.now()

    override fun move() {
        setDrawingPositionFromPosition()
        position = GameEngine.game.hero.position
    }

    override fun draw(g: Graphics2D) {
        if (GameEngine.debug) {
            super.draw(g)
            drawDebugPosition(g)
            g.color = Color.DARK_GRAY
            g.fillOval(getDrawingPosition().x, getDrawingPosition().y, width, height)
        }
        g.drawImage(sprite, null, getDrawingPosition().x, getDrawingPosition().y)
    }
}