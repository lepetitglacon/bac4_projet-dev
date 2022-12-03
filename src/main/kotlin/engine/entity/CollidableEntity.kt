package engine.entity

import engine.GameEngine

abstract class CollidableEntity : MovableEntity() {

    private fun repulse(entity: CollidableEntity) {
        val distance = position.distance(entity.position)
        val rayon = width
        val overlap = (distance - rayon) / 2

        println(distance)

        position.x -= overlap * (position.x - entity.position.x) / distance
        position.y -= overlap * (position.y - entity.position.y) / distance
        entity.position.x += overlap * (position.x - entity.position.x) / distance
        entity.position.y += overlap * (position.y - entity.position.y) / distance
    }

    fun collides(collidableEntity: CollidableEntity): Boolean  = position.distance(collidableEntity.position) < width

    fun checkCollisionBetweenEnemies() {
        GameEngine.game.collidableEntities.forEach {
            if (it != this && collides(it)) {
                repulse(it)
            }
        }
    }

    abstract override fun move()

}