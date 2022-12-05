package engine.entity.gui

import engine.GameEngine
import engine.entity.factory.SpriteFactory
import engine.entity.gui.component.ButtonGui
import engine.entity.gui.component.StringGui
import engine.maths.Vector2
import java.awt.Color
import java.awt.Graphics2D
import java.awt.geom.AffineTransform

class MainMenuGui {
    val playButton: ButtonGui = ButtonGui()

    fun init() {

    }

    fun update() {

    }

    fun drawMainMenu(g: Graphics2D) {
        g.drawImage(SpriteFactory.getMainTitle(), null,
                GameEngine.window.WIDTH/2 - SpriteFactory.getMainTitle().width/2,
                GameEngine.window.WIDTH/2 - SpriteFactory.getMainTitle().height/2)

        g.drawString("Enter to play", GameEngine.window.WIDTH/2, GameEngine.window.HEIGHT/2)
    }

    fun drawGameOver(g: Graphics2D) {
        g.color = Color.DARK_GRAY
        g.fillRect(0, 0, GameEngine.window.WIDTH, GameEngine.window.HEIGHT)
        val string = StringGui()
        val s = StringGui()
        val at = AffineTransform()
        at.scale(-2.0, -2.0)

        g.drawImage(SpriteFactory.getGameOver(),
                GameEngine.window.WIDTH/2 - 150,
                GameEngine.window.WIDTH/2 - 150,
                300, 300, 0, 0,
                SpriteFactory.getGameOver().width,
                SpriteFactory.getGameOver().height, null);

        s.position = Vector2(0.0, -15.0)

        string.draw(g)
        s.draw(g)
    }
}