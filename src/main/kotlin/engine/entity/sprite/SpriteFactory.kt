package engine.entity.sprite

import engine.entity.enums.MapTilePosition
import engine.logger.Logger
import java.awt.image.BufferedImage
import java.io.IOException
import javax.imageio.ImageIO


object SpriteFactory {
    private var heroSpriteSheet: BufferedImage? = null
    private var spiderSpriteSheet: BufferedImage? = null
    private var map: BufferedImage? = null
    const val TILE_SIZE = 64

    fun loadSprite(file: String): BufferedImage? {
        var sprite: BufferedImage? = null
        try {
            sprite = ImageIO.read(javaClass.getResource("images/$file.png"))
        } catch (e: IOException) {
            Logger.info(e.message ?: "no message")
        }
        return sprite
    }

    fun getHeroSprite(): BufferedImage {
        if (heroSpriteSheet == null) {
            heroSpriteSheet = loadSprite("player")
        }
        return heroSpriteSheet!!.getSubimage(0,0, TILE_SIZE, TILE_SIZE)
    }

    fun getPokemonSprite(): BufferedImage {
        if (spiderSpriteSheet == null) {
            spiderSpriteSheet = loadSprite("pokemons")
        }
        return spiderSpriteSheet!!.getSubimage(0, 0, TILE_SIZE, TILE_SIZE)
    }

    fun getTilemap(mapTilePosition: MapTilePosition): BufferedImage {
        if (map == null) {
            map = loadSprite("tilemap")
        }
        return when (mapTilePosition) {
            MapTilePosition.CENTER -> map!!.getSubimage(0, 0, TILE_SIZE, TILE_SIZE)
            MapTilePosition.RIGHT -> map!!.getSubimage(0, TILE_SIZE, TILE_SIZE, TILE_SIZE)
            MapTilePosition.LEFT -> map!!.getSubimage(TILE_SIZE, TILE_SIZE, TILE_SIZE, TILE_SIZE)
        }

    }
}