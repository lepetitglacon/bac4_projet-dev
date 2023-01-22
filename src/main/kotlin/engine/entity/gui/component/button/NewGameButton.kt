package engine.entity.gui.component.button

import engine.EngineState
import engine.GameEngine
import engine.game.GameFactory

class NewGameButton : Button("New Game") {
    override fun onClick() {
        GameEngine.state = EngineState.PLAY
        GameEngine.game = GameFactory.createGame()
        GameEngine.game!!.init()
        // Event binding
        GameEngine.inputListenerManager.sub(GameEngine.game!!)
    }
}