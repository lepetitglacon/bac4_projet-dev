package engine.entity

import engine.entity.sprite.Sprite
import engine.math.Vector2
import java.awt.Graphics2D
import java.awt.Point
import java.awt.Rectangle

abstract class Entity : Rectangle()
{
    abstract var speed: Int
    abstract var sprite: Sprite

    abstract fun xFromHero(): Int
    abstract fun yFromHero(): Int
    abstract fun collides(entity: Entity) : Boolean
    abstract fun move()
    abstract fun update()
    abstract fun draw(g: Graphics2D)

    fun moveFromKeyboardInput() {

    }

    /**
     * return the center of the entity
     */
     fun center(): Vector2
    {
        return Vector2(Point(x + width / 2, y + height / 2))
    }
}