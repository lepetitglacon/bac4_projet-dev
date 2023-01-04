package engine.game


import engine.entity.`object`.Behelit
import engine.entity.`object`.Object
import engine.entity.`object`.ObjectFactory
import engine.entity.`object`.Soul
import engine.entity.map.Map
import engine.entity.mob.enemy.Enemy
import engine.entity.mob.Hero
import engine.entity.mob.enemy.EnemyFactory
import engine.entity.weapon.weapon.Gun
import engine.event.input.InputEvent
import engine.event.input.InputListener
import engine.resource.SpriteFactory
import java.awt.Graphics2D
import kotlin.random.Random

class Game : InputListener {
    var map: Map = Map()
    var hero: Hero = Hero()
    var enemies = mutableListOf<Enemy>()
    var objects = mutableListOf<Object>()

    var state: GameState = GameState.PLAY
    var wave: Int = 1
    var enemyPerWave = 6

    var souls: Int = 0
    var behelits: Int = 0

    fun init() {
        // resources
        SpriteFactory.registerSprites()

        // entities
        hero.init()
        map.build()

        enemies.addAll(EnemyFactory.createFromRegistrer(wave * enemyPerWave))
    }

    fun update()
    {
        updateState(null)

        when(state)
        {
            GameState.PLAY ->
            {
                // Init
                removeDeadEntities()

                if (hero.xp >= hero.maxXp) hero.levelUp(1.2)

                if (enemies.size <= enemyPerWave) {
                    wave++
                    enemies.addAll(EnemyFactory.createFromRegistrer(wave * enemyPerWave))
                }

                objects.removeIf {
                    if (hero.collides(it)) {
                        when (it) {
                            is Soul -> {
                                souls++
                                hero.xp += it.xp
                            }
                            is Behelit -> behelits++
                        }
                    }
                    hero.collides(it)
                }

                // Update
                map?.update()
                hero?.update()
                enemies.forEach { it.update() }

            }
            GameState.SHOP -> TODO()
        }

    }

    fun draw(g: Graphics2D)
    {
        map?.draw(g)
        objects.forEach { it.draw(g) }
        hero?.draw(g)
        enemies.forEach { it.draw(g) }
    }

    fun updateState(e: InputEvent?)
    {

    }

    override fun onInputEvent(e: InputEvent)
    {
        super.onInputEvent(e)
        updateState(e)
    }

    fun removeDeadEntities() {
        hero.weapons.forEach { weapon ->
            when (weapon) {
                is Gun -> weapon.projectiles.removeIf { it.hp <= 0  }
            }
        }
        enemies.removeIf {
            if (it.hp <= 0) {
                if (Random.nextDouble(0.0, 1.0) <= it.xpDropRate) ObjectFactory.createSoul(it)
            }
            it.hp <= 0
        }
    }
}