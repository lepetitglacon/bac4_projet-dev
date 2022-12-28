package engine

import engine.entity.registrer.EnemyRegistrer
import engine.entity.registrer.EnemyType
import engine.event.input.InputListenerManager
import engine.event.movement.hero.HeroMovementListenerManager
import engine.game.Game
import engine.window.Window
import java.awt.Graphics
import java.awt.Graphics2D
import javax.swing.JPanel
import javax.swing.SwingUtilities
import javax.swing.Timer

object GameEngine : JPanel() {
    // debug
    var debug = true

    // game loop
    val fps: Long = (1_000_000 / 60).toLong()
    var ticksCounter = 0
    var running = false
    val timer: Timer = Timer(1) { run() }

    // objects
    var game: Game? = null
    var window: Window = Window()
    val enemyRegistrer = EnemyRegistrer()

    // events manager
    val inputListenerManager = InputListenerManager()
    val heroMovementListenerManager = HeroMovementListenerManager()

    init
    {
        // enemy registration
        enemyRegistrer.add(EnemyType("warrior", 0, 20, 50))
        enemyRegistrer.add(EnemyType("mercenary", 2, 50, 50))

        SwingUtilities.invokeLater {
            game = Game()

            running = true
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

//        g.drawLine(window?.WIDTH!!/2, 0, window?.WIDTH!!/2, window?.HEIGHT!!)

        game?.draw(g)
    }
}