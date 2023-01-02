package engine.entity.mob.enemy

import engine.GameEngine
import kotlin.random.Random

object EnemyFactory {
    
    fun createRandomEnemy(): Enemy {
        val enemy = Warrior()
        // get coordinates
        val heroXMin = (GameEngine.game?.hero?.pos?.x!!) - GameEngine.window.WIDTH/2 - enemy.width
        val heroXMax = (GameEngine.game?.hero?.pos?.x!!) + GameEngine.window.WIDTH/2 + enemy.width
        val heroYMin = (GameEngine.game?.hero?.pos?.y!!) - GameEngine.window.HEIGHT/2 - enemy.height
        val heroYMax = (GameEngine.game?.hero?.pos?.y!!) + GameEngine.window.HEIGHT/2 + enemy.height
        if (Random.nextBoolean()) {
            enemy.pos.x = Random.nextDouble(heroXMin, heroXMax)
            val y = if (Random.nextBoolean()) heroYMin else heroYMax
            enemy.pos.y = y
        } else {
            val x = if (Random.nextBoolean()) heroXMin else heroXMax
            enemy.pos.x = x
            enemy.pos.y = Random.nextDouble(heroYMin, heroYMax)
        }
        enemy.pos.x += 50 / 2
        enemy.pos.y += 50 / 2
        enemy.width = 64
        enemy.height = 64
        return enemy
    }

    fun createFromRegistrer(): MutableList<Enemy> {
        val enemies = mutableListOf<Enemy>()
        GameEngine.enemyRegistrer.enemies.forEach {
            enemies.add(createRandomEnemy())
        }
        return enemies;
    }

    fun createWarrior() {

    }
}