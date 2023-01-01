package engine.entity.mob

import engine.GameEngine
import engine.entity.Entity
import engine.entity.sprite.hero.HeroSprite
import engine.entity.sprite.Sprite
import engine.entity.weapon.Weapon
import engine.entity.weapon.weapon.Gun
import engine.resource.SpriteFactory
import java.awt.Color
import java.awt.Graphics2D

class Hero : Entity()
{
    override var speed: Int = 5
    override var sprite: Sprite = SpriteFactory.get("hero")

    val weapons = mutableListOf<Weapon>()

    fun init() {
        sprite = SpriteFactory.get("hero") as HeroSprite
        weapons.add(Gun())
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

    override fun collides(entity: Entity): Boolean {
        TODO("Not yet implemented")
    }

    override fun move() {
        val pos = GameEngine.window.keyboardMovementVector * speed
        x += pos.x.toInt()
        y += pos.y.toInt()
    }

    override fun update() {
        move()
        weapons.forEach {
            it.update()
        }
    }

    override fun draw(g: Graphics2D) {
        if (GameEngine.debug)
        {
            g.color = Color.black
            g.drawRect(xFromHero(), yFromHero(), width, height)
            g.drawArc(xFromHero(), yFromHero(), width, height, 0, 180)
            g.drawString("$x $y", xFromHero(), yFromHero() - 20)
        }
//        g.drawImage((sprite as HeroSprite).get(), null, xFromHero(), yFromHero())

        weapons.forEach {
            it.draw(g)
        }
    }

}