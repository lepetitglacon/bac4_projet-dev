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
    fun willCollideWith(e: CollidableEntity): Boolean  = (position + GameEngine.input.userInputVector.normalized() * speed).distance(e.position.clone().translatedTo(GameEngine.game.hero.position, e.speed)) < width

    fun checkCollisionBetweenEnemies() {
        GameEngine.game.collidableEntities.forEach {
//            if (it != this && willCollideWith(it)) {
//                println("WILL COLLIDE $this $it")
//            }

            if (it != this && collides(it)) {
                repulse(it)
            }
        }
    }

    abstract override fun move()

}