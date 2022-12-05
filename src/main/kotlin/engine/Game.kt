package engine

import engine.entity.CollidableEntity
import engine.entity.Entity
import engine.entity.MovableEntity
import engine.entity.enums.EngineState
import engine.entity.factory.EntityFactory
import engine.entity.factory.SpriteFactory
import engine.entity.factory.WeaponFactory
import engine.entity.gui.Gui
import engine.entity.gui.component.StringGui
import engine.entity.map.Map
import engine.entity.mob.Hero
import engine.maths.Vector2
import engine.sound.SoundManager
import java.awt.Color
import java.awt.Graphics2D
import java.awt.geom.AffineTransform

class Game {
    val hero: Hero = Hero()
    val map: Map = Map()
    val gui: Gui = Gui()
    val staticEntities: MutableList<Entity> = mutableListOf()
    val movableEntities: MutableList<MovableEntity> = mutableListOf()
    val collidableEntities: MutableList<CollidableEntity> = mutableListOf()
    val objects: MutableList<CollidableEntity> = mutableListOf()

    var initialized: Boolean = false


    val ENEMIES_PER_WAVE = 6
    var wave: Int = 1


    fun init() {
        hero.weapons.add(WeaponFactory.createStink())

        SoundManager.play("main song")

        map.init()
        GameEngine.gui.init()
        initialized = true
    }
    
    fun run() {
        createEnemies()
        moveEntities()
        checkCollisions()
        handleHeroLevelUp()
        handleDeaths()
    }

    fun createEnemies() {
        if (collidableEntities.size < wave*ENEMIES_PER_WAVE) {
            for (i in 0..wave*ENEMIES_PER_WAVE) {
                collidableEntities.add(EntityFactory.createRandomEnemy())
            }
        }
    }

    fun moveEntities() {
        movableEntities.forEach { it.move() }
        collidableEntities.forEach { it.move() }

        objects.forEach { it.move() }

        hero.weapons.forEach { it.move() }
        hero.move()
    }

    fun checkCollisions() {
        // remove items
        objects.removeIf { it.delete }

        collidableEntities.forEach {
            it.checkCollisionBetweenEnemiesToRepulseThem()
        }

        hero.weapons.forEach { it.checkCollisionWithEnemies() }

        hero.checkCollisionWithObjects()
        hero.checkCollisionWithEnemies()

    }

    fun handleDeaths() {
        staticEntities.removeIf { it.hp <= 0  }
        movableEntities.removeIf { it.hp <= 0  }
        collidableEntities.removeIf {
            if (it.hp <= 0) {
                objects.add(EntityFactory.createSoul(it.position))
            }
            it.hp <= 0
        }

        if (hero.hp <= 0) {
            if (GameEngine.debug) {
                hero.hp = hero.maxHp
            } else {
                SoundManager.stop("main song")
                SoundManager.play("death")
                GameEngine.state = EngineState.GAME_OVER
            }
        }
    }

    fun handleHeroLevelUp() {
        hero.checkForLevelUp()
    }

    fun draw(g: Graphics2D) {
        map.draw(g)
        objects.forEach { it.draw(g) }

        collidableEntities.forEach { it.draw(g) }
        movableEntities.forEach { it.draw(g) }
        staticEntities.forEach { it.draw(g) }

        hero.weapons.forEach { it.draw(g) }
        hero.draw(g)
        gui.draw(g)
    }
}