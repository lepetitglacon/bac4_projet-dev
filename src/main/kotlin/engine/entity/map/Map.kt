package engine.entity.map

import java.awt.Graphics2D
import java.awt.Point

class Map
{
    val tiles: MutableMap<Point, Tile> = mutableMapOf()

    fun update() {

    }

    fun draw(g: Graphics2D) {
        tiles.forEach { it.value.draw(g) }
    }
}