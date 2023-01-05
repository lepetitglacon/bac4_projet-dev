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
    val hpBar = BarComponent()
    val xpBar = BarComponent()

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

    override fun collides(entity: Entity): Boolean {
        return center().distance(entity.center()) < (width/2) + (entity.width/2)
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
        if (GameEngine.debug)
        {
            g.color = Color.black
            g.drawRect(xFromHero(), yFromHero(), width, height)
            g.drawArc(xFromHero(), yFromHero(), width, height, 0, 180)
            g.drawString("$x $y", xFromHero(), yFromHero() - 20)
        }

        g.drawImage(sprite.get(), null, xFromHero(), yFromHero())
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
        xpBar.maxFilled = maxXp.toInt()
        xpBar.filled = xp.toInt()
        xpBar.color = Color(0,191,255)
        xpBar.completingColor = Color(30,144,255)
        xpBar.draw(g)
        g.drawString(level.toString(), xpBar.x + xpBar.width/2, xpBar.y - 5)
        g.drawString(maxXp.toInt().toString(), xpBar.x + xpBar.width + 5, xpBar.y)
    }

}