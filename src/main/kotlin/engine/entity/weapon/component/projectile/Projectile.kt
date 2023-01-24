package engine.entity.weapon.component.projectile

import engine.GameEngine
import engine.entity.Entity
import engine.entity.mob.component.Attacking
import engine.entity.mob.component.Living
import engine.entity.mob.enemy.Enemy
import engine.entity.mob.enemy.boss.EnemyBoss
import engine.entity.sprite.Sprite
import engine.math.Vec2
import engine.resource.SpriteFactory
import engine.resource.SpriteFactory.TILE_SIZE
import engine.sound.SoundManager
import java.awt.Graphics2D
import java.time.Instant

open class Projectile : Entity(), Attacking, Living {
    val shotAt: Instant = Instant.now()
    var direction: Vec2 = Vec2()
    override var speed: kotlin.Double = 10.0
    override var sprite: Sprite = SpriteFactory.get("pokemons")

    override val damages: Int = 100
    override var hp: kotlin.Double = 1.0
    override var maxHp: kotlin.Double = 1.0

    val allreadyHitEnemy: MutableList<Enemy> = mutableListOf()

    override fun attack() : Int {
        return damages
    }

    open fun onHit(entity: Entity) {
        when (entity) {
            is Enemy -> {
                if (!allreadyHitEnemy.contains(entity)) {
                    SoundManager.clips["hit"]
                    allreadyHitEnemy.add(entity)
                    entity.applyDamage(damages)
                    hp--
                }
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
            if (it.collides(this)) {
                onHit(it)
            }
        }
        super.update()
    }

    override fun draw(g: Graphics2D) {
        g.fillOval(xFromHero() - width/2, yFromHero() - height/2, width, height)
        g.drawImage(sprite.image?.getSubimage(0,0,TILE_SIZE, TILE_SIZE), null, xFromHero() - 32, yFromHero() - 32)
        super.draw(g)
        if (GameEngine.debug) {
            g.drawLine(GameEngine.window.WIDTH/2, GameEngine.window.HEIGHT/2, (xFromHero() + direction.clone().x * 100).toInt(), (yFromHero() + direction.clone().y * 100).toInt())
        }
    }
}