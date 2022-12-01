package engine.entity.map

import engine.entity.factory.EntityFactory
import java.awt.Graphics2D

class Map() {
    private var tiles: HashSet<Tile> = hashSetOf()

    fun init() {
        tiles = EntityFactory.createMap()
    }

    fun draw(g: Graphics2D) {
        tiles.forEach {
            it.draw(g)
        }
    }
}