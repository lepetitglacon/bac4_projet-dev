package engine.entity.mob.enemy

import engine.GameEngine
import engine.entity.Entity
import engine.entity.gui.component.BarComponent
import engine.entity.sprite.Sprite
import engine.entity.sprite.enemy.WarriorSprite
import engine.resource.SpriteFactory
import engine.resource.SpriteFactory.TILE_SIZE
import java.awt.Graphics2D

class Warrior : Enemy()
{
    override var speed: Int = 2
    override var sprite: Sprite = SpriteFactory.get("warrior")

    override fun xFromHero(): Int {
        return x - width/2 - GameEngine.game?.hero?.x!! + GameEngine.window.WIDTH / 2
    }

    override fun yFromHero(): Int {
        return y - height/2 - GameEngine.game?.hero?.y!! + GameEngine.window.HEIGHT / 2
    }

    override fun move() {
        var pos = (center() - GameEngine.game?.hero?.center()!!).normalized()
        println("norma ${pos.x}x ${pos.y}y")
        pos *= speed
        println(" add to  pos ${pos.x}x ${pos.y}y")
        println()
        x -= pos.x.toInt()
        y -= pos.y.toInt()
    }

    override fun update() {
        move()
        //GameEngine.game?.enemies?.forEach { if (collides(it)) repulseNotToCollide(it) }
    }

    override fun draw(g: Graphics2D) {
        if (GameEngine.debug) {
            g.drawRect(xFromHero(), yFromHero(), width, height)
            g.drawString("$x $y", xFromHero(), yFromHero() - 20)
        }
        val hpBar = BarComponent()
        hpBar.x = x
        hpBar.y = y - height / 2
        hpBar.width = width
        hpBar.draw(g)

        g.drawImage(sprite.image?.getSubimage(0,0,TILE_SIZE,TILE_SIZE), null, xFromHero(), yFromHero())
    }

    
}