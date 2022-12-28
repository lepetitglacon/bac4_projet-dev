package engine.event.movement.hero

import engine.GameEngine
import engine.entity.sprite.hero.HeroPositionState
import engine.game.GameState
import engine.resource.SpriteFactory
import java.awt.Point

enum class HeroMovementListenerType {
    UP,
    UP_RIGHT,
    RIGHT,
    DOWN_RIGHT,
    DOWN,
    DOWN_LEFT,
    LEFT,
    UP_LEFT,

    IDLE;

    var walking = 0

    fun getPoint() : Point {
        if (GameEngine.game?.state == GameState.PLAY) {
            // change walking every 16 ticks
            if (GameEngine.ticksCounter % 16 == 0) walking++
            // reset animation
            if (walking > 3) walking = 0
        }
        // return sprite position
        return when (this) {
            UP -> Point(0 + SpriteFactory.TILE_SIZE * walking, 3 * (SpriteFactory.TILE_SIZE + 10))
            UP_RIGHT -> Point(0 + SpriteFactory.TILE_SIZE * walking, 3 * (SpriteFactory.TILE_SIZE + 10))
            UP_LEFT -> Point(0 + SpriteFactory.TILE_SIZE * walking, 3 * (SpriteFactory.TILE_SIZE + 10))
            RIGHT -> Point(0 + SpriteFactory.TILE_SIZE * walking, 2 * (SpriteFactory.TILE_SIZE + 10))
            DOWN_RIGHT -> Point(0 + SpriteFactory.TILE_SIZE * walking, 0)
            DOWN -> Point(0 + SpriteFactory.TILE_SIZE * walking, 0)
            DOWN_LEFT -> Point(0 + SpriteFactory.TILE_SIZE * walking, 0)
            LEFT -> Point(0 + SpriteFactory.TILE_SIZE * walking, SpriteFactory.TILE_SIZE + 10)
            IDLE -> Point()
        }
    }
}