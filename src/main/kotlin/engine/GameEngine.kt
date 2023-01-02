package engine

import engine.entity.gui.ButtonMenu
import engine.entity.gui.component.WindowGui
import engine.entity.gui.component.button.ExitGameButton
import engine.entity.gui.component.button.GoToMainMenuButton
import engine.entity.gui.component.button.NewGameButton
import engine.entity.gui.component.button.ResumeButton
import engine.entity.mob.enemy.EnemyType
import engine.entity.registrer.EnemyRegistrer
import engine.entity.registrer.EnemyRegistrerType
import engine.event.input.InputEvent
import engine.event.input.InputListener
import engine.event.input.InputListenerManager
import engine.event.input.InputListenerType
import engine.event.movement.hero.HeroMovementListenerManager
import engine.game.Game
import engine.resource.SpriteFactory
import engine.window.Window
import java.awt.Graphics
import java.awt.Graphics2D
import javax.swing.JPanel
import javax.swing.SwingUtilities
import javax.swing.Timer

object GameEngine : JPanel(), InputListener {
    // debug
    var debug = true

    // vars
    var state: EngineState = EngineState.MAIN_MENU
    var ticksCounter = 0

    // game loop
    val timer: Timer = Timer(1) { run() }

    var game: Game? = null
    var window: Window = Window()
    val enemyRegistrer = EnemyRegistrer()

    // event managers
    val inputListenerManager = InputListenerManager()
    val heroMovementListenerManager = HeroMovementListenerManager()

    // objects
    var mainMenu: ButtonMenu = ButtonMenu("Menu", mutableListOf(NewGameButton(), ExitGameButton()), WindowGui(150,150), mutableListOf(EngineState.MAIN_MENU))
    var optionMenu: ButtonMenu = ButtonMenu("Pause", mutableListOf(ResumeButton(), GoToMainMenuButton(), NewGameButton(), ExitGameButton()), WindowGui(150,150), mutableListOf(EngineState.OPTIONS))
    var gameOverMenu: ButtonMenu = ButtonMenu("Game over", mutableListOf(NewGameButton(), NewGameButton()), WindowGui(150,150), mutableListOf(EngineState.GAME_OVER))

    init
    {
        // enemy registration
        enemyRegistrer.add(EnemyRegistrerType("warrior", EnemyType.WARRIOR, 0, 100, 100, .5, SpriteFactory.get("hero"), 48, 48))
        enemyRegistrer.add(EnemyRegistrerType("warrior_1", EnemyType.WARRIOR, 2, 150, 150, .5, SpriteFactory.get("pokemons"), 32, 32))

        SwingUtilities.invokeLater {
            window.init()
            timer.start()

            // event binding
            inputListenerManager.sub(mainMenu)
            inputListenerManager.sub(optionMenu)
            inputListenerManager.sub(this)

            println("engine running")
        }
    }

    fun run()
    {
        window.updateTitle()
        window.getKeyboardMovementInput()

        when (state)
        {
            EngineState.MAIN_MENU -> {}
            EngineState.PLAY -> game?.update()
            EngineState.OPTIONS -> {}
            EngineState.GAME_OVER -> {}
        }

        repaint()
        ticksCounter++
    }

    override fun paint(gg: Graphics?) {
        super.paint(gg)
        val g = gg as Graphics2D

        game?.draw(g)
        if (state == EngineState.MAIN_MENU) mainMenu.draw(g)
        if (state == EngineState.OPTIONS) optionMenu.draw(g)
    }

    override fun onInputEvent(e: InputEvent) {
        super.onInputEvent(e)
        when (state) {
            EngineState.MAIN_MENU -> {}
            EngineState.PLAY -> if (e.type == InputListenerType.ESCAPE) state = EngineState.OPTIONS
            EngineState.OPTIONS -> if (e.type == InputListenerType.ESCAPE) state = EngineState.PLAY
            EngineState.GAME_OVER -> {}
        }

    }
}