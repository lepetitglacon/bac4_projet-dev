package engine.gui


import engine.GameEngine
import engine.Vector2
import java.awt.Dimension
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.event.ActionEvent
import javax.swing.JPanel
import javax.swing.Timer

class Gui : JPanel() {
    val windowWidth = 480
    val windowHeight = 720

    val timer = Timer(1 / GameEngine.FRAME_PER_SECOND / 1000) { e: ActionEvent? -> run() }
    var ticks = 0

    val hud = Hud()

    init {
        preferredSize = Dimension(windowWidth, windowHeight)
        isVisible = true
        timer.start()
    }

    fun run() {
        ticks++
        repaint()
    }

    override fun paint(gg: Graphics?) {
        super.paintComponent(gg)
        val g = gg as Graphics2D
        g.drawString("$ticks", windowWidth/2, windowHeight/2)
        g.drawString("seconds écoulées : ${ticks / 60}", windowWidth/2 + 25, windowHeight/2 + 25)

        draw(g)
    }

    fun center(): Vector2 {
        return Vector2((windowWidth/2).toDouble(), (windowHeight/2).toDouble())
    }

    fun draw(g: Graphics2D) {
        GameEngine.entities.forEach { it.draw(g) }



        hud.draw(g)
    }

}