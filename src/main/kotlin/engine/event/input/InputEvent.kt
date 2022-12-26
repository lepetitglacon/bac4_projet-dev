package engine.event.input

class InputEvent {
    var type: InputListenerType = InputListenerType.UNDEFINED
    var consumed = false

    override fun toString(): String {
        return "event: $type, consumed: $consumed"
    }
}