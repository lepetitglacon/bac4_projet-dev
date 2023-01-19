package engine.entity.map

import engine.GameEngine
import engine.math.Vec2
import engine.resource.SpriteFactory
import engine.resource.SpriteFactory.TILE_SIZE
import java.awt.Graphics2D

class Map
{
    val tiles: MutableMap<Vec2, Tile> = mutableMapOf()
    var minX: Int = GameEngine.window.WIDTH / 2 - GameEngine.window.WIDTH
    var maxX: Int = GameEngine.window.WIDTH / 2
    var minY: Int = GameEngine.window.HEIGHT / 2 - GameEngine.window.HEIGHT
    var maxY: Int = GameEngine.window.HEIGHT / 2

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

    fun onWindowResize() {
        minX = GameEngine.window.WIDTH / 2 - GameEngine.window.WIDTH
        maxX = GameEngine.window.WIDTH / 2
        minY = GameEngine.window.HEIGHT / 2 - GameEngine.window.HEIGHT
        maxY = GameEngine.window.HEIGHT / 2
        SpriteFactory.addTilesToMap().forEach { t, u ->
            tiles[t] = u
        }
    }

    fun onPlayerMovement() {
        minX = GameEngine.window.WIDTH / 2 - GameEngine.window.WIDTH + GameEngine.game?.hero?.x!!.toInt()
        maxX = GameEngine.window.WIDTH / 2 + GameEngine.game?.hero?.x!!.toInt()
        minY = GameEngine.window.HEIGHT / 2 - GameEngine.window.HEIGHT + GameEngine.game?.hero?.y!!.toInt()
        maxY = GameEngine.window.HEIGHT / 2 + GameEngine.game?.hero?.y!!.toInt()
        SpriteFactory.addTilesToMap().forEach { t, u ->
            tiles[t] = u
            println("${tiles.size}")
        }
    }
}