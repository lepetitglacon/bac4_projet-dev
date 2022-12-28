package engine.entity.mob.enemy

import engine.GameEngine
import kotlin.random.Random

object EnemyFactory {
    
    fun createRandomEnemy(): Enemy {
        val enemy = Warrior()
        // get coordinates
        val heroXMin = (GameEngine.game?.hero?.x ?: 0) - GameEngine.window.WIDTH/2 - enemy.width
        val heroXMax = (GameEngine.game?.hero?.x ?: 0)  + GameEngine.window.WIDTH/2 + enemy.width
        val heroYMin = (GameEngine.game?.hero?.y ?: 0)  - GameEngine.window.HEIGHT/2 - enemy.height
        val heroYMax = (GameEngine.game?.hero?.y ?: 0)  + GameEngine.window.HEIGHT/2 + enemy.height
        if (Random.nextBoolean()) {
            enemy.x = Random.nextInt(heroXMin, heroXMax)
            val y = if (Random.nextBoolean()) heroYMin else heroYMax
            enemy.y = y
        } else {
            val x = if (Random.nextBoolean()) heroXMin else heroXMax
            enemy.x = x
            enemy.y = Random.nextInt(heroYMin, heroYMax)
        }
        enemy.x = enemy.x + 50 / 2
        enemy.y = enemy.y + 50 / 2
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