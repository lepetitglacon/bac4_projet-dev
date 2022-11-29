package engine.collisions

import engine.interfaces.Collidable

class PushCollision(override val e1: Collidable, override val e2: Collidable) : Collision() {

    override fun onCollision() {
        super.onCollision()

    }
}