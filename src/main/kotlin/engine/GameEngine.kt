package engine

import engine.entity.gui.Gui
import engine.entity.gui.MainMenuGui
import engine.entity.gui.OptionMenuGui
import engine.entity.registrer.EnemyRegistrer
import engine.entity.registrer.EnemyType
import engine.event.input.InputEvent
import engine.event.input.InputListener
import engine.event.input.InputListenerManager
import engine.event.input.InputListenerType
import engine.event.movement.hero.HeroMovementListenerManager
import engine.game.Game
import engine.game.GameFactory
import engine.game.GameState
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

    // event managers
    val inputListenerManager = InputListenerManager()
    val heroMovementListenerManager = HeroMovementListenerManager()

    // objects
    var mainMenu: Gui? = null
    var optionMenu: Gui? = null
    var gameOverMenu: Gui? = null

    var game: Game? = null
    var window: Window = Window()
    val enemyRegistrer = EnemyRegistrer()

    init
    {
        // enemy registration
        enemyRegistrer.add(EnemyType("warrior", 0, 20, 50))
        enemyRegistrer.add(EnemyType("mercenary", 2, 50, 50))

        // GUI
        mainMenu = MainMenuGui()
        optionMenu = OptionMenuGui()
//        gameOverMenu = Gui()
//        shopMenu = Gui()

        // event binding
        inputListenerManager.subAll(mainMenu as MainMenuGui)
        inputListenerManager.subAll(optionMenu as OptionMenuGui)
        inputListenerManager.subAll(this)

        SwingUtilities.invokeLater {
            window.init()
            timer.start()
            println("engine running")
        }
    }

    fun run()
    {
        window.updateTitle()
        window.getKeyboardMovementInput()
        game?.update()

        repaint()
        ticksCounter++
    }

    override fun paint(gg: Graphics?) {
        super.paint(gg)
        val g = gg as Graphics2D

        game?.draw(g)
        if (state == EngineState.MAIN_MENU) mainMenu?.draw(g)
        if (state == EngineState.OPTIONS) optionMenu?.draw(g)
    }

    override fun onInputEvent(e: InputEvent) {
        super.onInputEvent(e)
        when (state) {
            EngineState.MAIN_MENU ->
            {
                if (e.type == InputListenerType.ENTER) {
                    state = EngineState.PLAY
                    game = GameFactory.createGame()
                    game!!.init()
                }
            }
            EngineState.PLAY -> if (e.type == InputListenerType.ESCAPE) state = EngineState.OPTIONS
            EngineState.OPTIONS -> if (e.type == InputListenerType.ESCAPE) state = EngineState.PLAY
            EngineState.GAME_OVER -> TODO()
        }

    }
}