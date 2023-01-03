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

open class Projectile : Entity(), Attacking, Living {
    var direction: Vec2 = Vec2()
    override var speed: kotlin.Double = 10.0
    override var sprite: Sprite = SpriteFactory.get("pokemons")

    override val damages: Int = 100
    override var hp: Int = 1
    override var maxHp: Int = 1

    override fun attack() : Int {
        return damages
    }

    override fun xFromHero(): Int {
        return x - width/2 - GameEngine.game?.hero?.x!! + GameEngine.window.WIDTH / 2
    }

    override fun yFromHero(): Int {
        return y - height/2 - GameEngine.game?.hero?.y!! + GameEngine.window.HEIGHT / 2
    }

    override fun collides(entity: Entity): Boolean {
        return center().distance(entity.center()) < (entity.width/2) + (width/2)
    }

    fun onHit(entity: Entity) {
        when (entity) {
            is Enemy -> {
                entity.applyDamage(damages)
                hp--
            }
            else -> { println("bullet hit something unknown") }

        }
    }

    override fun move() {
        pos += (direction.clone() * speed)
    }

    override fun update() {
        move()
        GameEngine.game?.enemies?.forEach {
            if (it.collides(this)) onHit(it)
        }
        super.update()
    }

    override fun draw(g: Graphics2D) {
        g.fillOval(xFromHero(), yFromHero(), width, height)
        g.drawImage(sprite.image?.getSubimage(0,0,TILE_SIZE, TILE_SIZE), null, xFromHero(), yFromHero())
    }
}