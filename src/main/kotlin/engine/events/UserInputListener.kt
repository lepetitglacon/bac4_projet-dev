package engine.events

import engine.Renderer
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent

class UserInputListener: KeyAdapter() {
    override fun keyPressed(e: KeyEvent) {
        when (e.keyCode) {
            KeyEvent.VK_Z -> Renderer.inputHandler.userInputUp = true
            KeyEvent.VK_S -> Renderer.inputHandler.userInputDown = true
            KeyEvent.VK_Q -> Renderer.inputHandler.userInputLeft = true
            KeyEvent.VK_D -> Renderer.inputHandler.userInputRight = true
            KeyEvent.VK_SPACE -> {
                Renderer.inputHandler.userInputSpace = true
            }
            KeyEvent.VK_ESCAPE -> {
                if (Renderer.game.timer.isRunning) {
                    Renderer.game.timer.stop()
                    Renderer.timer.start()
                } else {
                    Renderer.game.timer.start()
                    Renderer.timer.stop()
                }

            }
        }
    }
    override fun keyReleased(e: KeyEvent) {
        when (e.keyCode) {
            KeyEvent.VK_Z -> Renderer.inputHandler.userInputUp = false
            KeyEvent.VK_S -> Renderer.inputHandler.userInputDown = false
            KeyEvent.VK_Q -> Renderer.inputHandler.userInputLeft = false
            KeyEvent.VK_D -> Renderer.inputHandler.userInputRight = false
            KeyEvent.VK_SPACE -> Renderer.inputHandler.userInputSpace = false
        }
    }
}