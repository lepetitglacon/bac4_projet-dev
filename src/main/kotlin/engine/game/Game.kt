package engine.game

import engine.entity.gui.Gui
import engine.entity.map.Map
import engine.entity.mob.Enemy
import engine.entity.mob.Hero

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

        mainMenu = Gui()
        gameOverMenu = Gui()
        shopMenu = Gui()
    }

    fun update()
    {
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

    fun draw()
    {

    }
}