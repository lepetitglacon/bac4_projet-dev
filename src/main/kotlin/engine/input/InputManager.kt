package engine.input

import engine.maths.Vector2
import engine.GameEngine
import engine.entity.factory.EntityFactory
import engine.logger.Logger
import engine.maths.Vector2Int
import java.awt.MouseInfo
import java.awt.event.*
import javax.swing.SwingUtilities

class InputManager {
    var userInputVector: Vector2 = Vector2()
    var userInputUp = false
    var userInputDown = false
    var userInputLeft = false
    var userInputRight = false
    var userInputSpace = false
    var userInputEnter = false
    var userInputEscape = false

    var mousePointer: Vector2Int = Vector2Int()

    init {
        // key events
        GameEngine.window.addKeyListener(object : KeyAdapter() {
            override fun keyPressed(e: KeyEvent) {
                when (e.keyCode) {
                    KeyEvent.VK_Z -> userInputUp = true
                    KeyEvent.VK_S -> userInputDown = true
                    KeyEvent.VK_Q -> userInputLeft = true
                    KeyEvent.VK_D -> userInputRight = true
                    KeyEvent.VK_SPACE -> {
                        GameEngine.game.collidableEntities.clear()
                        for (i in 0..2) {
                            GameEngine.game.collidableEntities.add(EntityFactory.createRandomEnemy())
                        }
                    }
                    //KeyEvent.VK_ESCAPE -> TODO("escape")
                    KeyEvent.VK_ENTER -> userInputEnter = true
                    KeyEvent.VK_ESCAPE -> userInputEscape = true
                }
            }
            override fun keyReleased(e: KeyEvent) {
                when (e.keyCode) {
                    KeyEvent.VK_Z -> userInputUp = false
                    KeyEvent.VK_S -> userInputDown = false
                    KeyEvent.VK_Q -> userInputLeft = false
                    KeyEvent.VK_D -> userInputRight = false
                    KeyEvent.VK_SPACE -> userInputSpace = false
                    KeyEvent.VK_ENTER -> userInputEnter = false
                    KeyEvent.VK_ESCAPE -> userInputEscape = false
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

    fun getMouseLocation() {
        val p = MouseInfo.getPointerInfo().location
        SwingUtilities.convertPointFromScreen(p, GameEngine)
        mousePointer = Vector2Int((p.x + GameEngine.game.hero.drawingPosition.x).toInt(), (p.y + GameEngine.game.hero.drawingPosition.y).toInt())
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