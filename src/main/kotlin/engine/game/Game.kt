package engine.game

import engine.GameEngine
import engine.entity.gui.Gui
import engine.entity.gui.MainMenuGui
import engine.entity.gui.OptionMenuGui
import engine.entity.map.Map
import engine.entity.mob.Enemy
import engine.entity.mob.Hero
import engine.event.input.InputEvent
import engine.event.input.InputListener
import engine.event.input.InputListenerType
import java.awt.Graphics2D
import java.awt.event.KeyEvent

class Game : InputListener {
    var map: Map? = null
    var hero: Hero? = null
    var enemies = mutableListOf<Enemy>()

    var state: GameState = GameState.MAIN_MENU

    var mainMenu: Gui? = null
    var optionMenu: Gui? = null
    var gameOverMenu: Gui? = null
    var shopMenu: Gui? = null

    init
    {
        // entities
        map = Map()
        hero = Hero()

        // GUI
        mainMenu = MainMenuGui()
        optionMenu = OptionMenuGui()
//        gameOverMenu = Gui()
//        shopMenu = Gui()

        // Event binding
        GameEngine.inputListenerManager.subAll(this)
        GameEngine.inputListenerManager.subAll(mainMenu as MainMenuGui)
        GameEngine.inputListenerManager.subAll(optionMenu as OptionMenuGui)
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
            GameState.OPTIONS -> optionMenu?.update()
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
        if (state == GameState.OPTIONS) {
            optionMenu?.draw(g)
        }
    }

    fun updateState(e: InputEvent?) {
        // input handler
        if (e != null) {
            when (state)
            {
                GameState.MAIN_MENU -> {
                    if (e.type == InputListenerType.ENTER)
                    {
                        e.consumed = true
                        state = GameState.PLAY
                    }
                }
                GameState.PLAY -> {
                    if (e.type == InputListenerType.ESCAPE)
                    {
                        e.consumed = true
                        state = GameState.OPTIONS
                    }
                }
//                GameState.PLAY_SHOP -> if (e.type == InputListenerType.ENTER) state = GameState.PLAY
                GameState.OPTIONS -> if (e.type == InputListenerType.ESCAPE) state = GameState.PLAY
//                GameState.GAME_OVER -> if (e.type == InputListenerType.ENTER) state = GameState.MAIN_MENU
                else -> println("unknown state change")
            }
        }
    }

    override fun onInputEvent(e: InputEvent) {
        super.onInputEvent(e)
        updateState(e)
    }

}