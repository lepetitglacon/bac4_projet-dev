package engine.event.input

class InputEvent {
    var type: InputListenerType = InputListenerType.UNDEFINED

    override fun toString(): String {
        return "event $type"
    }
}