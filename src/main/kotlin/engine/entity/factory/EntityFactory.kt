package engine.entity.factory

import engine.maths.Vector2
import engine.GameEngine
import engine.entity.enums.DrawablePosition
import engine.entity.enums.MapTilePosition
import engine.entity.map.Tile
import engine.entity.mob.Enemy
import engine.entity.sprite.Sprite
import kotlin.random.Random

object EntityFactory {
    fun createRandomEnemy(): Enemy {
        val enemy = Enemy()

        // get coordinates
        val heroXMin = GameEngine.game.hero.position.x - GameEngine.window.WIDTH/2
        val heroXMax = GameEngine.game.hero.position.x + GameEngine.window.WIDTH/2
        val heroYMin = GameEngine.game.hero.position.y - GameEngine.window.HEIGHT/2
        val heroYMax = GameEngine.game.hero.position.y + GameEngine.window.HEIGHT/2

        if (Random.nextBoolean()) {
            enemy.position.x = Random.nextDouble(heroXMin, heroXMax)
            val y = if (Random.nextBoolean()) heroYMin else heroYMax
            enemy.position.y = y
        } else {
            val x = if (Random.nextBoolean()) heroXMin else heroXMax
            enemy.position.x = x
            enemy.position.y = Random.nextDouble(heroYMin, heroYMax)
        }
        enemy.position = Vector2(enemy.position.x + 50 / 2, enemy.position.y + 50 / 2)

        // conf
        enemy.drawingPositionType = DrawablePosition.RELATIVE_TO_HERO
        enemy.speed = Random.nextDouble(1.0, 3.0)
        return enemy
    }

    fun createEnemyFromPosition(v: Vector2): Enemy {
        val enemy = Enemy()
        enemy.position = v
        return enemy
    }

    fun createMap(): HashSet<Tile> {
        val tiles = hashSetOf<Tile>()
        for (i in 0..64) {
            for (j in 0..64) {
                val x = i * Sprite.TILE_SIZE
                val y = j * Sprite.TILE_SIZE
                val tile = Tile(Vector2(x.toDouble(), y.toDouble()))
                if (Random.nextBoolean()) tile.sprite = Sprite.getTilemap(MapTilePosition.LEFT)
                tiles.add(tile)
            }
        }
        return tiles
    }
}