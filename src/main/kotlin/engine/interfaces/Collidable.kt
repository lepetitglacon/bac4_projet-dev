package engine.interfaces

import engine.Vector2
import engine.entities.Entity
import engine.entities.physical.Oval
import engine.entities.physical.Rectangle
import engine.interfaces.drawable.Drawable
import kotlin.math.abs
import kotlin.math.pow

interface Collidable : Drawable {

    fun collides(e: Entity): Boolean {
        when (true) {
            (e is Oval) -> {
                val circleDistance = Vector2()
                circleDistance.x = abs(e.position.x - position.x);
                circleDistance.y = abs(e.position.y - position.y);

                if (circleDistance.x > (width/2 + e.radius)) { return false; }
                if (circleDistance.y > (height/2 + e.radius)) { return false; }

                if (circleDistance.x <= (width/2)) { return true; }
                if (circleDistance.y <= (height/2)) { return true; }

                val cornerDistanceSqrt = (circleDistance.x - width/2).pow(2) + (circleDistance.y - height/2).pow(2)

                return cornerDistanceSqrt <= ((e.radius.toDouble()).pow(2))
            }
            (e is Rectangle) -> {
                return (position.x < e.position.x + e.width &&
                    position.x + width > e.position.x &&
                    position.y < e.position.y + e.height &&
                    height + position.y > e.position.y)
            }
            else -> {return false}
        }
    }
}