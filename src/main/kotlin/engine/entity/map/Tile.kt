package engine.entity.map

import engine.GameEngine
import engine.entity.Entity
import engine.entity.enums.DrawablePosition
import engine.entity.enums.MapTilePosition
import engine.entity.sprite.Sprite
import engine.maths.Vector2
import java.awt.Graphics2D

class Tile(override var startPosition: Vector2) : Entity() {

    init {
        drawingPosition = DrawablePosition.RELATIVE_TO_HERO
        drawingRelative = GameEngine.game.hero
        sprite = Sprite.getTilemap(MapTilePosition.CENTER)
    }

    override fun draw(g: Graphics2D) {
//        drawDebugPosition(g)
        g.drawImage(sprite, null, getDrawingPosition().x, getDrawingPosition().y)
    }

    override fun move() {

    }
}