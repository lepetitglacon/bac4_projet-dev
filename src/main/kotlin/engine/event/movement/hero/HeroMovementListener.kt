package engine.event.movement.hero

import engine.GameEngine
import engine.event.input.InputEvent

interface HeroMovementListener {
    fun onHeroMovementEvent(e: HeroMovementEvent)
    {
        if (GameEngine.debug)
        {
            println("$this received event : $e")
        }
    }
}