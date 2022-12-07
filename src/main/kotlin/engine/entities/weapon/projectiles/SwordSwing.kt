package engine.entities.weapon.projectiles

import engine.GameEngine
import engine.entities.enums.DrawablePosition
import engine.entities.factory.SpriteFactory
import engine.maths.Vector2
import java.awt.Color
import java.awt.Graphics2D
import java.time.Instant

class SwordSwing(
        override var drawingPosition: Vector2,
        override var width: Int = 50,
        override var height: Int = 50
) : Projectile() {
    override var damages: Int = 100
    override var cooldownTime: Long = 3
    override var lastCooldownTime: Instant = Instant.now()

    init {
        sprite = SpriteFactory.getHeroSprite()
        drawingPositionType = DrawablePosition.RELATIVE_TO_HERO
    }

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
        if (canMakeAction()) g.drawImage(sprite, null, getDrawingPosition().x, getDrawingPosition().y)

    }
}