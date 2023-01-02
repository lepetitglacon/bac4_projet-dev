package engine.entity.sprite.hero

import engine.EngineState
import engine.GameEngine
import engine.entity.sprite.Sprite
import engine.event.movement.hero.HeroMovementEvent
import engine.event.movement.hero.HeroMovementListener
import engine.event.movement.hero.HeroMovementListenerType
import engine.game.Game
import engine.game.GameState
import engine.resource.SpriteFactory.TILE_SIZE
import java.awt.image.BufferedImage

class HeroSprite(override var image: BufferedImage?) : Sprite(), HeroMovementListener {
    var positionState = HeroMovementListenerType.UP

    init {
        GameEngine.heroMovementListenerManager.subAll(this)
    }

    fun get(): BufferedImage?
    {
        return image?.getSubimage(positionState.getPoint().x, positionState.getPoint().y, TILE_SIZE, TILE_SIZE)
    }

    override fun onHeroMovementEvent(e: HeroMovementEvent) {
        if (GameEngine.state == EngineState.PLAY)
        {
            super.onHeroMovementEvent(e)
            e.consumed
            positionState = e.type
        }
    }
}