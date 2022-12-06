package engine.entities.weapon

import engine.GameEngine
import engine.entities.enums.DrawablePosition
import engine.entities.factory.SpriteFactory
import java.awt.Color
import java.awt.Graphics2D
import java.time.Instant

class Stink : Projectile() {
    override var damages: Int = 1
    override var cooldownTime: Long = 2
    override var lastCooldownTime: Instant = Instant.now()

    init {
        width = 100
        height = 100
        position = GameEngine.game.hero.position
        drawingPositionTypeRelative = GameEngine.game.hero
        drawingPositionType = DrawablePosition.CENTERED
        sprite = SpriteFactory.getWeaponSprite("Stink")
    }

    override fun move() {
        setDrawingPositionFromPosition()
        position = GameEngine.game.hero.position
    }

    override fun draw(g: Graphics2D) {
        if (GameEngine.debug) {
            super.draw(g)
            drawDebugPosition(g)
            g.color = Color.GREEN
            g.fillOval(getDrawingPosition().x, getDrawingPosition().y, width, height)
        }

        g.drawImage(sprite, null, getDrawingPosition().x, getDrawingPosition().y)
    }



}