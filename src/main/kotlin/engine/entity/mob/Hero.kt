package engine.entity.mob

import engine.GameEngine
import engine.entity.Entity
import engine.entity.sprite.hero.HeroSprite
import engine.entity.sprite.Sprite
import engine.resource.SpriteFactory
import java.awt.Color
import java.awt.Graphics2D

class Hero : Entity()
{
    override var speed: Int = 5
    override var sprite: Sprite = SpriteFactory.get("hero")

    init {
        x = 0
        y = 0
        width = 64
        height = 64
    }

    override fun xFromHero(): Int {
        return GameEngine.window?.WIDTH!! / 2 - width / 2
    }

    override fun yFromHero(): Int {
        return GameEngine.window?.HEIGHT!! / 2 - height / 2
    }

    override fun collides(entity: Entity) {
        TODO("Not yet implemented")
    }

    override fun move() {
        x += GameEngine.window.keyboardMovementVector.x * speed
        y += GameEngine.window.keyboardMovementVector.y * speed
    }

    override fun update() {
        move()
    }

    override fun draw(g: Graphics2D) {
        if (GameEngine.debug)
        {
            g.color = Color.black
            g.drawRect(xFromHero(), yFromHero(), width, height)
        }

        g.drawImage((sprite as HeroSprite).get(), null, xFromHero(), yFromHero())
    }

}