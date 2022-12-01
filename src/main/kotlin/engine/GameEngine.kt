package engine

import engine.entity.factory.EntityFactory
import engine.input.InputManager
import engine.logger.Logger
import engine.maths.Vector2
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
    private const val FRAME_PER_MILLISECOND = FRAME_PER_SECOND / 1000

    var debug = true
    var debugShowCenter = true
    var ticks = 0

    val window = Window()
    val game = Game()
    val input = InputManager()
    val timer: Timer = Timer(FRAME_PER_MILLISECOND) { run() }
    var state: EnginState = EnginState.MAIN_MENU

    init {
        // init window
        SwingUtilities.invokeLater {
            window.init()
            window.addComponentListener(object : ComponentAdapter() {
                override fun componentResized(e: ComponentEvent) {
                    window.WIDTH = e.component.width
                    window.HEIGHT = e.component.height
                }
            })
        }
        // init du jeu
        game.init()
        // launch timer
        timer.start()
        Logger.info("Engine running")
        game.movableEntity.add(EntityFactory.createEnemyFromPosition(Vector2(35.0, 35.0)))
    }

    fun run() {
        input.getKeyboardMovement()
        game.handleStateChange()
        game.moveEntities()
        game.checkCollisions()
        repaint()
    }

    override fun paint(gg: Graphics?) {
        super.paint(gg)
        val g = gg as Graphics2D
        if (debugShowCenter) {
            g.drawLine(
                window.WIDTH / 2, 0,
                window.WIDTH / 2, window.HEIGHT
            )
            g.drawLine(
                0, window.HEIGHT/2,
                window.WIDTH, window.HEIGHT/2
            )
        }
        game.draw(g)
    }
}