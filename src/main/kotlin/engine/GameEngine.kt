package engine

import engine.entity.enums.EngineState
import engine.entity.factory.SpriteFactory
import engine.entity.gui.Gui
import engine.entity.gui.MainMenuGui
import engine.input.InputManager
import engine.logger.Logger
import engine.sound.SoundManager
import engine.window.Window
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.event.ComponentAdapter
import java.awt.event.ComponentEvent
import javax.swing.JPanel
import javax.swing.SwingUtilities
import javax.swing.Timer


object GameEngine : JPanel() {
    private const val FRAME_PER_SECOND = 60
    private const val FRAME_PER_MILLISECOND = 1

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

        SpriteFactory.loadFiles()
        SoundManager.loadFiles()

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
}