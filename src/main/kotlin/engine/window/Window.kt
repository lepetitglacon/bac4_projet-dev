package engine.window

import engine.GameEngine
import engine.event.input.InputEvent
import engine.event.input.InputListenerType
import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import java.util.EventListener
import javax.swing.JFrame

class Window : JFrame() {
    var WIDTH: Int = 480
    var HEIGHT: Int = 720

    init
    {
        title = "Bac+4 survival game - Esteban GAGNEUR"
        defaultCloseOperation = EXIT_ON_CLOSE
        isResizable = GameEngine.debug
        isVisible = true
        preferredSize = Dimension(WIDTH, HEIGHT)
        add(GameEngine, BorderLayout.CENTER)
        pack()
        setLocationRelativeTo(null)

        addKeyListener(object : KeyAdapter() {
            override fun keyTyped(e: KeyEvent?) {
            }

            override fun keyPressed(e: KeyEvent) {
                println(e.keyCode)
                val event = InputEvent()
                when (e.keyCode)
                {
                    KeyEvent.VK_ENTER -> event.type = InputListenerType.ENTER
                }


                GameEngine.inputListenerManager.on(event)
            }

            override fun keyReleased(e: KeyEvent?) {
            }
        })
    }

    fun updateTitle() {
        title = "Bac+4 survival game - Esteban GAGNEUR ${GameEngine.ticksCounter}"
    }
}