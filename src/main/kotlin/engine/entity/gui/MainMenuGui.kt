package engine.entity.gui

import engine.GameEngine
import engine.entity.gui.component.ButtonComponent
import engine.entity.gui.component.StringComponent
import engine.entity.gui.component.WindowComponent
import engine.event.input.InputEvent
import engine.event.input.InputListenerType
import engine.game.GameState

class MainMenuGui : Gui() {
    val buttons: MutableList<ButtonComponent> = mutableListOf()

    init {
        val window = WindowComponent()
        window.gui = this
        window.x = 150
        window.y = 150
        window.width = 150
        window.height = 150

        val string = StringComponent()
        string.string = "BONJOUR les amis"
        string.gui = this
        string.parent = window
        string.x = 25
        string.y = 25

        val btnLaunchGame= ButtonComponent()
        btnLaunchGame.string = StringComponent()
        btnLaunchGame.string.string = "New game"
        btnLaunchGame.x = 50
        btnLaunchGame.y = 50

        components.add(window)
        components.add(string)
        components.add(btnLaunchGame)
    }

    override fun update() {

    }

    override fun onInputEvent(e: InputEvent) {
//        if (GameEngine.game?.state == GameState.MAIN_MENU)
//        {
//            super.onInputEvent(e)
//            when (e.type)
//            {
//                InputListenerType.ENTER ->
//                {
//                    e.consumed = true
//                    GameEngine.game?.state = GameState.PLAY
//                }
//                InputListenerType.UP ->
//                {
//                    e.consumed = true
//                    // TODO go to previous button
//                }
//                InputListenerType.DOWN ->
//                {
//                    e.consumed = true
//                    // TODO go to next button
//                }
//                else -> {}
//            }
//
//        }
    }
}