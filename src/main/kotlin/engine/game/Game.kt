package engine.game

import engine.GameEngine
import engine.entity.gui.Gui
import engine.entity.map.Map
import engine.entity.mob.enemy.Enemy
import engine.entity.mob.Hero
import engine.entity.mob.enemy.EnemyFactory
import engine.event.input.InputEvent
import engine.event.input.InputListener
import engine.resource.SpriteFactory
import java.awt.Graphics2D

class Game : InputListener {
    var map: Map = Map()
    var hero: Hero = Hero()
    var enemies = mutableListOf<Enemy>()

    var state: GameState = GameState.PLAY

    var shopMenu: Gui? = null

    fun init() {
        // resources
        SpriteFactory.registerSprites()

        // entities
        hero.init()
        map.build()

        enemies.addAll(EnemyFactory.createFromRegistrer())
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

    }
}