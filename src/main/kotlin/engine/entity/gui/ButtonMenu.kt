package engine.entity.gui

import engine.EngineState
import engine.GameEngine
import engine.entity.gui.component.button.Button
import engine.entity.gui.component.WindowComponent
import engine.event.input.InputEvent
import engine.event.input.InputListener
import engine.event.input.InputListenerType
import java.awt.Color
import java.awt.Graphics2D

class ButtonMenu(
    var title: String = "",
    val buttons: MutableList<Button> = mutableListOf(),
    val window: WindowComponent = WindowComponent(),
    var listeningState: MutableList<EngineState> = mutableListOf()
) : InputListener {

    var currentButton: Int = 0
    val btnPadding: Int = 25

    fun draw(g: Graphics2D) {
        window.draw(g)

        // title
        val titleWidth = (g.fontMetrics.getStringBounds(title, g)).width
        val titleX = (window.x + (window.width / 2 - titleWidth / 2)).toInt()
        val titleY = window.y + 25
        g.color = Color.WHITE
        g.drawString(title, titleX, titleY)

        // btns
        val btnStartingX: Int = window.x + window.width / 4
        val btnStartingY: Int = titleY + btnPadding
        buttons.forEachIndexed { i, btn ->
            val btnX = btnStartingX
            val btnY = btnStartingY + btnPadding * i

            g.drawString(btn.string, btnX, btnY)
            if (i == currentButton) {
                g.drawString(">", btnX - btnPadding, btnY)
            }
        }
    }

    override fun onInputEvent(e: InputEvent) {
        if (listeningState.contains(GameEngine.state)) {
            super.onInputEvent(e)
            when (e.type) {
                InputListenerType.ENTER -> buttons[currentButton].onClick()
                InputListenerType.ESCAPE -> {}
                InputListenerType.SPACE -> {}
                InputListenerType.UP -> if (currentButton == 0) currentButton = buttons.size - 1 else currentButton--
                InputListenerType.LEFT -> {}
                InputListenerType.DOWN -> if (currentButton == buttons.size - 1) currentButton = 0 else currentButton++
                InputListenerType.RIGHT -> {}
                InputListenerType.UNDEFINED -> {}
            }
        }
    }
}