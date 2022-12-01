import Renderer.WINDOW_HEIGHT
import Renderer.WINDOW_WIDTH
import engine.maths.Vector2
import java.awt.Color
import kotlin.random.Random

const val ENTITY_RAYON = 25
const val ENTITY_SPEED = 10
const val ENNEMY_PER_WAVE = 6

enum class EntityState {
    IDLE
}


object EntityFactory {
    fun createHero(): Hero {
        return Hero()
    }

    fun createRandomEnemy(): Enemy {
        val enemy = Enemy()
        val heroXMin = Renderer.hero.position.x - WINDOW_WIDTH/2
        val heroXMax = Renderer.hero.position.x + WINDOW_WIDTH/2
        val heroYMin = Renderer.hero.position.y - WINDOW_HEIGHT/2
        val heroYMax = Renderer.hero.position.y + WINDOW_HEIGHT/2
        if (Random.nextBoolean()) {
            enemy.position.x = Random.nextDouble(heroXMin, heroXMax)
            val y = if (Random.nextBoolean()) heroYMin else heroYMax
            enemy.position.y = y
        } else {
            val x = if (Random.nextBoolean()) heroXMin else heroXMax
            enemy.position.x = x
            enemy.position.y = Random.nextDouble(heroYMin, heroYMax)
        }
        enemy.position = Vector2(enemy.position.x + ENTITY_RAYON / 2, enemy.position.y + ENTITY_RAYON / 2)
        enemy.color = Color.BLACK
        return enemy
    }

    /**
     * Add new ennemies to Renderer entity array
     */
    fun createEnemiesForWave(wave: Int) {
        val number = ENNEMY_PER_WAVE * wave
        for (i in 0..number) {
            Renderer.entities.add(createRandomEnemy())
        }
    }

    fun createBullet(): Bullet {
        val bullet = Bullet()
        bullet.velocity = Renderer.hero.velocity
        return bullet
    }

    private fun getRandomInt(min: Int, max: Int) {
        return
    }
}