package engine.entity.gui.component.button

import engine.EngineState
import engine.GameEngine

class GoToMainMenuButton : Button("Go to main menu") {
    override fun onClick() {
        GameEngine.game = null
        GameEngine.state = EngineState.MAIN_MENU
    }
}