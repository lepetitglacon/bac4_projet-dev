package engine

import engine.game.Game
import engine.window.Window
import javax.swing.JPanel
import javax.swing.SwingUtilities
import javax.swing.Timer

object GameEngine : JPanel() {
    // debug
    var debug = false

    // game loop
    val fps: Long = (1_000_000 / 60).toLong()
    var running = false
    val timer: Timer = Timer(1) { run() }

    // objects
    var game: Game? = null
    var window: Window? = null

    // events

    init
    {
        SwingUtilities.invokeLater {
            window = Window()
            game = Game()

            running = true
            timer.start()
            run()
        }
    }

    fun run()
    {
        var fpsCounter = 0

        if (System.nanoTime() % fps == 0.toLong()) {
            game?.update()
            game?.draw()
            fpsCounter++
        }
    }
}