package engine.game

import engine.entity.map.Map
import engine.entity.mob.enemy.Enemy
import engine.entity.mob.Hero
import engine.entity.mob.enemy.EnemyFactory
import engine.entity.weapon.weapon.Gun
import engine.event.input.InputEvent
import engine.event.input.InputListener
import engine.resource.SpriteFactory
import java.awt.Graphics2D

class Game : InputListener {
    var map: Map = Map()
    var hero: Hero = Hero()
    var enemies = mutableListOf<Enemy>()

    var state: GameState = GameState.PLAY
    var wave: Int = 1
    var enemyPerWave = 6

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

                if (hero.xp > hero.maxXp) hero.levelUp(hero.maxXp + 25 * 2)

                if (enemies.size <= enemyPerWave) {
                    wave++
                    enemies.addAll(EnemyFactory.createFromRegistrer(wave * enemyPerWave))
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
        enemies.forEach { it.draw(g) }
        hero?.draw(g)
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
            if (it.hp <= 0) hero.xp = it.xpToGive
            it.hp <= 0
        }
    }
}