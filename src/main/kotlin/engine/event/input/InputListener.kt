package engine.event.input

import engine.GameEngine

interface InputListener {
    fun onInputEvent(e: InputEvent)
    {
        if (GameEngine.debug)
        {
            println("$this received event : $e")
        }
    }
}