package engine.entity.gui.component.button

import engine.EngineState
import engine.GameEngine
import engine.game.GameFactory

class NewGameButton : Button("New Game") {
    override fun onClick() {
        GameEngine.state = EngineState.PLAY
        // Event binding
        GameEngine.game = GameFactory.createGame()
        GameEngine.game!!.init()
        GameEngine.inputListenerManager.sub(GameEngine.game!!)
    }
}