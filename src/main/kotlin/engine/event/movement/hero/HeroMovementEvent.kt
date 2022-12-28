package engine.event.movement.hero

class HeroMovementEvent {
    var type: HeroMovementListenerType = HeroMovementListenerType.DOWN
    var consumed = false

    override fun toString(): String {
        return "event: $type, consumed: $consumed"
    }
}