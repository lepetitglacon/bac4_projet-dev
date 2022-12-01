package engine.input

import engine.maths.Vector2
import engine.GameEngine
import engine.logger.Logger
import java.awt.event.*

class InputManager {
    var userInputVector: Vector2 = Vector2()
    var userInputUp = false
    var userInputDown = false
    var userInputLeft = false
    var userInputRight = false
    var userInputSpace = false

    init {
        // key events
        GameEngine.window.addKeyListener(object : KeyAdapter() {
            override fun keyPressed(e: KeyEvent) {
                when (e.keyCode) {
                    KeyEvent.VK_Z -> userInputUp = true
                    KeyEvent.VK_S -> userInputDown = true
                    KeyEvent.VK_Q -> userInputLeft = true
                    KeyEvent.VK_D -> userInputRight = true
                    KeyEvent.VK_SPACE -> userInputSpace = true
                    //KeyEvent.VK_ESCAPE -> TODO("escape")
                }
            }
            override fun keyReleased(e: KeyEvent) {
                when (e.keyCode) {
                    KeyEvent.VK_Z -> userInputUp = false
                    KeyEvent.VK_S -> userInputDown = false
                    KeyEvent.VK_Q -> userInputLeft = false
                    KeyEvent.VK_D -> userInputRight = false
                    KeyEvent.VK_SPACE -> userInputSpace = false
                }
            }
        })

        // mouse events
        GameEngine.window.addMouseListener(object : MouseAdapter() {
            override fun mouseClicked(e: MouseEvent) {
                Logger.info("mouse click at x:${e.x} y:${e.y}")
            }
        })
    }

    fun getKeyboardMovement() {
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
    }
}