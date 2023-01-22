package engine.entity.mob.enemy

import engine.entity.Entity
import engine.entity.gui.component.BarComponent
import engine.entity.mob.component.Attacking
import engine.entity.mob.component.Living
import java.awt.Graphics2D

abstract class Enemy : Entity(), Living, Attacking
{
    open var type: EnemyType = EnemyType.WARRIOR

    val hpBar = BarComponent()
    var xpToGive: Int = 25
    var xpDropRate: kotlin.Double = 0.8

    fun repulseNotToCollide(e: Enemy) {
        val distance = center().distance(e.center())
        val rayon = width
        val overlap = (distance - rayon) / 2
        val distanceBetweenPos = (pos.clone() - e.pos.clone())
        val d = distanceBetweenPos * overlap / distance
        pos -= d
        e.pos += d
    }

    fun drawHpBar(g: Graphics2D) {
        hpBar.width = 64
        hpBar.height = 7
        hpBar.x = xFromHero() - ((hpBar.width - width) / 2)
        hpBar.y = yFromHero() - 15
        hpBar.maxFilled = maxHp.toInt()
        hpBar.filled = hp.toInt()
        hpBar.draw(g)
    }

    fun collidesSquare(entity: Entity): Boolean {
        return intersects(entity)
    }

    override fun toString(): String {
        return "${this.javaClass.name} hp: $hp, "
    }


}