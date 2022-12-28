package engine.entity.map

import engine.GameEngine
import engine.entity.map.tile.Grass
import engine.resource.SpriteFactory
import engine.resource.SpriteFactory.TILE_SIZE
import java.awt.Graphics2D
import java.awt.Point

class Map
{
    val tiles: MutableMap<Point, Tile> = mutableMapOf()

    fun build()
    {
        fillGrass()
    }

    fun fillGrass() {
        println(GameEngine.window.WIDTH / TILE_SIZE)
        println(GameEngine.window.HEIGHT / TILE_SIZE)
        for (i in 0..GameEngine.window.WIDTH / TILE_SIZE)
        {
            for (j in 0..GameEngine.window.HEIGHT / TILE_SIZE)
            {
                val grass = Grass()
                grass.x = i * TILE_SIZE
                grass.y = j * TILE_SIZE
                tiles[Point(i,j)] = grass
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