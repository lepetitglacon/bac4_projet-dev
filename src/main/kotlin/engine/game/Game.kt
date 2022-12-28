package engine.game

import engine.GameEngine
import engine.entity.gui.Gui
import engine.entity.gui.MainMenuGui
import engine.entity.gui.OptionMenuGui
import engine.entity.map.Map
import engine.entity.mob.enemy.Enemy
import engine.entity.mob.Hero
import engine.entity.mob.enemy.EnemyFactory
import engine.event.input.InputEvent
import engine.event.input.InputListener
import engine.event.input.InputListenerType
import engine.resource.SpriteFactory
import java.awt.Graphics2D

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
        // resources
        SpriteFactory.registerSprites()

        // entities
        map = Map()
        map?.build()
        hero = Hero()

        // GUI
        mainMenu = MainMenuGui()
        optionMenu = OptionMenuGui()
//        gameOverMenu = Gui()
//        shopMenu = Gui()

        enemies.addAll(EnemyFactory.createFromRegistrer())

        // Event binding
        GameEngine.inputListenerManager.subAll(mainMenu as MainMenuGui)
        GameEngine.inputListenerManager.subAll(optionMenu as OptionMenuGui)
        GameEngine.inputListenerManager.subAll(this)
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
        map?.draw(g)
        hero?.draw(g)
        enemies.forEach { it.draw(g) }

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