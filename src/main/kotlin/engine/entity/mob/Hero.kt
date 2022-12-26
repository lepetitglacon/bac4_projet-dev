package engine.entity.mob

import engine.entity.Entity
import engine.entity.sprite.Sprite
import java.awt.Graphics2D

class Hero : Entity()
{
    override var speed: Int = 5
    override var sprite: Sprite = Sprite()

    override fun xFromHero() {
        TODO("Not yet implemented")
    }

    override fun yFromHero() {
        TODO("Not yet implemented")
    }

    override fun collides(entity: Entity) {
        TODO("Not yet implemented")
    }

    override fun move() {

    }

    override fun update() {
        move()
    }

    override fun draw(g: Graphics2D) {

    }

}