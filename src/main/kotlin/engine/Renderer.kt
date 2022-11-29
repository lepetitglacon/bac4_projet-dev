package engine

import Entity
import EntityFactory
import Gui
import engine.events.JFrameResizeListener
import engine.events.UserInputListener
import java.awt.*
import java.awt.event.*
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.SwingUtilities
import javax.swing.Timer

object Renderer : JPanel() {
    // Frame and engine variables
    val FRAMES_PER_SEC = 60
    val FRAME_PER_MSEC = 1000 / FRAMES_PER_SEC
    var WINDOW_WIDTH = 800
    var WINDOW_HEIGHT = 600
    var timer = Timer(FRAME_PER_MSEC) { e: ActionEvent? -> run() }
    var ticks = 0
    var game = Game()
    val inputHandler = InputHandler()
    val entities = mutableListOf<Entity>()
    val logger = Logger()
    val gui = Gui

    fun initEngine() {
        SwingUtilities.invokeLater {
            val f = JFrame()
            with(f) {
                defaultCloseOperation = JFrame.EXIT_ON_CLOSE
                title = "Bac+4 survival game - Esteban GAGNEUR"
                isResizable = true
                add(this@Renderer, BorderLayout.CENTER)
                pack()
                setLocationRelativeTo(null)
                isVisible = true
            }

            // Set up key event handler
            f.addKeyListener(UserInputListener())
            f.addComponentListener(JFrameResizeListener())

            game.timer.start()
        }
    }

    fun createEnnemies() {
        EntityFactory.createEnemiesForWave(game.wave)
        game.wave++
    }

    private fun run() {
        ticks++
        repaint()
    }

    override fun paint(gg: Graphics) {
        super.paintComponent(gg)
        val g = gg as Graphics2D
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)

        // Draw the ennemies
        entities.forEach { it.draw(g) }

        // Draw weapons
        game.hero.weapons.forEach { it.draw(g) }

        game.hero.gun.bullets.forEach { it.draw(g) }

        // Draw the hero
        game.hero.draw(g)

        // Draw GUI on top
        Gui.draw(g)
    }

    fun checkCollisions() {
        // Check if the hero is in collision with an enemy
        entities.removeAll {
            it.mustDie()
        }

        entities.forEach { it.attack() }

        game.hero.gun.bullets.removeIf {
            it.needRemoval
        }

        game.hero.weapons.forEach {
            it.checkCollision()
        }
    }


}