package engine.entity.gui

import engine.GameEngine
import engine.entity.factory.SpriteFactory
import engine.entity.gui.component.ButtonGui
import engine.entity.gui.component.StringGui
import engine.maths.Vector2
import java.awt.Color
import java.awt.Font
import java.awt.FontMetrics
import java.awt.Graphics2D
import java.awt.font.FontRenderContext
import java.awt.geom.AffineTransform
import java.awt.geom.Rectangle2D


class MainMenuGui {
    val playButton: ButtonGui = ButtonGui()

    fun init() {

    }

    fun update() {

    }

    fun drawMainMenu(g: Graphics2D) {
        g.drawImage(SpriteFactory.getMainTitle(), null,
                GameEngine.window.WIDTH/2 - SpriteFactory.getMainTitle().width/2,
                GameEngine.window.HEIGHT/2 - SpriteFactory.getMainTitle().height)

        val serif = Font(Font.DIALOG_INPUT, Font.BOLD, 24)
        val string = "Enter to play"
        val fontRenderCtx: FontRenderContext = g.fontRenderContext
        val bounds1: Rectangle2D = serif.getStringBounds(string, fontRenderCtx)
        val y = GameEngine.window.HEIGHT - SpriteFactory.getMainTitle().height
        val x = (GameEngine.window.WIDTH - bounds1.width.toInt()) / 2
        g.color = Color.RED
        g.font = serif
        g.drawString(string, x, y)
    }

    fun drawGameOver(g: Graphics2D) {
        g.color = Color.DARK_GRAY
        g.fillRect(0, 0, GameEngine.window.WIDTH, GameEngine.window.HEIGHT)

//        g.drawImage(SpriteFactory.getGameOver(),
//                GameEngine.window.WIDTH/2 - 150,
//                GameEngine.window.HEIGHT/2 - 150,
//                500, 300, 0, 0,
//                SpriteFactory.getGameOver().width,
//                SpriteFactory.getGameOver().height, null);

        g.drawImage(SpriteFactory.getGameOver(),
            null,
            GameEngine.window.WIDTH/2 - SpriteFactory.getGameOver().width/2,
            GameEngine.window.HEIGHT/2 - SpriteFactory.getGameOver().height/2 - 25
            )

        val serif = Font(Font.DIALOG_INPUT, Font.BOLD, 24)
        val string = "Enter to Rebirth"
        val fontRenderCtx: FontRenderContext = g.fontRenderContext
        val bounds1: Rectangle2D = serif.getStringBounds(string, fontRenderCtx)
        val y = GameEngine.window.HEIGHT - SpriteFactory.getMainTitle().height
        val x = (GameEngine.window.WIDTH - bounds1.width.toInt()) / 2
        g.color = Color.RED
        g.font = serif
        g.drawString(string, x, y)
    }
}