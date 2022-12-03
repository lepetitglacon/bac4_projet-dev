package engine

import engine.entity.CollidableEntity
import engine.entity.Entity
import engine.entity.MovableEntity
import engine.entity.enums.DrawablePosition
import engine.entity.factory.EntityFactory
import engine.entity.factory.WeaponFactory
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
        hero.weapons.add(WeaponFactory.createStink())

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

    fun handleStateChangeByUserInput() {
        when (GameEngine.state) {
            EngineState.MAIN_MENU -> {
                if (GameEngine.input.userInputEnter || GameEngine.input.userInputEscape) {
                    GameEngine.state = EngineState.PLAYING
                }
            }
            EngineState.PLAYING -> {
                if (GameEngine.input.userInputEscape) {
                    GameEngine.state = EngineState.MAIN_MENU
                }
            }
            EngineState.GAME_OVER -> {
                if (GameEngine.input.userInputEnter) {
                    GameEngine.state = EngineState.MAIN_MENU
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

        hero.weapons.forEach { it.move() }
        hero.move()
    }

    fun checkCollisions() {
//        Logger.log("check collisions")

        collidableEntities.forEach {
            it.checkCollisionBetweenEnemiesToRepulseThem()
        }

        hero.weapons.forEach { it.checkCollisionWithEnemies() }

        hero.checkCollisionWithEnemies()

    }

    fun handleDeaths() {
        staticEntities.removeIf { it.hp <= 0  }
        movableEntities.removeIf { it.hp <= 0  }
        collidableEntities.removeIf { it.hp <= 0  }

        if (hero.hp <= 0) {
            if (GameEngine.debug) {
                hero.hp = hero.maxHp
            } else {
                GameEngine.state = EngineState.GAME_OVER
            }

        }
    }

    fun drawMainMenu(g: Graphics2D) {
        // background
//        g.color = Color.DARK_GRAY
//        g.fillRect(0, 0, GameEngine.window.WIDTH, GameEngine.window.HEIGHT)
        val string = StringGui("Appuyez sur [ENTRER] pour jouer", DrawablePosition.CENTERED, null, Color.WHITE, Color.BLACK)
        string.draw(g)
    }

    fun drawGameOver(g: Graphics2D) {
        g.color = Color.DARK_GRAY
        g.fillRect(0, 0, GameEngine.window.WIDTH, GameEngine.window.HEIGHT)
        val string = StringGui("Game Over", DrawablePosition.CENTERED, null, Color.WHITE, Color.BLACK)
        val s = StringGui("Appuyez sur [ENTRER] pour jouer", DrawablePosition.RELATIVE, string, Color.WHITE, Color.BLACK)
        s.position = Vector2(0.0, -15.0)

        string.draw(g)
        s.draw(g)
    }

    fun draw(g: Graphics2D) {

        when (GameEngine.state) {
            EngineState.PLAYING -> {
                // first layer
                map.draw(g)

                collidableEntities.forEach { it.draw(g) }
                movableEntities.forEach { it.draw(g) }
                staticEntities.forEach { it.draw(g) }


                hero.weapons.forEach { it.draw(g) }
                hero.draw(g)

                // last layer
                gui.draw(g)
            }
            EngineState.MAIN_MENU -> {
                drawMainMenu(g)
            }
            EngineState.GAME_OVER -> {
                drawGameOver(g)
            }
            else -> {
                GameEngine.state = EngineState.MAIN_MENU
            }
        }
    }
}