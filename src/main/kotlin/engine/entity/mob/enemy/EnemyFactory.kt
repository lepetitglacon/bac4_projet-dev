package engine.entity.mob.enemy

import engine.GameEngine
import engine.entity.mob.enemy.boss.Corkus
import engine.entity.mob.enemy.boss.EnemyBoss
import engine.entity.registrer.EnemyRegistrerType
import engine.resource.SpriteFactory
import kotlin.random.Random

object EnemyFactory {
    val padding = 500
    
    fun createRandomEnemy(et: EnemyRegistrerType): Enemy {
        val enemy: Enemy

        when (et.type) {
            EnemyType.WARRIOR ->
            {
                enemy = Warrior()
                enemy.sprite = SpriteFactory.get(et.name)
                enemy.type = et.type
            }
            EnemyType.SPECTRE -> TODO()
            EnemyType.APOSTLE -> TODO()
        }
        // coordonées
        enemy.width = et.width
        enemy.height = et.height
        setRandomCoordinates(enemy)

        // HP + bar
        enemy.hp = et.hp.toDouble()
        enemy.maxHp = et.maxHp.toDouble()
        enemy.xpToGive = et.xp

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
        val heroXMin = (GameEngine.game?.hero?.pos?.x!!) - GameEngine.window.WIDTH/2 - enemy.width
        val heroXMax = (GameEngine.game?.hero?.pos?.x!!) + GameEngine.window.WIDTH/2 + enemy.width
        val heroYMin = (GameEngine.game?.hero?.pos?.y!!) - GameEngine.window.HEIGHT/2 - enemy.height
        val heroYMax = (GameEngine.game?.hero?.pos?.y!!) + GameEngine.window.HEIGHT/2 + enemy.height

        val bool = Random.nextBoolean()
        if (bool) {
            // set width
            enemy.pos.x = Random.nextDouble(heroXMin - padding, heroXMax + padding)
            enemy.pos.y = if (Random.nextBoolean()) heroYMin - enemy.height else heroYMax + enemy.height
        } else {
            // set height
            enemy.pos.x = if (Random.nextBoolean()) heroXMin - enemy.width else heroXMax + enemy.width
            enemy.pos.y = Random.nextDouble(heroYMin - padding, heroYMax + padding)
        }
    }

    fun createBoss() : EnemyBoss {
        val b = Corkus()
        b.width = 64
        b.height = 64
        setRandomCoordinates(b)
        return b
    }
}