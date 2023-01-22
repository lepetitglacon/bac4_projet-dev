package engine.entity.gui.component.button

import engine.GameEngine

class GoToMainMenuButton : Button("Go to main menu") {
    override fun onClick() {
        GameEngine.gameEnd()
    }
}