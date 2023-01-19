package engine.window

import engine.GameEngine
import engine.event.input.InputEvent
import engine.event.input.InputListenerType
import engine.event.movement.hero.HeroMovementEvent
import engine.event.movement.hero.HeroMovementListenerType
import engine.math.Vec2
import java.awt.BorderLayout
import java.awt.Dimension
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
import javax.swing.JFrame

class Window : JFrame() {
    var WIDTH: Int = 480
    var HEIGHT: Int = 720

    var up = false
    var left = false
    var down = false
    var right = false
    var keyboardMovementVector = Vec2()

    val sync = Object()

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
                    KeyEvent.VK_X -> event.type = InputListenerType.X

                    KeyEvent.VK_Z, KeyEvent.VK_UP -> {
                        up = true
                        event.type = InputListenerType.UP
                    }
                    KeyEvent.VK_Q, KeyEvent.VK_LEFT -> {
                        left = true
                        event.type = InputListenerType.LEFT
                    }
                    KeyEvent.VK_S, KeyEvent.VK_DOWN -> {
                        down = true
                        event.type = InputListenerType.DOWN
                    }
                    KeyEvent.VK_D, KeyEvent.VK_RIGHT -> {
                        right = true
                        event.type = InputListenerType.RIGHT
                    }
                }
                synchronized(GameEngine.inputListenerManager) {
                    GameEngine.inputListenerManager.on(event)
                }
            }

            override fun keyReleased(e: KeyEvent) {
                when (e.keyCode)
                {
                    KeyEvent.VK_Z, KeyEvent.VK_UP -> {
                        up = false
                    }
                    KeyEvent.VK_Q, KeyEvent.VK_LEFT -> {
                        left = false
                    }
                    KeyEvent.VK_S, KeyEvent.VK_DOWN -> {
                        down = false
                    }
                    KeyEvent.VK_D, KeyEvent.VK_RIGHT -> {
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
        GameEngine.game?.map?.onPlayerMovement()
    }

    fun updateTitle() {
        title = "Bac+4 survival game - Esteban GAGNEUR ${GameEngine.ticksCounter}"
    }
}