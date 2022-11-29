package engine.collisions

import engine.Renderer
import engine.Vector2
import engine.enums.DrawablePosition
import engine.interfaces.Callback
import engine.interfaces.Collidable

abstract class Collision : Callback {
    abstract val e1: Collidable
    abstract val e2: Collidable

    override fun onCollision() {
        if (Renderer.logger.collision) {
                Renderer.logger.log("$e1 collide with $e2")
        }
    }
}