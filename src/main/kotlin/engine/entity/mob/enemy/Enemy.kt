package engine.entity.mob.enemy

import engine.entity.Entity
import engine.entity.mob.component.Living

abstract class Enemy : Entity(), Living
{
    fun repulseNotToCollide(e: Enemy) {
        val distance = center().distance(e.center())
        val rayon = width
        val overlap = (distance - rayon) / 2
        val distanceBetweenPos = (pos.clone() - e.pos.clone())
        val d = distanceBetweenPos * overlap / distance
        pos -= d
        e.pos += d
    }

    override fun collides(entity: Entity): Boolean {
        return center().distance(entity.center()) < width
    }
    fun collidesSquare(entity: Entity): Boolean {
        return intersects(entity)
    }
}