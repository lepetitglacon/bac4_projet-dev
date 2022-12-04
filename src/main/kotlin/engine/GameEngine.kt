package engine

import engine.entity.factory.SpriteFactory
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

    val window = Window()
    val game = Game()
    val input = InputManager()
    val timer: Timer = Timer(FRAME_PER_MILLISECOND) { run() }
    var state: EngineState = EngineState.PLAYING

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
        game.handleStateChangeByUserInput()

        if (state == EngineState.PLAYING) {
            game.createEnemies()
            game.moveEntities()
            game.checkCollisions()
            game.handleHeroLevelUp()
            game.handleDeaths()
        }

        ticks++
        repaint()
    }

    override fun paint(gg: Graphics?) {
        super.paint(gg)
        val g = gg as Graphics2D
        game.draw(g)
    }
}