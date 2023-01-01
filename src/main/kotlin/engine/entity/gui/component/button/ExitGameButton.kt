package engine.entity.gui.component.button

import engine.GameEngine
import java.awt.event.WindowEvent

class ExitGameButton: Button("Exit game") {
    override fun onClick() {
        GameEngine.window.dispatchEvent(WindowEvent(GameEngine.window, WindowEvent.WINDOW_CLOSING))
    }
}