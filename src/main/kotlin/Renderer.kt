import java.awt.*
import java.awt.event.ActionEvent
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.SwingUtilities
import javax.swing.Timer

object Renderer : JPanel() {
    const val FRAMES_PER_SEC = 60
    const val FRAME_PER_MSEC = 1000 / FRAMES_PER_SEC
    const val WINDOW_WIDTH = 800
    const val WINDOW_HEIGHT = 600

    var userInputVector: Vector2 = Vector2()
    var userInputUp = false
    var userInputDown = false
    var userInputLeft = false
    var userInputRight = false

    val entities = mutableListOf<Enemy>()
    val hero = Hero()
    var wave = 1
    val gui = Gui

    var deaths = 0

    init {
        preferredSize = Dimension(WINDOW_WIDTH, WINDOW_HEIGHT)
        background = Color.white
    }

    fun initGame() {
        hero.weapons.add(Sword())

        SwingUtilities.invokeLater {

            val f = JFrame()
            with (f) {
                defaultCloseOperation = JFrame.EXIT_ON_CLOSE
                title = "Bac+4 survival game - Esteban GAGNEUR"
                isResizable = true
                add(this@Renderer, BorderLayout.CENTER)
                pack()
                setLocationRelativeTo(null)
                isVisible = true
            }

            // Set up key event handler
            f.addKeyListener(object : KeyAdapter() {

                override fun keyPressed(e: KeyEvent) {
                    when (e.keyCode) {
                        KeyEvent.VK_Z -> userInputUp = true
                        KeyEvent.VK_S -> userInputDown = true
                        KeyEvent.VK_Q -> userInputLeft = true
                        KeyEvent.VK_D -> userInputRight = true
                    }
                }

                override fun keyReleased(e: KeyEvent) {
                    when (e.keyCode) {
                        KeyEvent.VK_Z -> userInputUp = false
                        KeyEvent.VK_S -> userInputDown = false
                        KeyEvent.VK_Q -> userInputLeft = false
                        KeyEvent.VK_D -> userInputRight = false
                    }
                }
            })

            val stepTimer = Timer(FRAME_PER_MSEC) { e: ActionEvent? -> stepGame() }
            stepTimer.start()

        }
    }

    private fun createEnnemies() {
        EntityFactory.createEnemiesForWave(wave)
        wave++
    }

    private fun stepGame() {

        moveHero()

        handleHeroDeath()

        hero.attack()

        handleWaveChanging()


        repaint()
    }

    override fun paint(gg: Graphics) {
        super.paintComponent(gg)
        val g = gg as Graphics2D
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)

        // Draw the ennemies
        entities.forEach { it.draw(g) }

        // Draw the hero
        hero.draw(g)

        hero.weapons.forEach { it.draw(g) }

        // Check if the hero is in collision with an enemy
        entities.removeAll {
            it.mustDie(hero)
        }

        // Draw GUI on top
        gui.draw(g)
    }

    private fun handleHeroDeath() {
        if (hero.hp <= 0) {
            deaths++
            hero.hp = 100
        }
    }

    private fun handleWaveChanging() {
        if (entities.size <= 0) {
            createEnnemies()
        }
    }

    private fun moveHero() {
        if (userInputUp && userInputLeft) {
            userInputVector.x = -1.0
            userInputVector.y = -1.0
        }
        else if(userInputUp && userInputRight) {
            userInputVector.x = 1.0
            userInputVector.y = -1.0
        }
        else if(userInputDown && userInputLeft) {
            userInputVector.x = -1.0
            userInputVector.y = 1.0
        }
        else if(userInputDown && userInputRight) {
            userInputVector.x = 1.0
            userInputVector.y = 1.0
        }
        else if(userInputUp) {
            userInputVector.x = 0.0
            userInputVector.y = -1.0
        }
        else if(userInputDown) {
            userInputVector.x = 0.0
            userInputVector.y = 1.0
        }
        else if(userInputLeft) {
            userInputVector.x = -1.0
            userInputVector.y = 0.0
        }
        else if(userInputRight) {
            userInputVector.x = 1.0
            userInputVector.y = 0.0
        }
        else {
            userInputVector.x = 0.0
            userInputVector.y = 0.0
        }

        hero.move(userInputVector)
    }

}