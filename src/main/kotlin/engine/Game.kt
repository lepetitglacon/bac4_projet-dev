package engine

import engine.entity.Entity
import engine.entity.enums.DrawablePosition
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
    val movableEntity: MutableList<Entity> = mutableListOf()

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

    fun moveEntities() {
        movableEntity.forEach { it.move() }
        hero.move()
    }

    fun checkCollisions() {
//        Logger.log("check collisions")
    }

    fun drawMainMenu(g: Graphics2D) {
        g.color = Color.DARK_GRAY
        g.fillRect(0, 0, GameEngine.window.WIDTH, GameEngine.window.HEIGHT)
        val string = StringGui("Appuyez sur [ENTRER] pour jouer", DrawablePosition.CENTERED, null, Color.WHITE, Color.BLACK)
        string.draw(g)
    }

    fun draw(g: Graphics2D) {
//        Logger.log("draw entities")

        if (GameEngine.state == EnginState.PLAYING) {
            map.draw(g)
            staticEntities.forEach { it.draw(g) }
            movableEntity.forEach { it.draw(g) }
            hero.draw(g)

            val healthBar = BarGui()
            healthBar.width = GameEngine.window.WIDTH/2
            healthBar.height = 32
            healthBar.startPosition = Vector2(GameEngine.window.center.x, (GameEngine.window.HEIGHT - healthBar.height - 50).toDouble())
            healthBar.filled = hero.hp
            healthBar.maxFilled = 1400
            healthBar.draw(g)

            val xpBar = BarGui()
            xpBar.width = GameEngine.window.WIDTH/2
            xpBar.height = 8
            xpBar.startPosition = Vector2(GameEngine.window.center.x, (GameEngine.window.HEIGHT - healthBar.height - xpBar.height - 55).toDouble())
            xpBar.filled = 650
            xpBar.maxFilled = 1400
            xpBar.color = Color(33, 160, 232)
            xpBar.completingColor = Color(3, 52, 80)
            xpBar.draw(g)

        } else {
            drawMainMenu(g)
        }

    }
}