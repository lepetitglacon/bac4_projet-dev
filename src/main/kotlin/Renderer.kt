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
    const val FRAME_IN_MSEC = 1000 / FRAMES_PER_SEC
    const val WINDOW_WIDTH = 800
    const val WINDOW_HEIGHT = 600

    var upPressed = false
    var downPressed = false
    var leftPressed = false
    var rightPressed = false
    val entities = mutableListOf<Enemy>()
    val hero = Hero(0, 0, 30)

    init {
        preferredSize = Dimension(WINDOW_WIDTH, WINDOW_HEIGHT)
        background = Color.white
    }

    fun initGame() {
        createEnnemies()
        this.entities.addAll(entities)

        SwingUtilities.invokeLater {
            val f = JFrame()
            with (f) {
                defaultCloseOperation = JFrame.EXIT_ON_CLOSE
                title = "Survival"
                isResizable = false
                add(this@Renderer, BorderLayout.CENTER)
                pack()
                setLocationRelativeTo(null)
                isVisible = true
            }

            val stepTimer = Timer(FRAME_IN_MSEC) { e: ActionEvent? -> stepGame() }
            stepTimer.start()

            // Set up key event handler
            f.addKeyListener(object : KeyAdapter() {
                override fun keyPressed(e: KeyEvent) {
                    when (e.keyCode) {
                        KeyEvent.VK_UP -> upPressed = true
                        KeyEvent.VK_DOWN -> downPressed = true
                        KeyEvent.VK_LEFT -> leftPressed = true
                        KeyEvent.VK_RIGHT -> rightPressed = true
                    }
                }

                override fun keyReleased(e: KeyEvent) {
                    when (e.keyCode) {
                        KeyEvent.VK_UP -> upPressed = false
                        KeyEvent.VK_DOWN -> downPressed = false
                        KeyEvent.VK_LEFT -> leftPressed = false
                        KeyEvent.VK_RIGHT -> rightPressed = false
                    }
                }
            })
        }
    }

    private fun createEnnemies() {
        val entities = listOf(
            Enemy(100, 100, 20, Color.blue),
            Enemy(200, 200, 20, Color.green),
            Enemy(300, 300, 20, Color.yellow),
            Enemy(400, 400, 20, Color.orange),
            Enemy(500, 500, 20, Color.pink),
            Enemy(600, 600, 20, Color.cyan),
            Enemy(700, 700, 20, Color.magenta),
            Enemy(800, 800, 20, Color.gray),
            Enemy(900, 900, 20, Color.darkGray),
            Enemy(1000, 1000, 20, Color.lightGray)
        )
        this.entities.addAll(entities)
    }

    private fun stepGame() {
        repaint()
    }

    override fun paint(gg: Graphics) {
        super.paintComponent(gg)
        val g = gg as Graphics2D
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)

        // Draw the ennemies
        entities.forEach { it.draw(hero.posX, hero.posY, g) }
        // Draw the hero
        hero.draw(g)

        // Move the Hero
        if(upPressed && leftPressed) {
            hero.moveUpLeft()
        }
        else if(upPressed && rightPressed) {
            hero.moveUpRight()
        }
        else if(downPressed && leftPressed) {
            hero.moveDownLeft()
        }
        else if(downPressed && rightPressed) {
            hero.moveDownRight()
        }
        else if(upPressed) {
            hero.moveUp()
        }
        else if(downPressed) {
            hero.moveDown()
        }
        else if(leftPressed) {
            hero.moveLeft()
        }
        else if(rightPressed) {
            hero.moveRight()
        }
        else {
            // Invalid moves
        }

        // Check if the hero is in collision with an enemy
        entities.removeAll { hero.isColliding(it) }
    }

}