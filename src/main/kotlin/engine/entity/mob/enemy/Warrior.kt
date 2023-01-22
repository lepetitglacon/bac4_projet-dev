package engine.entity.mob.enemy

import engine.GameEngine
import engine.entity.sprite.Sprite
import engine.resource.SpriteFactory
import java.awt.Graphics2D

class Warrior : Enemy()
{
    override var hp: kotlin.Double = 0.0
    override var maxHp: kotlin.Double = 0.0
    override val damages: Int = 10

    override fun attack(): Int {
        return damages
    }

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