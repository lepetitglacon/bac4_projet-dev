package engine.entity.factory

import engine.GameEngine
import engine.ResourceManager
import engine.entity.enums.MapTilePosition
import engine.logger.Logger
import java.awt.image.BufferedImage
import java.io.File
import java.io.IOException
import javax.imageio.ImageIO


object SpriteFactory {
    const val TILE_SIZE = 64

    private var hero: BufferedImage? = null
    private var enemy: BufferedImage? = null
    private var map: BufferedImage? = null
    private var objects: BufferedImage? = null
    private var voidGameOver: BufferedImage? = null

    private var weaponStink: BufferedImage? = null


    fun loadFiles() {
        hero = loadSprite("player")
        enemy = loadSprite("pokemons")
        map = loadSprite("tilemap")
        objects = loadSprite("objects")
        voidGameOver = loadSprite("voidondeath")

        // weapons
        weaponStink = loadSprite("weapons/Stink")
    }

    fun loadSprite(file: String): BufferedImage? {
        var sprite: BufferedImage? = null
        try {
            sprite = ImageIO.read(ResourceManager.getPng("assets/img/$file"))
        } catch (e: IOException) {
            Logger.info(e.message ?: "no message")
        }
        return sprite
    }

    fun getWeaponSprite(w: String): BufferedImage? {
        return loadSprite("weapons/${w}")
    }

    fun getHeroSprite(): BufferedImage {
        if (hero == null) {
            hero = loadSprite("player")
        }
        return hero!!.getSubimage(0,0, TILE_SIZE, TILE_SIZE)
    }

    fun getPokemonSprite(): BufferedImage {
        if (enemy == null) {
            enemy = loadSprite("pokemons")
        }
        return enemy!!.getSubimage(0, 0, TILE_SIZE, TILE_SIZE)
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

    fun getGameOver(): BufferedImage {
        if (voidGameOver == null) {
            voidGameOver = loadSprite("voidondeath")
        }
        return voidGameOver!!
    }

    fun getSoul(): BufferedImage {
        if (objects == null) {
            objects = loadSprite("objects")
        }
        return objects!!.getSubimage(0,0, TILE_SIZE, TILE_SIZE)
    }
}