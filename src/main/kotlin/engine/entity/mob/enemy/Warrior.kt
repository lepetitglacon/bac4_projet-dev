package engine.entity.mob.enemy

import engine.GameEngine
import engine.entity.Entity
import engine.entity.gui.component.BarComponent
import engine.entity.sprite.Sprite
import engine.entity.sprite.enemy.WarriorSprite
import engine.resource.SpriteFactory
import engine.resource.SpriteFactory.TILE_SIZE
import java.awt.Color
import java.awt.Graphics2D
import kotlin.math.ceil
import kotlin.math.floor

class Warrior : Enemy()
{
    override var hp: Int = 0
    override var maxHp: Int = 0

    override var speed: Int = 2
    override var sprite: Sprite = SpriteFactory.get("warrior")

    val hpBar = BarComponent()

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

        hpBar.maxFilled = maxHp
        hpBar.filled = hp
        hpBar.x = x
        hpBar.y = y - height / 2
        hpBar.width = width
        hpBar.draw(g)

        g.drawImage(sprite.image?.getSubimage(0,0,TILE_SIZE,TILE_SIZE), null, xFromHero(), yFromHero())
        g.color = Color.RED
        g.drawOval(xFromHero() + width/2, yFromHero() + height/2, 10, 10)

    }

    
}