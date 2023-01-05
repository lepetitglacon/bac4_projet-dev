package engine.entity.mob.enemy

import engine.GameEngine
import engine.entity.gui.component.BarComponent
import engine.entity.sprite.Sprite
import engine.resource.SpriteFactory
import java.awt.Color
import java.awt.Graphics2D

class Warrior : Enemy()
{
    override var hp: Int = 0
    override var maxHp: Int = 0

    override var speed: kotlin.Double = 2.0
    override var sprite: Sprite = SpriteFactory.get("warrior_0")

    override fun move() {
        pos -= (center() - GameEngine.game?.hero?.center()!!).normalized() * speed
    }

    override fun update() {
        move()
        GameEngine.game?.enemies?.forEach {
            if (it != this && collides(it)) repulseNotToCollide(it)
        }
        super.update()
    }

    override fun draw(g: Graphics2D) {
        g.drawImage(sprite.image, null, xFromHero(), yFromHero())
        drawHpBar(g)
        super.draw(g)
    }
}