package engine.game

import engine.GameEngine
import engine.entity.gui.Gui
import engine.entity.gui.MainMenuGui
import engine.entity.map.Map
import engine.entity.mob.Enemy
import engine.entity.mob.Hero
import java.awt.Graphics2D
import java.awt.event.KeyEvent

class Game {
    var map: Map? = null
    var hero: Hero? = null
    var enemies = mutableListOf<Enemy>()

    var state: GameState = GameState.MAIN_MENU

    var mainMenu: Gui? = null
    var gameOverMenu: Gui? = null
    var shopMenu: Gui? = null

    init
    {
        map = Map()
        hero = Hero()

        mainMenu = MainMenuGui()
        GameEngine.inputListenerManager.subAll(mainMenu as MainMenuGui)
//        gameOverMenu = Gui()
//        shopMenu = Gui()
    }

    fun update()
    {
        updateState(null)

        when(state)
        {
            GameState.MAIN_MENU ->
            {
                mainMenu?.update()
            }
            GameState.PLAY ->
            {
                map?.update()
                hero?.update()
                enemies.forEach { it.update() }
            }
            GameState.PLAY_SHOP -> TODO()
            GameState.OPTIONS -> TODO()
            GameState.GAME_OVER -> TODO()
        }

    }

    fun draw(g: Graphics2D)
    {
        hero?.draw(g)
        enemies.forEach { it.draw(g) }
        map?.draw(g)

        if (state == GameState.MAIN_MENU) {
            mainMenu?.draw(g)
        }
    }

    fun updateState(e: KeyEvent?) {
        if (e != null) {
            when (state)
            {
                GameState.MAIN_MENU -> if (e.keyCode == KeyEvent.VK_ENTER) state = GameState.PLAY
                GameState.PLAY -> if (e.keyCode == KeyEvent.VK_ESCAPE) state = GameState.OPTIONS
                GameState.PLAY_SHOP -> if (e.keyCode == KeyEvent.VK_ENTER) state = GameState.PLAY
                GameState.OPTIONS -> if (e.keyCode == KeyEvent.VK_ESCAPE) state = GameState.PLAY
                GameState.GAME_OVER -> if (e.keyCode == KeyEvent.VK_ENTER) state = GameState.MAIN_MENU
                else -> println("unknown state change")
            }
        }

    }
}