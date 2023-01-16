package engine.math

import engine.entity.Entity

object MathUtility {
    fun collides(e1: Entity, e2: Entity): Boolean {
        return e1.center().distance(e2.center()) < e1.width/2 + e2.width/2
    }
    fun collides(e1: Entity, e2: Entity, r: Int): Boolean {
        return e1.center().distance(e2.center()) < e1.width/2 + e2.width/2 + r
    }
}