package engine.entity.map

import engine.GameEngine
import engine.entity.factory.EntityFactory
import engine.maths.Vector2
import java.awt.Graphics2D

class Map() {
    var tiles: HashMap<Vector2, Tile> = hashMapOf()
    var minX: Int = GameEngine.window.WIDTH / 2 - GameEngine.window.WIDTH
    var maxX: Int = GameEngine.window.WIDTH / 2
    var minY: Int = GameEngine.window.HEIGHT / 2 - GameEngine.window.HEIGHT
    var maxY: Int = GameEngine.window.HEIGHT / 2

    fun init() {
        tiles = EntityFactory.createMap()
    }

    fun draw(g: Graphics2D) {
        tiles.forEach {
            it.value.move()
            it.value.draw(g)
        }
    }

    fun onWindowResize() {
        minX = GameEngine.window.WIDTH / 2 - GameEngine.window.WIDTH
        maxX = GameEngine.window.WIDTH / 2
        minY = GameEngine.window.HEIGHT / 2 - GameEngine.window.HEIGHT
        maxY = GameEngine.window.HEIGHT / 2
        EntityFactory.addTilesToMap().forEach { t, u ->
            tiles[t] = u
        }

    }

    fun onPlayerMovement() {
        minX = GameEngine.window.WIDTH / 2 - GameEngine.window.WIDTH + GameEngine.game.hero.position.x.toInt()
        maxX = GameEngine.window.WIDTH / 2 + GameEngine.game.hero.position.x.toInt()
        minY = GameEngine.window.HEIGHT / 2 - GameEngine.window.HEIGHT + GameEngine.game.hero.position.y.toInt()
        maxY = GameEngine.window.HEIGHT / 2 + GameEngine.game.hero.position.y.toInt()
        EntityFactory.addTilesToMap().forEach { t, u ->
            tiles[t] = u
        }

    }
}