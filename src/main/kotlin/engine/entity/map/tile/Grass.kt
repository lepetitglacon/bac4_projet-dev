package engine.entity.map.tile

import engine.GameEngine
import engine.entity.Entity
import engine.entity.map.Tile
import engine.entity.sprite.Sprite
import engine.resource.SpriteFactory
import java.awt.Graphics2D

class Grass : Tile() {
    override var speed: Int = 0
    override var sprite: Sprite = SpriteFactory.get("map_grass")

    override fun xFromHero(): Int {
        return x - width/2 - GameEngine.game?.hero?.x!! + GameEngine.window.WIDTH / 2
    }

    override fun yFromHero(): Int {
        return y - height/2 - GameEngine.game?.hero?.y!! + GameEngine.window.HEIGHT / 2
    }

    override fun collides(entity: Entity) {
        TODO("Not yet implemented")
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