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
        // coordonées
        enemy.width = et.width
        enemy.height = et.height
        setRandomCoordinates(enemy)

        // HP + bar
        enemy.hp = et.hp
        enemy.maxHp = et.maxHp

        return enemy
    }

    fun createFromRegistrer(numberOfEnemies: Int): MutableList<Enemy> {
        val enemies = mutableListOf<Enemy>()
        var i = 0
        while (i <= numberOfEnemies) {
            GameEngine.enemyRegistrer.enemies.forEach {
                if (it.startingWave < GameEngine.game?.wave!!) {
                    enemies.add(createRandomEnemy(it))
                    i++
                }
            }
        }
        return enemies
    }

    private fun setRandomCoordinates(enemy: Enemy) {
        fun Boolean.toInt(): Int = if (this) 1 else 0

        val heroXMin = (GameEngine.game?.hero?.pos?.x!!) - GameEngine.window.WIDTH/2 - enemy.width
        val heroXMax = (GameEngine.game?.hero?.pos?.x!!) + GameEngine.window.WIDTH/2 + enemy.width
        val heroYMin = (GameEngine.game?.hero?.pos?.y!!) - GameEngine.window.HEIGHT/2 - enemy.height
        val heroYMax = (GameEngine.game?.hero?.pos?.y!!) + GameEngine.window.HEIGHT/2 + enemy.height

        val bool = Random.nextBoolean()
        if (bool) {
            enemy.pos.x = Random.nextDouble(heroXMin, heroXMax)
            val y = if (Random.nextBoolean()) heroYMin - enemy.height else heroYMax + enemy.height
            enemy.pos.y = y
        } else {
            val x = if (Random.nextBoolean()) heroXMin - enemy.width else heroXMax + enemy.width
            enemy.pos.x = x
            enemy.pos.y = Random.nextDouble(heroYMin, heroYMax)
        }
//        enemy.pos.x += enemy.width + bool.toInt() * 2
//        enemy.pos.y += enemy.height + bool.toInt() * 2
    }
}