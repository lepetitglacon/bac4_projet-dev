package engine.events

import engine.GameEngine
import engine.logger.Logger

abstract class AbstractListenerManager {
    private val listeners: MutableMap<ListenerInterface, MutableSet<ListenerEventTypeInterface>> = mutableMapOf()

    fun sub(l: ListenerInterface, e: MutableSet<ListenerEventTypeInterface>) {
        listeners[l] = e
    }
    fun unsub(l: ListenerInterface) {
        listeners.remove(l)
    }
    fun notify(e: ListenerEventTypeInterface) {
        if (GameEngine.debug) Logger.event("event fired : $e")
        listeners.filterValues { it.contains(e) }.forEach { it.key.on(e) }
    }
}