package engine.entity

import java.awt.Point
import java.awt.Rectangle

abstract class Entity : Rectangle() {
    abstract fun xFromHero()
    abstract fun yFromHero()
    abstract fun collides(entity: Entity)


    /**
     * return the center of the entity
     */
     fun center(): Point
    {
        return Point(x + width / 2, y + height / 2)
    }
}