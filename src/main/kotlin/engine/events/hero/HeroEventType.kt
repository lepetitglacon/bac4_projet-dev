package engine.events.hero

import engine.events.ListenerEventTypeInterface

enum class HeroEventType : ListenerEventTypeInterface{
    HP_UP,
    HP_DOWN,
    HP_FULL,
    HP_ZERO,

    XP_UP,
    XP_LEVELUP,

    DEATH,
    REBIRTH,

    ATTACK
}