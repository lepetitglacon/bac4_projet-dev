package engine.event.movement.hero

import engine.GameEngine

interface HeroMovementListener {
    fun onHeroMovementEvent(e: HeroMovementEvent)
    {
        if (GameEngine.debug)
        {
            //println("$this received event : $e")
        }
    }
}