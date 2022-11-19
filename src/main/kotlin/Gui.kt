import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics2D
import java.awt.Point
import kotlin.math.cos

object Gui {
    val GUI_DISPLAY_START_PANEL = Vector2(0.0, 580.0)
    val GUI_DISPLAY_START_DEBUG = Point(0, 0)

    val CHAR_SIZE = 5
    val MARGIN_Y = 25
    val MARGIN_X = 10

    val infos = mutableListOf<String>()

    fun draw(g: Graphics2D) {
        drawDebug(g)
        drawPanel(g)
        drawUserInput(g)
    }

    private fun drawUserInput(g: Graphics2D) {
        val startX = GUI_DISPLAY_START_DEBUG.x + 150
        val startY = GUI_DISPLAY_START_DEBUG.y
        val width = 25
        val height = 25
        val centerX = (startX + width/ 2)
        val centerY = (startY + height/ 2)
        val normalized = Vector2.normalize(Renderer.userInputVector)
        val endX = centerX + normalized.x * (width/2)
        val endY = centerY + normalized.y * (height/2)

        g.color = Color.RED
        g.fillRect(startX, startY, width, height)
        g.color = Color.BLUE
        g.fillOval(startX, startY, width, height)
        g.color = Color.GREEN
        g.drawLine(centerX, centerY, endX.toInt(), endY.toInt())
    }

    private fun drawDebug(g: Graphics2D) {
        loadStrings()

        // background
        g.color = Color(100, 100, 100, 100)
        g.fillRect(GUI_DISPLAY_START_DEBUG.x, GUI_DISPLAY_START_DEBUG.y, 200, (infos.size + 1) * MARGIN_Y)
        g.color = Color.WHITE

        // infos
        infos.forEachIndexed { i, it ->
            g.drawString(it, MARGIN_X, MARGIN_Y * (i+1))
        }
        g.color = Color.BLACK
        infos.clear()
    }

    private fun drawPanel(g: Graphics2D) {
        // background
        g.color = Color(100, 100, 100, 100)
        g.fillRect(GUI_DISPLAY_START_PANEL.x.toInt(), GUI_DISPLAY_START_PANEL.y.toInt(), 200, 20)
        g.color = Color.WHITE
    }

    private fun loadStrings() {
        infos.add("DEBUG")
        infos.add("Hero position x:${Renderer.hero.position.x} y:${Renderer.hero.position.y}")
        infos.add("Hero speed :${Renderer.hero.speed}")
        infos.add("Hero hp :${Renderer.hero.hp}")
        infos.add("Hero deaths :${Renderer.deaths}")
//        infos.add("Hero direction:${Renderer.hero.direction.name}")
//        infos.add("Manche ${Renderer.wave}")
//        infos.add("Balles tirées (toujours actives) ${Renderer.bullets.size}")
    }
}