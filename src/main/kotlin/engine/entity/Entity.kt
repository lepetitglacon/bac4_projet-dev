package engine.entity

import engine.entity.sprite.Sprite
import java.awt.Graphics2D
import java.awt.Point
import java.awt.Rectangle

abstract class Entity : Rectangle()
{
    abstract var speed: Int
    abstract var sprite: Sprite

    abstract fun xFromHero()
    abstract fun yFromHero()
    abstract fun collides(entity: Entity)
    abstract fun move()
    abstract fun update()
    abstract fun draw(g: Graphics2D)

    /**
     * return the center of the entity
     */
     fun center(): Point
    {
        return Point(x + width / 2, y + height / 2)
    }
}