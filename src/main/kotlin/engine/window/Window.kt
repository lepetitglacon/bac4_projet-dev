package engine.window

import engine.GameEngine
import engine.event.input.InputEvent
import engine.event.input.InputListenerType
import engine.event.movement.hero.HeroMovementEvent
import engine.event.movement.hero.HeroMovementListenerType
import engine.math.Vector2
import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import java.awt.event.KeyListener
import java.util.EventListener
import java.util.Vector
import javax.swing.JFrame

class Window : JFrame() {
    var WIDTH: Int = 480
    var HEIGHT: Int = 720

    var up = false
    var left = false
    var down = false
    var right = false
    var keyboardMovementVector = Vector2()

    fun init() {
        title = "Bac+4 survival game - Esteban GAGNEUR"
        defaultCloseOperation = EXIT_ON_CLOSE
        isResizable = GameEngine.debug
        isVisible = true
        preferredSize = Dimension(WIDTH, HEIGHT)
        add(GameEngine, BorderLayout.CENTER)
        pack()
        setLocationRelativeTo(null)

        // input event
        addKeyListener(object : KeyAdapter() {
            override fun keyTyped(e: KeyEvent?) {
            }

            override fun keyPressed(e: KeyEvent) {
                val event = InputEvent()
                when (e.keyCode)
                {
                    KeyEvent.VK_ENTER -> event.type = InputListenerType.ENTER
                    KeyEvent.VK_ESCAPE -> event.type = InputListenerType.ESCAPE
                    KeyEvent.VK_SPACE -> event.type = InputListenerType.SPACE

                    KeyEvent.VK_Z -> {
                        up = true
                        event.type = InputListenerType.UP
                    }
                    KeyEvent.VK_Q -> {
                        left = true
                        event.type = InputListenerType.LEFT
                    }
                    KeyEvent.VK_S -> {
                        down = true
                        event.type = InputListenerType.DOWN
                    }
                    KeyEvent.VK_D -> {
                        right = true
                        event.type = InputListenerType.RIGHT
                    }
                }

                GameEngine.inputListenerManager.on(event)
            }

            override fun keyReleased(e: KeyEvent) {
                when (e.keyCode)
                {
                    KeyEvent.VK_Z -> {
                        up = false
                    }
                    KeyEvent.VK_Q -> {
                        left = false
                    }
                    KeyEvent.VK_S -> {
                        down = false
                    }
                    KeyEvent.VK_D -> {
                        right = false
                    }
                }
            }
        })
    }

    fun getKeyboardMovementInput()
    {
        val e = HeroMovementEvent()
        if (up && left) {
            e.type = HeroMovementListenerType.UP_LEFT
            keyboardMovementVector.x = -8.0
            keyboardMovementVector.y = -8.0
        } else if (up && right) {
            e.type = HeroMovementListenerType.UP_RIGHT
            keyboardMovementVector.x = 8.0
            keyboardMovementVector.y = -8.0
        } else if (down && left) {
            e.type = HeroMovementListenerType.DOWN_LEFT
            keyboardMovementVector.x = -8.0
            keyboardMovementVector.y = 8.0
        } else if (down && right) {
            e.type = HeroMovementListenerType.DOWN_RIGHT
            keyboardMovementVector.x = 8.0
            keyboardMovementVector.y = 8.0
        } else if (up) {
            e.type = HeroMovementListenerType.UP
            keyboardMovementVector.x = 0.0
            keyboardMovementVector.y = -8.0
        } else if (down) {
            e.type = HeroMovementListenerType.DOWN
            keyboardMovementVector.x = 0.0
            keyboardMovementVector.y = 8.0
        } else if (left) {
            e.type = HeroMovementListenerType.LEFT
            keyboardMovementVector.x = -8.0
            keyboardMovementVector.y = 0.0
        } else if (right) {
            e.type = HeroMovementListenerType.RIGHT
            keyboardMovementVector.x = 8.0
            keyboardMovementVector.y = 0.0
        } else {
            e.type = HeroMovementListenerType.IDLE
            keyboardMovementVector.x = 0.0
            keyboardMovementVector.y = 0.0
        }
        keyboardMovementVector.normalize()
        GameEngine.heroMovementListenerManager.on(e)
    }

    fun updateTitle() {
        title = "Bac+4 survival game - Esteban GAGNEUR ${GameEngine.ticksCounter}"
    }
}