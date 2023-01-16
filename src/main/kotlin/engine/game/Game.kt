package engine.game

import engine.entity.`object`.Behelit
import engine.entity.`object`.Object
import engine.entity.`object`.ObjectFactory
import engine.entity.`object`.Soul
import engine.entity.gui.Gui
import engine.entity.gui.component.upgrade.Upgrade
import engine.entity.map.Map
import engine.entity.mob.enemy.Enemy
import engine.entity.mob.Hero
import engine.entity.mob.enemy.EnemyFactory
import engine.entity.mob.enemy.EnemyType
import engine.entity.mob.enemy.boss.EnemyBoss
import engine.entity.weapon.weapon.Gun
import engine.event.input.InputEvent
import engine.event.input.InputListener
import engine.resource.SpriteFactory
import java.awt.Graphics2D
import java.time.Instant
import kotlin.random.Random

class Game : InputListener {
    var map: Map = Map()
    var hero: Hero = Hero()
    var enemies = mutableListOf<Enemy>()
    var bosses = mutableListOf<EnemyBoss>()
    var objects = mutableListOf<Object>()
    var gui = Gui()
    var upgrades = mutableMapOf<Upgrade, Int>()

    var state: GameState = GameState.PLAY

    val startTime: Instant = Instant.now()
    var lastBossSpawned: Instant = Instant.now()
    var bossSpawnRateInSecond: Long = 10
    var bossSpawnRate: Long = bossSpawnRateInSecond * 1000 // ms

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
                // remove dead entities to free memory
                removeDeadEntities()

                // move entities
                // TODO changer le méthode update pour découpler le move() des entités
                map.update()
                hero.update()

                // spawn enemies
                if (enemies.size <= enemyPerWave) {
                    wave++
                    enemies.addAll(EnemyFactory.createFromRegistrer(((wave * enemyPerWave) * 0.8).toInt()))
                }
                // spawn bosses
                if (Instant.now().toEpochMilli() - lastBossSpawned.toEpochMilli() >= bossSpawnRate) {
                    enemies.add(EnemyFactory.createBoss())
                    lastBossSpawned = Instant.now()
                }

                // objects handling
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

                enemies.forEach { it.update() }
            }
            GameState.SHOP -> TODO()
        }

    }

    fun draw(g: Graphics2D)
    {
        map.draw(g)
        objects.forEach { it.draw(g) }
        hero.draw(g)
        enemies.forEach { it.draw(g) }

        gui.draw(g)
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
                is Gun -> weapon.projectiles.removeIf { it.hp <= 0 || it.center().clone().distance(hero.center()) > 10000  }
            }
        }
        enemies.removeIf {
            if (it.hp <= 0) {
                when(it.type) {
                    EnemyType.WARRIOR, EnemyType.SPECTRE -> {
                        if (Random.nextDouble(0.0, 1.0) <= it.xpDropRate) ObjectFactory.createSoul(it)
                    }
                    EnemyType.APOSTLE -> ObjectFactory.createBehelit(it)
                }
            }
            it.hp <= 0
        }
    }
}