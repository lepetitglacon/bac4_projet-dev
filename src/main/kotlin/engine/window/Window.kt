package engine.window

import engine.maths.Vector2
import engine.GameEngine
import java.awt.BorderLayout
import java.awt.Dimension
import javax.swing.JFrame

class Window : JFrame() {
    val WIDTH: Int = 480
    val HEIGHT: Int = 720
    val center: Vector2 = Vector2((WIDTH/2).toDouble(), (HEIGHT/2).toDouble())

    fun init() {
        title = "Bac+4 survival game - Esteban GAGNEUR"
        defaultCloseOperation = EXIT_ON_CLOSE
        isResizable = true
        isVisible = true
        preferredSize = Dimension(WIDTH, HEIGHT)
        add(GameEngine, BorderLayout.CENTER)
        pack()
        setLocationRelativeTo(null)
    }
}