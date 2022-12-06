package engine.entities

import engine.GameEngine

abstract class CollidableEntity : MovableEntity() {

    private fun repulseNotToCollide(entity: CollidableEntity) {
        val distance = position.distance(entity.position)
        val rayon = width
        val overlap = (distance - rayon) / 2
        position.x -= overlap * (position.x - entity.position.x) / distance
        position.y -= overlap * (position.y - entity.position.y) / distance
        entity.position.x += overlap * (position.x - entity.position.x) / distance
        entity.position.y += overlap * (position.y - entity.position.y) / distance
    }

    fun collides(collidableEntity: CollidableEntity): Boolean  = position.distance(collidableEntity.position) < (width + collidableEntity.width) / 2
    fun willCollideWith(e: CollidableEntity): Boolean  = (position + GameEngine.input.userInputVector.normalized() * speed).distance(e.position.clone().translatedTo(GameEngine.game.hero.position, e.speed)) < width

    fun checkCollisionBetweenEnemiesToRepulseThem() {
        GameEngine.game.collidableEntities.forEach {
            if (it != this && collides(it)) {
                repulseNotToCollide(it)
            }
        }
    }

    abstract override fun move()

}