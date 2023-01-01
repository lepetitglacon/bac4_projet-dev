package engine.entity.gui.component.button

import engine.EngineState
import engine.GameEngine

class ResumeButton : Button("Resume") {
    override fun onClick() {
        GameEngine.state = EngineState.PLAY
    }

}