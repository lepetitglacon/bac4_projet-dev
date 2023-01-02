package engine.entity

import engine.entity.sprite.Sprite
import engine.math.Vec2
import java.awt.Graphics2D
import java.awt.Point
import java.awt.Rectangle

abstract class Entity : Rectangle()
{
    var pos: Vec2 = Vec2()
    abstract var speed: Int
    abstract var sprite: Sprite

    abstract fun xFromHero(): Int
    abstract fun yFromHero(): Int
    abstract fun collides(entity: Entity) : Boolean
    abstract fun move()
    abstract fun draw(g: Graphics2D)

    open fun update() { setDrawingPosition() }
    fun setDrawingPosition() {
        x = pos.x.toInt()
        y = pos.y.toInt()
    }

    /**
     * return the center of the entity
     */
     fun center(): Vec2
    {
        return Vec2(pos.x + (width / 2), pos.y + (height / 2))
    }
}