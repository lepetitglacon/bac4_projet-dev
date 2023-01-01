package engine.entity.mob.enemy

import engine.entity.Entity
import engine.math.Vector2

abstract class Enemy : Entity()
{
    fun repulseNotToCollide(e: Enemy) {
        val distance = center().distance(e.center())
        val rayon = width
        val overlap = (distance - rayon) / 2
        x -= (overlap * (x - e.center().x) / distance).toInt()
        y -= (overlap * (y - e.center().y) / distance).toInt()
        e.center().x += overlap * (x - e.center().x) / distance
        e.center().y += overlap * (y - e.center().y) / distance
    }

    override fun collides(entity: Entity): Boolean {
        return center().distance(entity.center()) < (width + entity.width) / 2
    }
}