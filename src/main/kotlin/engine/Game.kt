package engine

import Hero
import java.awt.event.ActionEvent
import javax.swing.Timer

class Game {
    val hero = Hero()
    val timer = Timer(Renderer.FRAME_PER_MSEC) { e: ActionEvent? -> run() }
    var wave = 1

    private fun run() {
        moveHero()
//        moveEnnemy()
//        Renderer.game.hero.attack()
//        Renderer.checkCollisions()
//        handleHeroDeath()
//        handleWaveChanging()
//        Renderer.ticks++
        Renderer.repaint()
    }

    fun handleHeroDeath() {
        if (Renderer.game.hero.dead) {

            // restart the game
            if (Renderer.inputHandler.userInputSpace) {
                Renderer.game.hero.dead = false
                Renderer.timer.stop()
                Renderer.game.timer.start()
                Renderer.game.hero.hp = 100
            }
        } else {

            // on hero death
            if (Renderer.game.hero.hp <= 0) {
                Renderer.game.hero.deaths++
                Renderer.game.hero.dead = true
                Renderer.game.timer.stop()
                Renderer.timer.start()
            }
        }
    }

    fun handleWaveChanging() {
        if (Renderer.entities.size <= wave * 6 / 2) {
            Renderer.createEnnemies()
        }
    }

    fun moveEnnemy() {
        Renderer.entities.forEach {  }
    }

    fun moveHero() {
        if (Renderer.inputHandler.userInputUp && Renderer.inputHandler.userInputLeft) {
            Renderer.inputHandler.userInputVector.x = -1.0
            Renderer.inputHandler.userInputVector.y = -1.0
        } else if (Renderer.inputHandler.userInputUp && Renderer.inputHandler.userInputRight) {
            Renderer.inputHandler.userInputVector.x = 1.0
            Renderer.inputHandler.userInputVector.y = -1.0
        } else if (Renderer.inputHandler.userInputDown && Renderer.inputHandler.userInputLeft) {
            Renderer.inputHandler.userInputVector.x = -1.0
            Renderer.inputHandler.userInputVector.y = 1.0
        } else if (Renderer.inputHandler.userInputDown && Renderer.inputHandler.userInputRight) {
            Renderer.inputHandler.userInputVector.x = 1.0
            Renderer.inputHandler.userInputVector.y = 1.0
        } else if (Renderer.inputHandler.userInputUp) {
            Renderer.inputHandler.userInputVector.x = 0.0
            Renderer.inputHandler.userInputVector.y = -1.0
        } else if (Renderer.inputHandler.userInputDown) {
            Renderer.inputHandler.userInputVector.x = 0.0
            Renderer.inputHandler.userInputVector.y = 1.0
        } else if (Renderer.inputHandler.userInputLeft) {
            Renderer.inputHandler.userInputVector.x = -1.0
            Renderer.inputHandler.userInputVector.y = 0.0
        } else if (Renderer.inputHandler.userInputRight) {
            Renderer.inputHandler.userInputVector.x = 1.0
            Renderer.inputHandler.userInputVector.y = 0.0
        } else {
            Renderer.inputHandler.userInputVector.x = 0.0
            Renderer.inputHandler.userInputVector.y = 0.0
        }

        Renderer.game.hero.move(Renderer.inputHandler.userInputVector)
    }
}