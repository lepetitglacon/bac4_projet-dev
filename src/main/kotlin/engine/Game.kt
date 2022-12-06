package engine

import engine.entities.CollidableEntity
import engine.entities.Entity
import engine.entities.MovableEntity
import engine.states.EngineState
import engine.entities.factory.EntityFactory
import engine.entities.factory.WeaponFactory
import engine.entities.gui.Gui
import engine.entities.gui.shop.Shop
import engine.entities.map.Map
import engine.events.ListenerEventTypeInterface
import engine.events.hero.HeroEventType
import engine.events.hero.HeroListener
import engine.events.input.InputEventType
import engine.events.input.InputListener
import engine.maths.Vector2
import game.mob.Hero
import engine.sound.SoundManager
import engine.states.GameState
import java.awt.Graphics2D
import kotlin.random.Random

class Game : HeroListener, InputListener {
    val hero: Hero = Hero()
    val map: Map = Map()
    val gui: Gui = Gui()
    val shop: Shop = Shop()
    val staticEntities: MutableList<Entity> = mutableListOf()
    val movableEntities: MutableList<MovableEntity> = mutableListOf()
    val collidableEntities: MutableList<CollidableEntity> = mutableListOf()
    val objects: MutableList<CollidableEntity> = mutableListOf()

    var state: GameState = GameState.UNINITIALIZED

    val ENEMIES_PER_WAVE = 6
    var wave: Int = 1


    fun init() {
        hero.weapons.add(WeaponFactory.createSword())

        SoundManager.play("main song")

        // Bind
        GameEngine.heroEventManager.sub(this, mutableSetOf(HeroEventType.XP_UP, HeroEventType.XP_LEVELUP, HeroEventType.HP_ZERO, HeroEventType.HP_DOWN, HeroEventType.ATTACK))
        GameEngine.inputEventManager.sub(this, mutableSetOf(InputEventType.ENTER))

        map.init()
        gui.init()
        state = GameState.SURVIVING
    }

    fun reset() {
        hero.hp = hero.maxHp
        hero.xp = 0
        hero.position = Vector2()

        collidableEntities.clear()
        staticEntities.clear()
        movableEntities.clear()
    }
    
    fun run() {
        when (state) {
            GameState.UNINITIALIZED -> {
                init()
            }
            GameState.SURVIVING -> {
                createEnemies()
                moveEntities()
                checkCollisions()
                handleHeroLevelUp()
                handleDeaths()
            }
            GameState.SHOP -> {

            }
        }
    }

    fun createEnemies() {
        if (collidableEntities.size < wave*ENEMIES_PER_WAVE) {
            for (i in 0..wave*ENEMIES_PER_WAVE) {
                collidableEntities.add(EntityFactory.createRandomEnemy())
            }
        }
    }

    fun moveEntities() {
        movableEntities.forEach { it.move() }
        collidableEntities.forEach { it.move() }

        objects.forEach { it.move() }

        hero.weapons.forEach {
            it.moveProjectiles()
        }
        hero.move()
    }

    fun checkCollisions() {
        // remove items
        objects.removeIf { it.delete }

        collidableEntities.forEach {
            it.checkCollisionBetweenEnemiesToRepulseThem()
        }

        hero.weapons.forEach {
            it.fire()
            it.checkProjectilesCollisions()
        }

        hero.checkCollisionWithObjects()
        hero.checkCollisionWithEnemies()
    }

    fun handleHeroLevelUp() {
        hero.checkForLevelUp()
    }

    fun handleDeaths() {
        staticEntities.removeIf { it.hp <= 0  }
        movableEntities.removeIf { it.hp <= 0  }
        collidableEntities.removeIf {
            if (it.hp <= 0) {
                if (Random.nextBoolean()) objects.add(EntityFactory.createSoul(it.position))
            }
            it.hp <= 0
        }

        if (hero.hp <= 0) {
            if (GameEngine.debug) {
                hero.hp = hero.maxHp
            } else {
                SoundManager.stop("main song")
                SoundManager.play("death")
                GameEngine.state = EngineState.GAME_OVER
            }
        }
    }

    fun draw(g: Graphics2D) {
        map.draw(g)
        objects.forEach { it.draw(g) }

        collidableEntities.forEach { it.draw(g) }
        movableEntities.forEach { it.draw(g) }
        staticEntities.forEach { it.draw(g) }

        hero.weapons.forEach { it.drawProjectiles(g) }
        hero.draw(g)
        gui.draw(g)

        if (state == GameState.SHOP) {
            shop.draw(g)
        }
    }

    override fun on(e: ListenerEventTypeInterface) {
        if (e is HeroEventType) {
            when (e) {
                HeroEventType.XP_LEVELUP -> {
                    SoundManager.play("level up")
                    state = GameState.SHOP
                }
                HeroEventType.XP_UP -> SoundManager.play("xp")
                else -> {}
            }
        }

        if (e is InputEventType) {
            when (e) {
                InputEventType.ESCAPE -> TODO()
                InputEventType.ENTER -> {
                    if (state == GameState.SHOP) state = GameState.SURVIVING
                }
                InputEventType.SPACE -> TODO()
            }
        }

    }
}