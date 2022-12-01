package engine

import engine.entity.Entity
import engine.entity.enums.DrawablePosition
import engine.entity.gui.StringGui
import engine.entity.map.Map
import engine.entity.mob.Hero
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
//        g.color = Color.WHITE
//        g.drawString("Appuyez sur entrer pour jouer", GameEngine.window.WIDTH / 2, GameEngine.window.HEIGHT/2)
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
        } else {
            drawMainMenu(g)
        }

    }
}