package engine.event.movement.hero

class HeroMovementListenerManager {
    val listeners = mutableMapOf<HeroMovementListener, MutableList<HeroMovementListenerType>>()

    fun on(e: HeroMovementEvent)
    {
        listeners.forEach {
            if (it.value.contains(e.type) && !e.consumed) {
                it.key.onHeroMovementEvent(e)
            }
        }
    }

    fun sub(e: HeroMovementListener, types: MutableList<HeroMovementListenerType>) { listeners[e] = types }
    fun subAll(e: HeroMovementListener) { sub(e, HeroMovementListenerType.values().toMutableList()) }
    fun unsub(e: HeroMovementListener, types: MutableList<HeroMovementListenerType>) { listeners[e]?.removeAll(types) }
    fun unsubAll(e: HeroMovementListener) { unsub(e, HeroMovementListenerType.values().toMutableList()) }
}