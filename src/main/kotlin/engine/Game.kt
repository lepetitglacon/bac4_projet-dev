package engine

import engine.entity.Entity
import engine.entity.mob.Hero
import engine.logger.Logger
import java.awt.Graphics2D

class Game {
    val hero: Hero = Hero()
    val staticEntities: MutableList<Entity> = mutableListOf()
    val movableEntity: MutableList<Entity> = mutableListOf()

    fun moveEntities() {
        movableEntity.forEach { it.move() }
        hero.move()
    }

    fun checkCollisions() {
        Logger.log("check collisions")
    }

    fun draw(g: Graphics2D) {
        Logger.log("draw entities")
        staticEntities.forEach { it.draw(g) }
        movableEntity.forEach { it.draw(g) }
        hero.draw(g)
    }
}