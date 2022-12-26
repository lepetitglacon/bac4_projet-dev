package engine

import engine.event.input.InputListenerManager
import engine.game.Game
import engine.window.Window
import java.awt.Graphics
import java.awt.Graphics2D
import javax.swing.JPanel
import javax.swing.SwingUtilities
import javax.swing.Timer

object GameEngine : JPanel() {
    // debug
    var debug = false

    // game loop
    val fps: Long = (1_000_000 / 60).toLong()
    var ticksCounter = 0
    var running = false
    val timer: Timer = Timer(1) { run() }

    // objects
    var game: Game? = null
    var window: Window? = null

    // events
    val inputListenerManager = InputListenerManager()

    init
    {
        SwingUtilities.invokeLater {
            window = Window()
            game = Game()

            running = true
            timer.start()
            println("engine running")
        }
    }

    fun run()
    {
        window?.updateTitle()
        game?.update()
        ticksCounter++

        repaint()
    }

    override fun paint(gg: Graphics?) {
        super.paint(gg)
        val g = gg as Graphics2D

        game?.draw(g)
    }
}