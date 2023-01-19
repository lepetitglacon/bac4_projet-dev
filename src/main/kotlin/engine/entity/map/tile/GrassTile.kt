package engine.entity.map.tile

import engine.GameEngine
import engine.entity.map.Tile
import engine.entity.sprite.Sprite
import engine.resource.SpriteFactory
import java.awt.Graphics2D

class GrassTile : Tile() {
    override var speed: kotlin.Double = 0.0
    override var sprite: Sprite = SpriteFactory.get("map_grass_0")

    override fun xFromHero(): Int {
        return x - width/2 - GameEngine.game?.hero?.x!! + GameEngine.window.WIDTH / 2
    }

    override fun yFromHero(): Int {
        return y - height/2 - GameEngine.game?.hero?.y!! + GameEngine.window.HEIGHT / 2
    }

    override fun move() {
        TODO("Not yet implemented")
    }

    override fun update() {
        TODO("Not yet implemented")
    }

    override fun draw(g: Graphics2D) {
        g.drawImage(sprite.image, null, xFromHero(), yFromHero())
    }
}