package engine.entities.factory

import engine.maths.Vector2
import engine.GameEngine
import engine.entities.enums.DrawablePosition
import engine.entities.enums.MapTilePosition
import engine.entities.item.Soul
import engine.entities.map.Tile
import game.mob.Enemy
import game.mob.Hero
import kotlin.random.Random

object EntityFactory {
    fun createRandomEnemy(): Enemy {
        val enemy = Enemy()

        // get coordinates
        val heroXMin = GameEngine.game.hero.position.x - GameEngine.window.WIDTH/2 - enemy.width
        val heroXMax = GameEngine.game.hero.position.x + GameEngine.window.WIDTH/2 + enemy.width
        val heroYMin = GameEngine.game.hero.position.y - GameEngine.window.HEIGHT/2 - enemy.height
        val heroYMax = GameEngine.game.hero.position.y + GameEngine.window.HEIGHT/2 + enemy.height

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
        enemy.speed = Random.nextDouble(0.5, 1.5)
        enemy.damages = 1
        return enemy
    }

    fun createHero(): Hero {
        return Hero()
    }

    fun createEnemyFromPosition(v: Vector2): Enemy {
        val enemy = Enemy()
        enemy.position = v
        return enemy
    }

    fun createMap(): HashMap<Vector2, Tile> {
        val tiles = hashMapOf<Vector2, Tile>()

        for (i in GameEngine.game.map.minX/SpriteFactory.TILE_SIZE  ..GameEngine.game.map.maxX/SpriteFactory.TILE_SIZE+1) {
            for (j in GameEngine.game.map.minY/SpriteFactory.TILE_SIZE..GameEngine.game.map.maxY/SpriteFactory.TILE_SIZE+1) {
                val position = Vector2((i * SpriteFactory.TILE_SIZE).toDouble(), (j * SpriteFactory.TILE_SIZE).toDouble())
                val tile = Tile(position)
                if (Random.nextBoolean()) tile.sprite = SpriteFactory.getTilemap(MapTilePosition.LEFT)
                tiles[position] = tile
            }
        }
        return tiles
    }

    fun addTilesToMap(): HashMap<Vector2, Tile> {
        val tiles = hashMapOf<Vector2, Tile>()

        for (i in GameEngine.game.map.minX/SpriteFactory.TILE_SIZE -1  ..GameEngine.game.map.maxX/SpriteFactory.TILE_SIZE+1) {
            for (j in GameEngine.game.map.minY/SpriteFactory.TILE_SIZE -1..GameEngine.game.map.maxY/SpriteFactory.TILE_SIZE+1) {
                val position = Vector2((i * SpriteFactory.TILE_SIZE).toDouble(), (j * SpriteFactory.TILE_SIZE).toDouble())
                if (!GameEngine.game.map.tiles.containsKey(position)) {
                    val tile = Tile(position)
                    if (Random.nextBoolean()) tile.sprite = SpriteFactory.getTilemap(MapTilePosition.DIRT)
                    tiles[position] = tile
                }
            }
        }
        return tiles
    }

    fun createSoul(position: Vector2): Soul {
        return Soul(position)
    }
}