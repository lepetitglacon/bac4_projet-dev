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

    val weapons = mutableListOf<Weapon>()
    val hpBar = BarComponent()
    val xpBar = BarComponent()

    override var hp: Int = 100
    override var maxHp: Int = 100

    override var level: Int = 1
    override var xp: Int = 0
    override var maxXp: Int = 100

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
        pos += GameEngine.window.keyboardMovementVector * speed
    }

    override fun update() {
        move()
        weapons.forEach {
            it.update()
        }
        super.update()
    }

    override fun draw(g: Graphics2D) {
        if (GameEngine.debug)
        {
            g.color = Color.black
            g.drawRect(xFromHero(), yFromHero(), width, height)
            g.drawArc(xFromHero(), yFromHero(), width, height, 0, 180)
            g.drawString("$x $y", xFromHero(), yFromHero() - 20)
        }

        g.drawImage((sprite as HeroSprite).get(), null, xFromHero(), yFromHero())
        weapons.forEach { it.draw(g) }
        drawHpBar(g)
        drawXpBar(g)
    }

    fun drawHpBar(g: Graphics2D) {
        hpBar.width = GameEngine.window.WIDTH / 2
        hpBar.height = 25
        hpBar.x = GameEngine.window.WIDTH / 4
        hpBar.y = GameEngine.window.HEIGHT - 150
        hpBar.maxFilled = maxHp
        hpBar.filled = hp
        hpBar.draw(g)
    }

    fun drawXpBar(g: Graphics2D) {
        xpBar.width = GameEngine.window.WIDTH / 2
        xpBar.height = 8
        xpBar.x = GameEngine.window.WIDTH / 4
        xpBar.y = hpBar.y - xpBar.height - 5
        xpBar.maxFilled = maxXp
        xpBar.filled = xp
        xpBar.color = Color(0,191,255)
        xpBar.completingColor = Color(30,144,255)
        xpBar.draw(g)
    }

}