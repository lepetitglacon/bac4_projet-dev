package engine.entity.factory

import Vector2
import engine.GameEngine
import engine.entity.DrawablePosition
import engine.entity.mob.Enemy
import java.awt.Color
import kotlin.random.Random

object EntityFactory {
    fun createRandomEnemy(): Enemy {
        val enemy = Enemy()
        val heroXMin = GameEngine.game.hero.centerPosition.x - GameEngine.window.WIDTH/2
        val heroXMax = GameEngine.game.hero.centerPosition.x + GameEngine.window.WIDTH/2
        val heroYMin = GameEngine.game.hero.centerPosition.y - GameEngine.window.HEIGHT/2
        val heroYMax = GameEngine.game.hero.centerPosition.y + GameEngine.window.HEIGHT/2
        if (Random.nextBoolean()) {
            enemy.centerPosition.x = Random.nextDouble(heroXMin, heroXMax)
            val y = if (Random.nextBoolean()) heroYMin else heroYMax
            enemy.centerPosition.y = y
        } else {
            val x = if (Random.nextBoolean()) heroXMin else heroXMax
            enemy.centerPosition.x = x
            enemy.centerPosition.y = Random.nextDouble(heroYMin, heroYMax)
        }
        enemy.centerPosition = Vector2(enemy.centerPosition.x + 50 / 2, enemy.centerPosition.y + 50 / 2)
        return enemy
    }

    fun createEnemyFromPosition(v: Vector2): Enemy {
        val enemy = Enemy()
        enemy.centerPosition = v
        enemy.drawingPosition = DrawablePosition.RELATIVE
        return enemy
    }
}