package engine.entity.mob

import engine.GameEngine
import engine.entity.Entity
import java.awt.Color
import java.awt.Graphics2D

class Enemy : Entity() {

    init {
        width = 25
        height = 25
    }

    override fun draw(g: Graphics2D) {
        if (GameEngine.debug) {
            g.color = Color.RED
            g.fillOval(getDrawingPosition(GameEngine.game.hero).x.toInt(), getDrawingPosition(GameEngine.game.hero).y.toInt(), width, height)
        }
    }

    override fun move() {

    }

}