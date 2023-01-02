package engine.entity.mob.enemy

import engine.GameEngine
import engine.entity.registrer.EnemyRegistrerType
import engine.resource.SpriteFactory
import kotlin.random.Random

object EnemyFactory {
    
    fun createRandomEnemy(et: EnemyRegistrerType): Enemy {
        val enemy: Enemy

        when (et.type) {
            EnemyType.WARRIOR ->
            {
                enemy = Warrior()
                enemy.sprite = SpriteFactory.get(et.name)
            }
            EnemyType.SPECTRE -> TODO()
            EnemyType.APOSTLE -> TODO()
        }

        enemy.hp = et.hp
        enemy.maxHp = et.maxHp

        setRandomCoordinates(enemy)
        enemy.width = et.width
        enemy.height = et.height



        return enemy
    }

    fun createFromRegistrer(): MutableList<Enemy> {
        val enemies = mutableListOf<Enemy>()
        var i = 0
        while (i < GameEngine.game?.enemyPerWave!!) {
            GameEngine.enemyRegistrer.enemies.forEach {
                enemies.add(createRandomEnemy(it))
                i++
            }
        }
        return enemies
    }

    private fun setRandomCoordinates(enemy: Enemy): Enemy {
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
        return enemy
    }
}