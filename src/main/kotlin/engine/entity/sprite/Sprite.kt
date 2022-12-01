package engine.entity.sprite

import java.awt.image.BufferedImage
import java.io.IOException
import javax.imageio.ImageIO


object Sprite {
    private var heroSpriteSheet: BufferedImage? = null
    private var spiderSpriteSheet: BufferedImage? = null
    const val TILE_SIZE = 64

    fun loadSprite(file: String): BufferedImage? {
        var sprite: BufferedImage? = null
        try {
            sprite = ImageIO.read(javaClass.getResource("/images/$file.png"))
        } catch (e: IOException) {
            Renderer.logger.info(e.message)
        }
        return sprite
    }

    fun getHeroSprite(xGrid: Int, yGrid: Int): BufferedImage {
        if (heroSpriteSheet == null) {
            heroSpriteSheet = loadSprite("player")
        }
        return heroSpriteSheet!!.getSubimage(0,0, TILE_SIZE, TILE_SIZE)
    }

    fun getPokemonSprite(xGrid: Int, yGrid: Int): BufferedImage {
        if (spiderSpriteSheet == null) {
            spiderSpriteSheet = loadSprite("pokemons")
        }
        return spiderSpriteSheet!!.getSubimage(TILE_SIZE, TILE_SIZE, TILE_SIZE, TILE_SIZE)
    }
}