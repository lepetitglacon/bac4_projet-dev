package engine

import engine.entities.enums.EngineState
import engine.entities.factory.SpriteFactory
import engine.entities.gui.MainMenuGui
import engine.events.ListenerEventTypeInterface
import engine.events.hero.HeroEventManager
import engine.events.input.InputEventManager
import engine.events.input.InputEventType
import engine.events.input.InputListener
import engine.inputs.InputManager
import engine.logger.Logger
import engine.sound.SoundManager
import engine.window.Window
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.event.ComponentAdapter
import java.awt.event.ComponentEvent
import javax.swing.JPanel
import javax.swing.KeyStroke
import javax.swing.SwingUtilities
import javax.swing.Timer


object GameEngine : JPanel(), InputListener {
    private const val FRAME_PER_SECOND = 60
    private const val FRAME_PER_MILLISECOND = 1

    val inputEventManager = InputEventManager()
    val heroEventManager = HeroEventManager()

    var debug = false
    var ticks = 0
    var state: EngineState = EngineState.MAIN_MENU

    val window = Window()
    val game = Game()
    val gui = MainMenuGui()
    val input = InputManager()
    val timer: Timer = Timer(FRAME_PER_MILLISECOND) { run() }

    init {
        // init window
        SwingUtilities.invokeLater {
            window.init()
            window.addComponentListener(object : ComponentAdapter() {
                override fun componentResized(e: ComponentEvent) {
                    window.WIDTH = e.component.width
                    window.HEIGHT = e.component.height
                    game.map.onWindowResize()
                }
            })
        }

        // load assets
        SpriteFactory.loadFiles()
        SoundManager.loadFiles()

        // Bind events
        inputEventManager.sub(this, mutableSetOf(InputEventType.ENTER,InputEventType.SPACE,InputEventType.ESCAPE))

        // init game
        game.init()
        timer.start()
        Logger.info("Engine running")
    }

    fun run() {
        input.getMouseLocation()
        input.getKeyboardMovement()
        handleStateChangeByUserInput()

        when (state) {
            EngineState.MAIN_MENU -> {

            }
            EngineState.PLAYING -> {
                if (!game.initialized) game.init()
                game.run()
            }
            EngineState.GAME_OVER -> {

            }
        }

        ticks++
        repaint()
    }

    fun handleStateChangeByUserInput() {
        when (state) {
            EngineState.MAIN_MENU -> {
                if (input.userInputEnter || input.userInputEscape) {
                    state = EngineState.PLAYING
                    SoundManager.play("main song")
                }
            }
            EngineState.PLAYING -> {
                if (input.userInputEscape) {
                    state = EngineState.MAIN_MENU
                }
            }
            EngineState.GAME_OVER -> {
                if (input.userInputEnter) {
                    state = EngineState.MAIN_MENU
                    SoundManager.stop("death")
                    game.reset()
                }
            }
        }
    }

    override fun paint(gg: Graphics?) {
        super.paint(gg)
        val g = gg as Graphics2D

        when (state) {
            EngineState.MAIN_MENU -> {
                gui.drawMainMenu(g)
            }
            EngineState.PLAYING -> {
                game.draw(g)
            }
            EngineState.GAME_OVER -> {
                gui.drawGameOver(g)
            }
        }
    }

    override fun onEscape() {
        println("Escape")
    }

    override fun onEnter() {
        println("Enter")
    }

    override fun onSpace() {
        println("Space")
    }

    override fun onClick() {
        TODO("Not yet implemented")
    }

    override fun on(e: ListenerEventTypeInterface) {
        if (e !is InputEventType) Logger.warning("send wrong event to Input event : $e")
        when (e) {
            InputEventType.ENTER -> onEnter()
            InputEventType.SPACE -> onSpace()
            InputEventType.ESCAPE -> onEscape()
        }
    }
}