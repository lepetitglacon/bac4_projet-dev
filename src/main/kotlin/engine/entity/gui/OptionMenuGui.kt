package engine.entity.gui

import engine.GameEngine
import engine.entity.gui.component.StringComponent
import engine.entity.gui.component.WindowComponent
import engine.event.input.InputEvent
import engine.event.input.InputListenerType
import engine.game.GameState

class OptionMenuGui : Gui() {
    val buttons = mutableMapOf<String, Int>()

    init {
        val window = WindowComponent()
        window.gui = this
        window.x = 150
        window.y = 150
        window.width = 150
        window.height = 150

        val string = StringComponent()
        string.string = "Options"
        string.gui = this
        string.parent = window
        string.x = 25
        string.y = 25

        components.add(string)
        components.add(window)
    }

    override fun update() {

    }

    override fun onInputEvent(e: InputEvent) {
        if (GameEngine.game?.state == GameState.OPTIONS)
        {
            super.onInputEvent(e)
            if (e.type == InputListenerType.ESCAPE)
            {
                e.consumed = true
                GameEngine.game?.state = GameState.PLAY
            }
            if (e.type == InputListenerType.ENTER)
            {
                e.consumed = true
                GameEngine.game?.state = GameState.MAIN_MENU
            }
        }

    }
}