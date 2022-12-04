package engine.entity.map

import engine.GameEngine
import engine.entity.MovableEntity
import engine.entity.enums.DrawablePosition
import engine.entity.enums.MapTilePosition
import engine.entity.factory.SpriteFactory
import engine.maths.Vector2
import java.awt.Graphics2D

class Tile(override var position: Vector2) : MovableEntity() {

    init {
        drawingPositionType = DrawablePosition.RELATIVE_TO_HERO
        drawingPositionTypeRelative = GameEngine.game.hero
        sprite = SpriteFactory.getTilemap(MapTilePosition.GRASS)
    }

    override fun draw(g: Graphics2D) {
        g.drawImage(sprite, null, getDrawingPosition().x, getDrawingPosition().y)
    }

    override fun move() {
        setDrawingPositionFromPosition()
    }
}