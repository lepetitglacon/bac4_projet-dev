package engine.event.input

interface InputListener {
    fun onInputEvent(e: InputEvent)
    {
        println("$this received event : $e")
    }
}