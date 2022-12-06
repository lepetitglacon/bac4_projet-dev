package engine.entities.gui

import engine.GameEngine
import engine.entities.gui.component.BarGui
import engine.entities.gui.component.StringGui
import engine.maths.Vector2
import java.awt.Color
import java.awt.Graphics2D

class Gui {
    val healthBar = BarGui()
    val xpBar = BarGui()
    val levelCounter = StringGui()

    fun init() {
        levelCounter.string = GameEngine.game.hero.level.toString()

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
        levelCounter.string = GameEngine.game.hero.level.toString()

        healthBar.position = Vector2(GameEngine.window.center.x, (GameEngine.window.HEIGHT - healthBar.height - 50).toDouble())
        healthBar.filled = GameEngine.game.hero.hp
        healthBar.maxFilled = GameEngine.game.hero.maxHp

        xpBar.position = Vector2(GameEngine.window.center.x, (GameEngine.window.HEIGHT - healthBar.height - xpBar.height - 55).toDouble())
        xpBar.filled = GameEngine.game.hero.xp
        xpBar.maxFilled = GameEngine.game.hero.xpToNextLevel
    }

    fun draw(g: Graphics2D) {
        update()

        xpBar.draw(g)
        healthBar.draw(g)
        levelCounter.draw(g)
    }
}