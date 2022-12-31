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
    override var speed: Int = 5
    override var sprite: Sprite = SpriteFactory.get("warrior")

    override fun xFromHero(): Int {
        return x - width/2 - GameEngine.game?.hero?.x!! + GameEngine.window.WIDTH / 2
    }

    override fun yFromHero(): Int {
        return y - height/2 - GameEngine.game?.hero?.y!! + GameEngine.window.HEIGHT / 2
    }

    override fun collides(entity: Entity) {
        TODO("Not yet implemented")
    }

    override fun move() {

    }

    override fun update() {
        move()
    }

    override fun draw(g: Graphics2D) {
        if (GameEngine.debug) {
            g.drawRect(xFromHero(), yFromHero(), width, height)
            g.drawString("$x $y", xFromHero(), yFromHero() - 20)
        }
        val hpBar = BarComponent()
        hpBar.x = x
        hpBar.y = y - height
        hpBar.width = width
        hpBar.draw(g)

        g.drawImage(sprite.image?.getSubimage(0,0,TILE_SIZE,TILE_SIZE), null, xFromHero(), yFromHero())
    }


}