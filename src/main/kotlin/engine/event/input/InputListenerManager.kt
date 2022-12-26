package engine.event.input

class InputListenerManager
{
    val listeners = mutableMapOf<InputListener, MutableList<InputListenerType>>()

    fun on(e: InputEvent)
    {
        listeners.forEach {
            if (it.value.contains(e.type)) {
                it.key.onInputEvent(e)
            }
        }
    }

    fun sub(e: InputListener, types: MutableList<InputListenerType>) { listeners[e] = types }
    fun subAll(e: InputListener) { sub(e, InputListenerType.values().toMutableList()) }
    fun unsub(e: InputListener, types: MutableList<InputListenerType>) { listeners[e]?.removeAll(types) }
    fun unsubAll(e: InputListener) { unsub(e, InputListenerType.values().toMutableList()) }
}