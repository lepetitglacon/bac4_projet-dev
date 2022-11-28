package interfaces

import Entity

interface CollidableInterface : DrawableInterface {
    fun collides(entity: Entity)

    fun repulse(entity: Entity) {
        val distance = position.distance(entity.position)
        val overlap = (distance - size) / 2
        position.x -= overlap * (position.x - entity.position.x) / distance
        position.y -= overlap * (position.y - entity.position.y) / distance
        entity.position.x += overlap * (position.x - entity.position.x) / distance
        entity.position.y += overlap * (position.y - entity.position.y) / distance
    }
}