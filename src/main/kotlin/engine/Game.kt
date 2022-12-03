package engine

import engine.entity.CollidableEntity
import engine.entity.Entity
import engine.entity.MovableEntity
import engine.entity.enums.DrawablePosition
import engine.entity.factory.EntityFactory
import engine.entity.gui.BarGui
import engine.entity.gui.Gui
import engine.entity.gui.StringGui
import engine.entity.map.Map
import engine.entity.mob.Hero
import engine.maths.Vector2
import java.awt.Color
import java.awt.Graphics2D

class Game {
    val hero: Hero = Hero()
    val map: Map = Map()
    val gui: Gui = Gui()
    val staticEntities: MutableList<Entity> = mutableListOf()
    val movableEntities: MutableList<MovableEntity> = mutableListOf()
    val collidableEntities: MutableList<CollidableEntity> = mutableListOf()

    val ENEMIES_PER_WAVE = 6
    var wave: Int = 1

    fun init() {
        map.init()
        gui.init()
    }

    fun entities(): MutableList<Entity> {
        val e = mutableListOf<Entity>()
        e.addAll(staticEntities)
        e.addAll(movableEntities)
        e.addAll(collidableEntities)
        return e
    }

    fun handleStateChange() {

        when (GameEngine.state) {
            EnginState.MAIN_MENU -> {
                if (GameEngine.input.userInputEnter || GameEngine.input.userInputEscape) {
                    GameEngine.state = EnginState.PLAYING
                }
            }
            EnginState.PLAYING -> {
                if (GameEngine.input.userInputEscape) {
                    GameEngine.state = EnginState.MAIN_MENU
                }
            }
            else -> {}
        }
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
        hero.move()
    }

    fun checkCollisions() {
//        Logger.log("check collisions")

        collidableEntities.forEach {
            it.checkCollisionBetweenEnemies()
        }

        hero.checkCollisionWithEnemies()

    }

    fun handleDeaths() {
        entities().removeIf { it.hp <= 0  }

        if (hero.hp <= 0) {
            hero.hp = hero.maxHp
        }
    }

    fun drawMainMenu(g: Graphics2D) {
//        g.color = Color.DARK_GRAY
//        g.fillRect(0, 0, GameEngine.window.WIDTH, GameEngine.window.HEIGHT)
        val string = StringGui("Appuyez sur [ENTRER] pour jouer", DrawablePosition.CENTERED, null, Color.WHITE, Color.BLACK)
        string.draw(g)
    }

    fun draw(g: Graphics2D) {
        if (GameEngine.state == EnginState.PLAYING) {
            // first layer
            map.draw(g)

            collidableEntities.forEach { it.draw(g) }
            movableEntities.forEach { it.draw(g) }
            staticEntities.forEach { it.draw(g) }
            hero.draw(g)

            // last layer
            gui.draw(g)
        } else {
            drawMainMenu(g)
        }
    }
}