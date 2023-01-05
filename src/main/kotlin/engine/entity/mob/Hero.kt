package engine.entity.mob

import engine.GameEngine
import engine.entity.Entity
import engine.entity.gui.component.BarComponent
import engine.entity.mob.component.Leveling
import engine.entity.mob.component.Living
import engine.entity.sprite.hero.HeroSprite
import engine.entity.sprite.Sprite
import engine.entity.weapon.Weapon
import engine.entity.weapon.weapon.Gun
import engine.resource.SpriteFactory
import java.awt.Color
import java.awt.Graphics2D

class Hero : Entity(), Living, Leveling
{
    override var speed: kotlin.Double = 5.0
    override var sprite: Sprite = SpriteFactory.get("hero")

    override var hp: Int = 100
    override var maxHp: Int = 100

    override var level: Int = 1
    override var xp: kotlin.Double = 0.0
    override var maxXp: kotlin.Double = 100.0

    val weapons = mutableListOf<Weapon>()

    val pickupRadius: kotlin.Double = width.toDouble()

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

    override fun move() {
        pos += GameEngine.window.keyboardMovementVector * speed
    }

    override fun update() {
        // move
        move()
        super.update()

        // update weapon
        weapons.forEach { it.update() }

        // check for levelup
        if (xp >= maxXp) levelUp(1.2)
    }

    override fun draw(g: Graphics2D) {
        g.drawImage(sprite.get(), null, xFromHero(), yFromHero())
        weapons.forEach { it.draw(g) }
        super.draw(g)
    }

}