package engine.entity.mob.enemy

import engine.entity.Entity
import engine.entity.gui.component.BarComponent
import engine.entity.mob.component.Living
import java.awt.Graphics2D

abstract class Enemy : Entity(), Living
{
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
        hpBar.y = yFromHero() - 25
        hpBar.maxFilled = maxHp
        hpBar.filled = hp
        hpBar.draw(g)
    }

    override fun collides(entity: Entity): Boolean {
        return center().distance(entity.center()) < width
    }
    fun collidesSquare(entity: Entity): Boolean {
        return intersects(entity)
    }
}