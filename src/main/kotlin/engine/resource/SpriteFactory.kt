package engine.resource

import engine.entity.sprite.hero.HeroSprite
import engine.entity.sprite.Sprite

object SpriteFactory {
    const val TILE_SIZE = 64

    val sprites: MutableMap<String, Sprite> = mutableMapOf()

    fun registerSprites()
    {
        sprites["hero"] = HeroSprite(ResourceManager.getSpriteSheet("hero"))
        sprites["warrior"] = Sprite(ResourceManager.getSpriteSheet("pokemons").getSubimage(TILE_SIZE, 3*TILE_SIZE, TILE_SIZE,TILE_SIZE))
        sprites["warrior_1"] = Sprite(ResourceManager.getSpriteSheet("pokemons").getSubimage(TILE_SIZE, 2*TILE_SIZE, TILE_SIZE,TILE_SIZE))
        sprites["warrior_2"] = Sprite(ResourceManager.getSpriteSheet("pokemons").getSubimage(TILE_SIZE, 9*TILE_SIZE, TILE_SIZE,TILE_SIZE))

        val map = ResourceManager.getSpriteSheet("map")
        sprites["map_grass"] = Sprite(map.getSubimage(TILE_SIZE,0, TILE_SIZE,TILE_SIZE))
        sprites["map_path_horizontal"] = Sprite(map.getSubimage(5 * TILE_SIZE,4 * TILE_SIZE, TILE_SIZE,TILE_SIZE))

    }

    fun get(name: String): Sprite {
        return sprites[name] ?: Sprite()
    }
}