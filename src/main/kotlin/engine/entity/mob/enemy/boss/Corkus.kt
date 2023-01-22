package engine.entity.mob.enemy.boss

import engine.GameEngine
import engine.entity.mob.enemy.EnemyType
import engine.entity.sprite.Sprite
import engine.resource.SpriteFactory
import java.awt.Color
import java.awt.Graphics2D

class Corkus : EnemyBoss() {
    override var hp: kotlin.Double = 1000.0
    override var maxHp: kotlin.Double = 1000.0
    override val damages: Int = 20

    override var type: EnemyType = EnemyType.APOSTLE

    override fun attack(): Int {
        return damages
    }

    override var speed: kotlin.Double = 0.4
    override var sprite: Sprite = SpriteFactory.get("boss_corkus")

    override fun xFromHero(): Int {
        return x - width/2 - GameEngine.game?.hero?.x!! + GameEngine.window.WIDTH / 2
    }

    override fun yFromHero(): Int {
        return y - height/2 - GameEngine.game?.hero?.y!! + GameEngine.window.HEIGHT / 2
    }

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
        if (GameEngine.debug) {
            g.drawRect(xFromHero(), yFromHero(), width, height)
            g.drawString("$x $y", xFromHero(), yFromHero() - 20)
            g.drawOval(xFromHero(), yFromHero(), width, height)
        }

        drawHpBar(g)

        g.drawImage(sprite.image, null, xFromHero() - (sprite.image?.width!!/2 - width/2), yFromHero() - (sprite.image?.height!!/2 - height/2))
        g.color = Color.RED
        g.drawOval(xFromHero() + width/2, yFromHero() + height/2, 10, 10)
    }
}