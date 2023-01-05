package engine.event.input

import engine.GameEngine

class InputListenerManager
{
    val listeners = mutableMapOf<InputListener, MutableList<InputListenerType>>()

    fun on(e: InputEvent)
    {
        listeners.forEach {
            if (it.value.contains(e.type) && !e.consumed) {
                it.key.onInputEvent(e)
            }
        }
    }

    fun sub(e: InputListener, types: MutableList<InputListenerType>) { listeners[e] = types }
    fun sub(e: InputListener) { sub(e, InputListenerType.values().toMutableList()) }
    fun unsub(e: InputListener, types: MutableList<InputListenerType>) { listeners[e]?.removeAll(types) }
    fun unsub(e: InputListener) { unsub(e, InputListenerType.values().toMutableList()) }
}