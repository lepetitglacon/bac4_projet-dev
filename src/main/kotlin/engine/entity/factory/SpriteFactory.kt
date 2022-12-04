package engine.entity.factory

import engine.entity.enums.MapTilePosition
import engine.logger.Logger
import java.awt.image.BufferedImage
import java.io.File
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
            val url = "assets/img/$file.png"
            sprite = ImageIO.read(File(url))
            Logger.log("sprite loaded: $url")
        } catch (e: IOException) {
            Logger.info(e.message ?: "no message")
        }
        return sprite
    }

    fun getWeaponSprite(w: String): BufferedImage? {
        return loadSprite("weapons/${w}")
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
            MapTilePosition.GRASS -> map!!.getSubimage(0, 0, TILE_SIZE, TILE_SIZE)
            MapTilePosition.DIRT -> map!!.getSubimage(TILE_SIZE, 0, TILE_SIZE, TILE_SIZE)
            MapTilePosition.LEFT -> map!!.getSubimage(TILE_SIZE, TILE_SIZE, TILE_SIZE, TILE_SIZE)
        }

    }
}