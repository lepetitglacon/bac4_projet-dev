package engine.entity.weapon.component

import engine.GameEngine
import engine.entity.Entity
import engine.entity.mob.component.Attacking
import engine.entity.mob.component.Living
import engine.entity.mob.enemy.Enemy
import engine.entity.sprite.Sprite
import engine.math.Vec2
import engine.resource.SpriteFactory
import engine.resource.SpriteFactory.TILE_SIZE
import java.awt.Graphics2D

class Projectile : Entity(), Attacking, Living {
    var direction: Vec2 = Vec2()
    override var speed: Int = 2
    override var sprite: Sprite = SpriteFactory.get("pokemons")

    override val damages: Int = 100
    override var hp: Int = 1
    override var maxHp: Int = 1

    override fun xFromHero(): Int {
        return x - width/2 - GameEngine.game?.hero?.x!! + GameEngine.window.WIDTH / 2
    }

    override fun yFromHero(): Int {
        return y - height/2 - GameEngine.game?.hero?.y!! + GameEngine.window.HEIGHT / 2
    }

    override fun collides(entity: Entity): Boolean {
        return center().distance(entity.center()) < (width/2 + entity.width/2)
    }

    fun onHit(entity: Entity) {
        println("hit")
        when (entity) {
            is Enemy -> {
                entity.applyDamage(damages)
                hp--
            }

        }
    }

    override fun move() {
        pos += (direction * speed)
    }

    override fun update() {
        move()
        GameEngine.game?.enemies?.forEach {
            if (collides(it)) onHit(it)
        }
        super.update()
    }

    override fun draw(g: Graphics2D) {
        g.fillOval(xFromHero(), yFromHero(), width, height)
        g.drawImage(sprite.image?.getSubimage(0,0,TILE_SIZE, TILE_SIZE), null, xFromHero(), yFromHero())
    }
}