package engine.entity.weapon.component

import engine.GameEngine
import engine.entity.Entity
import engine.entity.sprite.Sprite
import engine.math.Vector2
import engine.resource.SpriteFactory
import engine.resource.SpriteFactory.TILE_SIZE
import java.awt.Graphics2D

class Projectile : Entity() {
    var direction: Vector2 = Vector2()
    override var speed: Int = 5
    override var sprite: Sprite = SpriteFactory.get("pokemons")

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
        x += (direction.x * speed).toInt()
        y += (direction.y * speed).toInt()
    }

    override fun update() {
        move()
    }

    override fun draw(g: Graphics2D) {
        g.fillOval(xFromHero(), yFromHero(), width, height)
        g.drawImage(sprite.image?.getSubimage(0,0,TILE_SIZE, TILE_SIZE), null, xFromHero(), yFromHero())
    }

}