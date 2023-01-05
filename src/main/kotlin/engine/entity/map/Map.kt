package engine.entity.map

import engine.GameEngine
import engine.entity.map.tile.GrassTile
import engine.math.Vec2
import engine.resource.SpriteFactory
import engine.resource.SpriteFactory.TILE_SIZE
import java.awt.Graphics2D
import kotlin.random.Random

class Map
{
    val tiles: MutableMap<Vec2, Tile> = mutableMapOf()

    fun build()
    {
        fillGrass()
    }

    fun fillGrass() {
        for (i in 0..GameEngine.window.WIDTH / TILE_SIZE)
        {
            for (j in 0..GameEngine.window.HEIGHT / TILE_SIZE)
            {
                val tile: Tile = SpriteFactory.getRandomGrassTile()
                tile.x = i * TILE_SIZE
                tile.y = j * TILE_SIZE
                tiles[Vec2(i,j)] = tile
            }
        }
    }

    fun rebuild()
    {

    }

    fun update() {

    }

    fun draw(g: Graphics2D) {
//        println("map drawned with ${tiles.size} tiles")
        tiles.forEach { it.value.draw(g) }
    }
}