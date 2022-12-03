package engine.entity.gui

import engine.GameEngine
import engine.maths.Vector2
import java.awt.Color
import java.awt.Graphics2D

class Gui {
    val healthBar = BarGui()
    val xpBar = BarGui()

    fun init() {
        healthBar.width = GameEngine.window.WIDTH/2
        healthBar.height = 32
        healthBar.position = Vector2(GameEngine.window.center.x, (GameEngine.window.HEIGHT - healthBar.height - 50).toDouble())
        healthBar.filled = GameEngine.game.hero.hp
        healthBar.maxFilled = GameEngine.game.hero.maxHp

        xpBar.width = GameEngine.window.WIDTH/2
        xpBar.height = 8
        xpBar.position = Vector2(GameEngine.window.center.x, (GameEngine.window.HEIGHT - healthBar.height - xpBar.height - 55).toDouble())
        xpBar.filled = GameEngine.game.hero.xp
        xpBar.maxFilled = GameEngine.game.hero.xpToNextLevel
        xpBar.color = Color(33, 160, 232)
        xpBar.completingColor = Color(3, 52, 80)
    }

    fun update() {
        healthBar.filled = GameEngine.game.hero.hp
        healthBar.maxFilled = GameEngine.game.hero.maxHp

        xpBar.filled = GameEngine.game.hero.xp
        xpBar.maxFilled = GameEngine.game.hero.xpToNextLevel
    }

    fun draw(g: Graphics2D) {
        update()

        xpBar.draw(g)
        healthBar.draw(g)
    }
}