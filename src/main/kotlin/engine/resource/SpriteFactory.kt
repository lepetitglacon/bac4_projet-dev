package engine.resource

import engine.entity.map.Tile
import engine.entity.map.tile.GrassTile
import engine.entity.sprite.hero.HeroSprite
import engine.entity.sprite.Sprite

object SpriteFactory {
    const val TILE_SIZE = 64

    val sprites: MutableMap<String, Sprite> = mutableMapOf()

    fun registerSprite(key: String, sprite: Sprite) {
        sprites[key] = sprite
    }

    fun registerSprites()
    {
        // heroes
        sprites["hero"] = HeroSprite(ResourceManager.getSpriteSheet("hero"))

        // enemies
        val warriors = ResourceManager.getSpriteSheet("pokemons")
        sprites["warrior_0"] = Sprite(warriors.getSubimage(TILE_SIZE, 3*TILE_SIZE, TILE_SIZE,TILE_SIZE))
        sprites["warrior_1"] = Sprite(warriors.getSubimage(TILE_SIZE, 2*TILE_SIZE, TILE_SIZE,TILE_SIZE))
        sprites["warrior_2"] = Sprite(warriors.getSubimage(TILE_SIZE, 9*TILE_SIZE, TILE_SIZE,TILE_SIZE))
        // bosses
        sprites["boss_corkus"] = Sprite(warriors.getSubimage(TILE_SIZE, 15*TILE_SIZE, TILE_SIZE,TILE_SIZE))


        // map
        val map = ResourceManager.getSpriteSheet("map")
        sprites["map_grass_0"] = Sprite(map.getSubimage(TILE_SIZE,0, TILE_SIZE,TILE_SIZE))
        sprites["map_grass_1"] = Sprite(map.getSubimage(TILE_SIZE*2,0, TILE_SIZE,TILE_SIZE))
        sprites["map_grass_2"] = Sprite(map.getSubimage(TILE_SIZE*2,0, TILE_SIZE,TILE_SIZE))
        sprites["map_grass_3"] = Sprite(map.getSubimage(TILE_SIZE*3,0, TILE_SIZE,TILE_SIZE))
        sprites["map_path_horizontal"] = Sprite(map.getSubimage(5 * TILE_SIZE,4 * TILE_SIZE, TILE_SIZE,TILE_SIZE))

        // objects / items
        //sprites["object_soul"] = Sprite(ResourceManager.getSpriteSheet("mark"))
        sprites["object_mark"] = Sprite(ResourceManager.getSpriteSheet("mark"))
        sprites["object_behelit"] = Sprite(ResourceManager.getSpriteSheet("behelit"))

    }

    fun get(name: String): Sprite {
        return sprites[name] ?: Sprite()
    }

    fun getRandomGrassTile() : Tile {
        val rdm = sprites.filterKeys { it.startsWith("map_grass") }
        val tile = GrassTile()
        tile.sprite = rdm.entries.shuffled().first().value
        return tile
    }
}