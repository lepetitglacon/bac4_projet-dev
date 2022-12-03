package engine

import engine.entity.CollidableEntity
import engine.entity.Entity
import engine.entity.MovableEntity
import engine.entity.enums.DrawablePosition
import engine.entity.factory.EntityFactory
import engine.entity.gui.BarGui
import engine.entity.gui.StringGui
import engine.entity.map.Map
import engine.entity.mob.Hero
import engine.maths.Vector2
import java.awt.Color
import java.awt.Graphics2D

class Game {
    val hero: Hero = Hero()
    val map: Map = Map()
    val staticEntities: MutableList<Entity> = mutableListOf()
    val movableEntities: MutableList<MovableEntity> = mutableListOf()
    val collidableEntities: MutableList<CollidableEntity> = mutableListOf()

    val ENEMIES_PER_WAVE = 6
    var wave: Int = 1

    fun init() {
        map.init()
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

    }

    fun drawMainMenu(g: Graphics2D) {
//        g.color = Color.DARK_GRAY
//        g.fillRect(0, 0, GameEngine.window.WIDTH, GameEngine.window.HEIGHT)
        val string = StringGui("Appuyez sur [ENTRER] pour jouer", DrawablePosition.CENTERED, null, Color.WHITE, Color.BLACK)
        string.draw(g)
    }

    fun draw(g: Graphics2D) {
//        Logger.log("draw entities")

        if (GameEngine.state == EnginState.PLAYING) {
            map.draw(g)
            staticEntities.forEach { it.draw(g) }
            movableEntities.forEach { it.draw(g) }
            collidableEntities.forEach { it.draw(g) }
            hero.draw(g)

            val healthBar = BarGui()
            healthBar.width = GameEngine.window.WIDTH/2
            healthBar.height = 32
            healthBar.position = Vector2(GameEngine.window.center.x, (GameEngine.window.HEIGHT - healthBar.height - 50).toDouble())
            healthBar.filled = hero.hp
            healthBar.maxFilled = hero.maxHp
            healthBar.draw(g)

            val xpBar = BarGui()
            xpBar.width = GameEngine.window.WIDTH/2
            xpBar.height = 8
            xpBar.position = Vector2(GameEngine.window.center.x, (GameEngine.window.HEIGHT - healthBar.height - xpBar.height - 55).toDouble())
            xpBar.filled = hero.xp
            xpBar.maxFilled = hero.xpToNextLevel
            xpBar.color = Color(33, 160, 232)
            xpBar.completingColor = Color(3, 52, 80)
            xpBar.draw(g)

        } else {
            drawMainMenu(g)
        }

    }
}