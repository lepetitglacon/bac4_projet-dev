package engine.entity.gui

import engine.GameEngine
import engine.entity.gui.component.BarComponent
import engine.resource.SpriteFactory
import java.awt.Color
import java.awt.Graphics2D

class Gui {
    val PADDING = 5

    val hpBar = BarComponent()
    val xpBar = BarComponent()

    fun draw(g: Graphics2D) {
        // HP
        hpBar.width = GameEngine.window.WIDTH / 2
        hpBar.height = 25
        hpBar.x = GameEngine.window.WIDTH / 4
        hpBar.y = GameEngine.window.HEIGHT - 150
        hpBar.maxFilled = GameEngine.game?.hero?.maxHp!!.toInt()
        hpBar.filled = GameEngine.game?.hero?.hp!!.toInt()
        hpBar.draw(g)

        // XP
        xpBar.width = GameEngine.window.WIDTH / 2
        xpBar.height = 8
        xpBar.x = GameEngine.window.WIDTH / 4
        xpBar.y = hpBar.y - xpBar.height - PADDING
        xpBar.maxFilled = GameEngine.game?.hero?.maxXp!!.toInt()
        xpBar.filled = GameEngine.game?.hero?.xp!!.toInt()
        xpBar.color = Color(0,191,255)
        xpBar.completingColor = Color(30,144,255)
        xpBar.draw(g)
        g.drawString(GameEngine.game?.hero?.level.toString(), xpBar.x + xpBar.width/2, xpBar.y - 5)
        g.drawString(GameEngine.game?.hero?.maxXp!!.toInt().toString(), xpBar.x + xpBar.width + 5, xpBar.y)

        // behelitsss
        val behelitX = hpBar.x
        val behelitY = hpBar.y + hpBar.height + PADDING
        val behelitImg = SpriteFactory.get("object_behelit").image
        g.drawImage(behelitImg, null, behelitX, behelitY)
        g.drawString(GameEngine.game?.behelits.toString(),behelitX + behelitImg?.width!!, behelitY + behelitImg.height/2)
    }


}