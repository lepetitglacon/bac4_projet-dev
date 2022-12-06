package engine.events.hero

import engine.events.ListenerInterface

interface HeroListener : ListenerInterface {

    fun onXpGain() {

    }
}