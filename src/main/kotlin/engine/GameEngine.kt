package engine

import engine.game.Game
import engine.window.Window
import javax.swing.JPanel

object GameEngine : JPanel() {
    // debug
    var debug = false

    // game loop
    val fps: Long = (1_000_000 / 60).toLong()
    var running = false

    // objects
    var game: Game? = null
    var window: Window? = null

    init {
        game = Game()
        window = Window()
        running = true
        run()
    }

    fun run()
    {
        var fpsCounter = 0
        while (running)
        {
            if (System.nanoTime() % fps == 0.toLong()) {
                game?.update()
                game?.draw()
                fpsCounter++
                println(fpsCounter)
            }
        }
    }
}