import engine.logger.Formatter
import engine.maths.Vector2
import java.awt.*
import java.awt.event.ActionEvent
import java.util.logging.ConsoleHandler
import java.util.logging.Logger
import javax.swing.JPanel
import javax.swing.SwingUtilities
import javax.swing.Timer


object Renderer : JPanel() {
    // Frame and engine variables
    const val FRAMES_PER_SEC = 60
    const val FRAME_PER_MSEC = 1000 / FRAMES_PER_SEC
    const val WINDOW_WIDTH = 800
    const val WINDOW_HEIGHT = 600
    var timeElapsed = FRAME_PER_MSEC
    var gameTimer = Timer(FRAME_PER_MSEC) { e: ActionEvent? -> stepGame() }
    var engineTimer = Timer(FRAME_PER_MSEC) { e: ActionEvent? -> stepEngine() }
    var ticks = 0
    val logger = Logger.getLogger(Renderer::class.java.name)

    // player input
    var userInputVector: Vector2 = Vector2()
    var userInputUp = false
    var userInputDown = false
    var userInputLeft = false
    var userInputRight = false
    var userInputSpace = false

    //
    val hero = Hero()
    val entities = mutableListOf<Enemy>()
    val gui = Gui

    // game variables
    var wave = 1
    var deaths = 0
    var playerDead = false

    init {
        preferredSize = Dimension(WINDOW_WIDTH, WINDOW_HEIGHT)
        background = Color.white
    }

    fun initEngine() {
        hero.weapons.add(Sword())

        logger.useParentHandlers = false
        val handler = ConsoleHandler()
        val formatter: Formatter = Formatter()
        handler.formatter = formatter
        logger.addHandler(handler)


        // frame
        SwingUtilities.invokeLater {
            this.gameTimer.start()
            logger.info("Engine running")
        }
    }

    private fun stepGame() {
        // engine
        ticks++

        // move entities
        moveHero()
        moveEnnemy()

        // actions
        handleHeroDeath()
        handleWaveChanging()

        // collisions
        hero.attack()
        checkCollisions()


        repaint()
    }

    override fun paint(gg: Graphics) {
        super.paintComponent(gg)
        val g = gg as Graphics2D
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)

        // Draw the ennemies
        entities.forEach { it.draw(g) }

        // Draw weapons
        hero.weapons.forEach { it.draw(g) }



        // Draw the hero
        hero.draw(g)

        // Draw GUI on top
        gui.draw(g)
    }

    private fun checkCollisions() {
        // Check if the hero is in collision with an enemy
        entities.removeAll {
            it.mustDie(hero)
        }

        hero.weapons.forEach {
            it.checkCollision()
        }
    }

    private fun handleHeroDeath() {
        if (playerDead) {

            // restart the game
            if (userInputSpace) {
                playerDead = false
                engineTimer.stop()
                gameTimer.start()
                hero.hp = 100
            }
        } else {

            // on hero death
            if (hero.hp <= 0) {
                deaths++
                playerDead = true
                gameTimer.stop()
                engineTimer.start()
            }
        }
    }

    private fun moveHero() {
        if (userInputUp && userInputLeft) {
            userInputVector.x = -1.0
            userInputVector.y = -1.0
        } else if (userInputUp && userInputRight) {
            userInputVector.x = 1.0
            userInputVector.y = -1.0
        } else if (userInputDown && userInputLeft) {
            userInputVector.x = -1.0
            userInputVector.y = 1.0
        } else if (userInputDown && userInputRight) {
            userInputVector.x = 1.0
            userInputVector.y = 1.0
        } else if (userInputUp) {
            userInputVector.x = 0.0
            userInputVector.y = -1.0
        } else if (userInputDown) {
            userInputVector.x = 0.0
            userInputVector.y = 1.0
        } else if (userInputLeft) {
            userInputVector.x = -1.0
            userInputVector.y = 0.0
        } else if (userInputRight) {
            userInputVector.x = 1.0
            userInputVector.y = 0.0
        } else {
            userInputVector.x = 0.0
            userInputVector.y = 0.0
        }

        hero.move(userInputVector)
    }

    private fun handleWaveChanging() {
        if (entities.size <= wave * 6 / 2) {
            createEnnemies()
        }
    }

    private fun moveEnnemy() {
        entities.forEach { it.move() }
    }

    private fun createEnnemies() {
        EntityFactory.createEnemiesForWave(wave)
        wave++
    }

    private fun stepEngine() {
        handleHeroDeath()
        repaint()
    }
}