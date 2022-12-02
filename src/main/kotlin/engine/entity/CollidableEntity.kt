package engine.entity

import engine.GameEngine

abstract class CollidableEntity : MovableEntity() {

    private fun repulse(entity: CollidableEntity) {
        val distance = position.distance(entity.position)
        val rayon = width/2
        val overlap = (distance - rayon) / 2

        println("-------")
        println("dist $distance")
        println("over $overlap")
        println("newx ${overlap * (position.x - entity.position.x) / distance}")
        println("posx ${position.x - overlap * (position.x - entity.position.x) / distance}")



        position.x -= overlap * (position.x - entity.position.x) / distance
        position.y -= overlap * (position.y - entity.position.y) / distance
//
//        entity.position.x += overlap * (position.x - entity.position.x) / distance
//        entity.position.y += overlap * (position.y - entity.position.y) / distance
    }

    fun collides(collidableEntity: CollidableEntity): Boolean {
        return position.distance(collidableEntity.position) < width/2
    }

    fun checkCollisionBetweenEnemies() {
        GameEngine.game.collidableEntities.forEach {
            if (it !== this && collides(it)) {
//                println("collides $position, ${it.position}, ${position.distance(it.position)}")
                repulse(it)
            }
        }
    }

    abstract override fun move()

}